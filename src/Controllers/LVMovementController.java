package Controllers;

import Enums.Direction;
import Game.GameUI;
import Entities.Entity;
import Entities.Hero;
import Entities.Monster;
import Interfaces.Positionable;
import Parties.Party;
import Market.Market;
import WorldSets.Space;
import WorldSets.Maps.Arena;
import WorldSets.Maps.UnitToken;
import WorldSets.Spaces.MarketSpace;
import WorldSets.Spaces.ObstacleSpace;
import WorldSets.Spaces.PlainSpace;
import WorldSets.Spaces.WallSpace;

import java.util.List;
import java.util.Arrays;

public class LVMovementController extends MovementController<Arena, Entity> {
    private boolean isHero;
    private final GameUI ui;
    
    public LVMovementController(GameUI ui, Arena map) {
        super(map, null);
        this.ui = ui;
        isHero = true;
    }

    public void setTarget(Entity e){
        target = e;
    }

    public void takeTurns(){
        isHero = !isHero;
    }

    @Override
    public boolean move(Direction direction) {
        int targetRow = target.getRow() + direction.dRow();
        int targetCol = target.getCol() + direction.dCol();

        if (!mapSet.inBounds(targetRow, targetCol)) {
            onOutOfBounds(targetRow, targetCol);
            return false;
        }

        Space destination = mapSet.getSpace(targetRow, targetCol);
        if (!destination.canEnter()) {
            if (isHero) {
                onBlocked(destination);
            }
            return false;
        }

        if (isHero && mapSet.hasHeroAt(targetRow, targetCol)) {
            System.out.println("Another hero already occupies that space.");
            return false;
        }

        if (isHero && mapSet.hasMonsterAt(targetRow, targetCol)) {
            System.out.println("A monster is blocking that space. Defeat it before advancing.");
            return false;
        }

        if (isHero && mapSet.hasMonsterInSameRow(target) && direction.equals(Direction.UP)) {
            System.out.println("You cannot advance past an enemy monster in this lane.");
            return false;
        }

        if (!isHero && mapSet.hasMonsterAt(targetRow, targetCol)) {
            System.out.println("Another monster already occupies that space.");
            return false;
        }

        if (!isHero && mapSet.hasHeroAt(targetRow, targetCol)) {
            System.out.println("The path is blocked by a hero.");
            return false;
        }

        return mapSet.move(target, targetRow, targetCol);
    }


    public boolean teleport(){
        Positionable actingHero = getTarget();

        // all the heroes on the map
        Party heroes = mapSet.getHeroes();

        if (heroes == null || heroes.size() < 2) {
            System.out.println("No other heroes available to teleport to.");
            return false;
        }

        System.out.println("Select a hero to teleport next to:");
        for (int i = 0; i < heroes.size(); i++) {
            Positionable hero = heroes.get(i);
            String label = (hero == actingHero) ? " (you)" : "";
            System.out.printf("%d) Hero at (%d,%d)%s%n", i + 1, hero.getRow(), hero.getCol(), label);
        }

        int selection = ui.askInt() - 1;
        if (selection < 0 || selection >= heroes.size()) {
            System.out.println("Invalid hero selection.");
            return false;
        }

        Positionable targetHero = heroes.get(selection);
        if (targetHero == actingHero) {
            System.out.println("You cannot teleport to yourself.");
            return false;
        }

        System.out.println("Teleport mode: (A) Adjacent | (B) Behind");
        String mode = ui.askOneWord("Choose mode: ").toUpperCase();

        boolean success = "B".equals(mode)
                ? teleportBehindHero(targetHero)
                : teleportToHero(targetHero);

        if (!success) {
            System.out.println("Teleport failed. Try a different mode or target.");
        }
        return success;
    }

