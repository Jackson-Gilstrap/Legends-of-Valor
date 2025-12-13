package Controllers;

import Enums.Direction;
import Entities.Hero;
import Entities.Monster;
import Interfaces.HasHero;
import Interfaces.HasMonster;
import Interfaces.HasSpawnPosition;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.ObstacleSpace;
import WorldSets.Spaces.PlainSpace;
import WorldSets.Spaces.WallSpace;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

public class LVMovementController extends MovementController {

    private final List<? extends Positionable> heroes;
    private final List<? extends Positionable> monsters;
    private final boolean isHero;
    private final int spawnRow;
    private final int spawnCol;

    public LVMovementController(MapSet mapSet,
                                Positionable target,
                                List<? extends Positionable> heroes,
                                List<? extends Positionable> monsters,
                                boolean isHero) {
        super(mapSet, target);
        this.heroes = heroes == null ? Collections.emptyList() : heroes;
        this.monsters = monsters == null ? Collections.emptyList() : monsters;
        this.isHero = isHero;
        if (target instanceof HasSpawnPosition) {
            this.spawnRow = ((HasSpawnPosition) target).getSpawnRow();
            this.spawnCol = ((HasSpawnPosition) target).getSpawnCol();
        } else {
            this.spawnRow = target.getRow();
            this.spawnCol = target.getCol();
        }
    }

    @Override
    public void move(Direction direction) {
        attemptMove(direction);
    }

    public boolean attemptMove(Direction direction) {
        int targetRow = target.getRow() + direction.dRow();
        int targetCol = target.getCol() + direction.dCol();

        if (!mapSet.inBounds(targetRow, targetCol)) {
            onOutOfBounds(targetRow, targetCol);
            return false;
        }

        Space destination = mapSet.getSpace(targetRow, targetCol);
        if (destination instanceof WallSpace || !destination.canEnter()) {
            if (isHero) {
                onBlocked(destination);
            }
            return false;
        }

        if (isHero && isHeroOccupied(targetRow, targetCol)) {
            System.out.println("Another hero already occupies that space.");
            return false;
        }

        if (isHero && isMonsterOccupied(targetRow, targetCol)) {
            System.out.println("A monster is blocking that space. Defeat it before advancing.");
            return false;
        }

        if (isHero && isBlockedByMonsterAhead(targetRow, targetCol)) {
            System.out.println("You cannot advance past an enemy monster in this lane.");
            return false;
        }

        if (!isHero && isMonsterOccupied(targetRow, targetCol)) {
            System.out.println("Another monster already occupies that space.");
            return false;
        }

        if (!isHero && isHeroOccupied(targetRow, targetCol)) {
            System.out.println("The path is blocked by a hero.");
            return false;
        }

        target.setPosition(targetRow, targetCol);
        return true;
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
                new int[]{targetHero.getRow() - 1, targetHero.getCol()},
                new int[]{targetHero.getRow() + 1, targetHero.getCol()},
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

            if (isHeroOccupied(row, col)) {
                continue;
            }

            if (isBlockedByMonsterAhead(row, col)) {
                continue;
            }

            target.setPosition(row, col);
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

        if (isHeroOccupied(row, col)) {
            System.out.println("Another hero already occupies that space.");
            return false;
        }

        if (isBlockedByMonsterAhead(row, col)) {
            System.out.println("Cannot teleport behind while a monster blocks this lane.");
            return false;
        }

        target.setPosition(row, col);
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

        if (!mapSet.inBounds(spawnRow, spawnCol)) {
            System.out.println("Your nexus cannot be located on the map.");
            return false;
        }

        Space destination = mapSet.getSpace(spawnRow, spawnCol);
        if (destination instanceof WallSpace || !destination.canEnter()) {
            System.out.println("Your nexus space is blocked.");
            return false;
        }

        if (isHeroOccupied(spawnRow, spawnCol)) {
            System.out.println("Another hero is occupying your nexus.");
            return false;
        }

        if (isMonsterOccupied(spawnRow, spawnCol)) {
            System.out.println("A monster is occupying your nexus. Clear it before recalling.");
            return false;
        }

        target.setPosition(spawnRow, spawnCol);
        System.out.printf("You have been recalled to your nexus at (%d,%d).%n", spawnRow, spawnCol);
        return true;
    }

    private boolean isHeroOccupied(int row, int col) {
        for (Positionable hero : heroes) {
            if (hero == null || hero == target) {
                continue;
            }
            if (hero.getRow() == row && hero.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private boolean isMonsterOccupied(int row, int col) {
        for (Positionable monster : monsters) {
            if (monster == null || monster == target) {
                continue;
            }
            if (monster.getRow() == row && monster.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private boolean isBlockedByMonsterAhead(int targetRow, int targetCol) {
        int lane = laneOf(targetCol);
        for (Positionable monster : monsters) {
            if (monster == null) {
                continue;
            }
            if (laneOf(monster.getCol()) != lane) {
                continue;
            }
            // heroes advance upward, so a smaller row index is "ahead"
            if (targetRow < monster.getRow()) {
                return true;
            }
        }
        return false;
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
    public boolean clearObstacle(Direction direction) {
        if (!isHero) {
            System.out.println("Only heroes can clear obstacles.");
            return false;
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

        if (!(target instanceof HasHero)) {
            System.out.println("Cannot identify the acting hero.");
            return false;
        }

        HasHero heroHolder = (HasHero) target;
        Hero hero = heroHolder.getHeroEntity();
        if (hero == null) {
            System.out.println("No hero found on this token.");
            return false;
        }

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
     * Monsters in orthogonal/diagonal range (or same cell).
     */
    public List<Positionable> monstersInRange() {
        List<Positionable> inRange = new ArrayList<Positionable>();
        for (Positionable m : monsters) {
            if (m == null) {
                continue;
            }
            int dr = Math.abs(m.getRow() - target.getRow());
            int dc = Math.abs(m.getCol() - target.getCol());
            if (dr <= 1 && dc <= 1) {
                inRange.add(m);
            }
        }
        return inRange;
    }

    /**
     * Simple one-round battle between acting hero and selected monster.
     */
    public boolean battleMonster(Positionable monsterPos) {
        if (!isHero) {
            System.out.println("Only heroes can start battles.");
            return false;
        }
        if (!(target instanceof HasHero)) {
            System.out.println("Cannot identify acting hero.");
            return false;
        }
        if (!(monsterPos instanceof HasMonster)) {
            System.out.println("That target is not a monster.");
            return false;
        }

        HasHero heroHolder = (HasHero) target;
        HasMonster monsterHolder = (HasMonster) monsterPos;
        Hero hero = heroHolder.getHeroEntity();
        Monster monster = monsterHolder.getMonsterEntity();

        if (hero == null || monster == null) {
            System.out.println("Could not resolve hero or monster for battle.");
            return false;
        }

        int heroAtk = hero.getStats().getAttack();
        int monsterDr = (int) (heroAtk * monster.getStats().getDamage_reduction());
        int dmgToMonster = Math.max(1, heroAtk - monsterDr);

        int monsterAtk = monster.getStats().getAttack();
        int heroDr = (int) (monsterAtk * hero.getStats().getDamage_reduction());
        int dmgToHero = Math.max(1, monsterAtk - heroDr);

        System.out.printf("%s strikes %s for %d damage.%n", hero.getName(), monster.getName(), dmgToMonster);
        monster.getStats().setHealth(monster.getStats().getHealth() - dmgToMonster);

        if (monster.getStats().getHealth() > 0) {
            System.out.printf("%s counterattacks %s for %d damage.%n", monster.getName(), hero.getName(), dmgToHero);
            int newHp = Math.max(0, hero.getStats().getHealth() - dmgToHero);
            hero.getStats().setHealth(newHp);
        } else {
            System.out.printf("%s is defeated!%n", monster.getName());
        }

        if (monster.getStats().getHealth() <= 0) {
            monsters.remove(monsterPos);
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
}