    /**
     * Teleport adjacent to a target hero while observing Legends of Valor lane rules.
     *
     * @param targetHero hero to teleport next to
     * @return true if teleport succeeded
     */
    public boolean teleportToHero(Positionable targetHero) {
        if (!isHero) {
            System.out.println("Only heroes may teleport.");
            return false;
        }

        if (targetHero == null) {
            System.out.println("No target hero selected to teleport to.");
            return false;
        }

        if (laneOf(target.getCol()) == laneOf(targetHero.getCol())) {
            System.out.println("Teleport works only between different lanes.");
            return false;
        }

        List<int[]> candidates = Arrays.asList(
                new int[]{targetHero.getRow(), targetHero.getCol() - 1},
                new int[]{targetHero.getRow(), targetHero.getCol() + 1}
        );

        for (int[] candidate : candidates) {
            int row = candidate[0];
            int col = candidate[1];

            if (!mapSet.inBounds(row, col)) {
                continue;
            }

            // must change lanes
            if (laneOf(col) == laneOf(target.getCol())) {
                continue;
            }

            Space destination = mapSet.getSpace(row, col);
            if (destination instanceof WallSpace || !destination.canEnter()) {
                continue;
            }

            if (row < targetHero.getRow()) {
                // cannot teleport ahead of the target hero
                continue;
            }

            if (mapSet.hasHeroAt(row, col)) {
                continue;
            }

            if (isBlockedByMonsterAhead(row, col)) {
                continue;
            }

            mapSet.move(target, row, col);
            return true;
        }

        System.out.println("No valid teleport destination found near that hero.");
        return false;
    }

    /**
     * Teleport directly behind a target hero (same lane, one row back).
     *
     * @param targetHero hero to teleport behind
     * @return true if teleport succeeded
     */
    public boolean teleportBehindHero(Positionable targetHero) {
        if (!isHero) {
            System.out.println("Only heroes may teleport.");
            return false;
        }
        if (targetHero == null) {
            System.out.println("No target hero selected to teleport behind.");
            return false;
        }

        int row = targetHero.getRow() + 1;
        int col = targetHero.getCol();

        if (!mapSet.inBounds(row, col)) {
            System.out.println("No valid space behind that hero.");
            return false;
        }

        Space destination = mapSet.getSpace(row, col);
        if (destination instanceof WallSpace || !destination.canEnter()) {
            System.out.println("Space behind that hero is blocked.");
            return false;
        }

        if (mapSet.hasHeroAt(row, col)) {
            System.out.println("Another hero already occupies that space.");
            return false;
        }

        if (isBlockedByMonsterAhead(row, col)) {
            System.out.println("Cannot teleport behind while a monster blocks this lane.");
            return false;
        }

        mapSet.move(target, row, col);
        return true;
    }

    /**
     * Recall the acting hero back to their original nexus space.
     *
     * @return true if the recall succeeds
     */
    public boolean recallToNexus() {
        if (!isHero) {
            System.out.println("Only heroes may recall to a nexus.");
            return false;
        }

        UnitToken spawnPos = mapSet.getSpawnPosition((Hero)target);
        int spawnRow = spawnPos.getSpawnRow();
        int spawnCol = spawnPos.getSpawnCol();
        if (mapSet.hasHeroAt(spawnRow, spawnCol)) {
            System.out.println("Another hero is occupying your nexus.");
            return false;
        }

        if (mapSet.hasMonsterAt(spawnRow, spawnCol)) {
            System.out.println("A monster is occupying your nexus. Clear it before recalling.");
            return false;
        }

        mapSet.move(target, spawnRow, spawnCol);
        System.out.printf("You have been recalled to your nexus at (%d,%d).%n", spawnRow, spawnCol);
        return true;
    }


    private boolean isBlockedByMonsterAhead(int targetRow, int targetCol) {
        return mapSet.hasMonsterInSameRow(target);
    }

    private int laneOf(int col) {
        // Arena uses walls at columns 2 and 5 to create three lanes.
        if (col < 2) {
            return 0;
        }
        if (col < 5) {
            return 1;
        }
        return 2;
    }

    public Positionable getTarget() {
        return target;
    }

    /**
     * Clear an adjacent obstacle, converting it to plain space for 1 HP.
     */
    public boolean clearObstacle() {
        if (!isHero) {
            System.out.println("Only heroes can clear obstacles.");
            return false;
        }

        // ask for the specific direction
        String dirInput;
        Direction direction = null;
        while(direction == null){
            dirInput = ui.askOneWord("Choose direction to clear obstacle (W/A/S/D): ").toUpperCase();
            switch (dirInput) {
                case "W":
                    direction = Direction.UP;
                    break;
                case "S":
                    direction = Direction.DOWN;
                    break;
                case "A":
                    direction = Direction.LEFT;
                    break;
                case "D":
                    direction = Direction.RIGHT;
                    break;
                default:
                    System.out.println("Invalid direction. Please enter again.");
            }
        }
        
        int targetRow = target.getRow() + direction.dRow();
        int targetCol = target.getCol() + direction.dCol();

        if (!mapSet.inBounds(targetRow, targetCol)) {
            System.out.println("No obstacle there (out of bounds).");
            return false;
        }

        Space destination = mapSet.getSpace(targetRow, targetCol);
        if (!(destination instanceof ObstacleSpace)) {
            System.out.println("That space is not an obstacle.");
            return false;
        }

        Hero hero = (Hero)target;
        int currentHealth = hero.getStats().getHealth();
        if (currentHealth <= 1) {
            System.out.println("Not enough health to clear the obstacle (costs 1 HP).");
            return false;
        }

        hero.getStats().setHealth(currentHealth - 1);
        mapSet.setSpace(targetRow, targetCol, new PlainSpace("Plain", targetRow, targetCol));
        System.out.printf("Obstacle cleared at (%d,%d). %s loses 1 HP.%n", targetRow, targetCol, hero.getName());
        return true;
    }

    /**
     * Simple one-round battle between acting hero and selected monster.
     */
    public boolean attackMonster() {
        List<Monster> candidates = mapSet.getMonstersInRange(target, 1);
        if (candidates.isEmpty()) {
            System.out.println("No monsters in battle range.");
            return false;
        }

        int choice;
        if(candidates.size() <=1){
            // attack directly
            choice = 0;
        } else{
            System.out.println("Choose a monster to battle:");
            for (int i = 0; i < candidates.size(); i++) {
                Positionable m = candidates.get(i);
                System.out.printf("%d) Monster at (%d,%d)%n", i + 1, m.getRow(), m.getCol());
            }
            choice = ui.askInt() - 1;
            while (choice < 0 || choice >= candidates.size()) {
                System.out.println("Invalid monster selection.");
                choice = ui.askInt() - 1;
            }
        }

        if (!isHero) {
            System.out.println("Only heroes can start battles.");
            return false;
        }

        Hero hero = (Hero)target;
        Monster monster = candidates.get(choice);

        if (hero == null || monster == null) {
            System.out.println("Could not resolve hero or monster for battle.");
            return false;
        }

        hero.attack(monster);

        if (monster.getStats().getHealth() <= 0) {
            mapSet.removeMonster(monster);
            System.out.printf("%s is defeated!%n", monster.getName());
        }

        return true;
    }

    
    @Override
    protected void onOutOfBounds(int row, int col) {
        System.out.println("That move would leave the arena bounds.");
    }

    @Override
    protected void onBlocked(Space space) {
        if (space instanceof WallSpace) {
            System.out.println("A lane wall blocks that path.");
            return;
        }
        System.out.println("You cannot move onto that space.");
    }

    @Override
    public void getHeroInfo() {
        mapSet.getHeroes().getPartyInfo();

        System.out.print("Select a hero by number: ");
        int choice = ui.askInt() - 1;

        if (choice < 0 || choice >= mapSet.getHeroes().size()) {
            System.out.println("Invalid hero selection.");
            return;
        }

        Hero hero = mapSet.getHeroes().get(choice);

        HeroInfoController infoController = new HeroInfoController(ui);
        infoController.showHeroDetails(hero);
    }
}
