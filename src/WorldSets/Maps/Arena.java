package WorldSets.Maps;

import Factories.ArmorFactory;
import Factories.PotionFactory;
import Factories.SpellFactory;
import Factories.WeaponFactory;
import Items.Item;
import Parties.Party;
import Seeders.ItemSeeder;
import Utility.Inventory;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Market;
import WorldSets.Space;
import WorldSets.Spaces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Entities.Hero;
import Entities.Monster;

public class Arena extends MapSet {
    // constants
    
    // the mapset
    private List<Lane> allLanes; // the lanes

    private final Market heroNexusMarket;
    private final Market monsterNexusMarket;

    // the positions of the entities
    private final List<UnitToken> heroTokens = new ArrayList<>();
    private final List<UnitToken> monsterTokens = new ArrayList<>();

    private final Party heroes = new Party();
    private final List<Monster> monsters = new ArrayList<>();

    public Arena(int rows, int cols) {
        super(rows, cols);
        allLanes = Arrays.asList(Lane.values());

        this.heroNexusMarket = new Market(buildMarketInventory());
        this.monsterNexusMarket = new Market(new Inventory());
        build();
    }

    // -------------------------------
    // Public methods
    // -------------------------------
    @Override
    protected void build() {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {

                if (c == 2 || c == 5) {
                    grids[c][r] = new WallSpace("Wall", r, c);
                    continue;
                }

                if (r == 0) { // monster nexus
                    grids[c][r] = new NexusSpace("Nexus", r, c, monsterNexusMarket, NexusSpace.NexusType.MONSTER);
                    continue;
                }

                if (r == getRows() - 1) { // hero nexus
                    grids[c][r] = new NexusSpace("Nexus", r, c, heroNexusMarket, NexusSpace.NexusType.HERO);
                    continue;
                }

                grids[c][r] = generateRandomLaneSpace(r, c);
            }
        }
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();

        sb.append("    ");
        for (int c = 0; c < getCols(); c++) {
            sb.append(String.format("%-11d", c));
        }
        sb.append("\n");

        String horizontalBorder = repeat("-", getCols() * 11 + 4);
        sb.append(horizontalBorder).append("\n");

        for (int r = 0; r < getRows(); r++) {
            List<StringBuilder> rowBuilders = new ArrayList<StringBuilder>();
            for (int i = 0; i < 3; i++) {
                rowBuilders.add(new StringBuilder());
            }

            for (int c = 0; c < getCols(); c++) {
                Space space = grids[c][r];
                boolean heroHere = isOccupied(heroTokens, r, c);
                boolean monsterHere = isOccupied(monsterTokens, r, c);
                List<String> lines = space.renderLinesWithOccupants(heroHere, monsterHere);

                for (int i = 0; i < lines.size(); i++) {
                    rowBuilders.get(i).append(lines.get(i)).append(" ");
                }
            }

            for (int i = 0; i < rowBuilders.size(); i++) {
                if (i == 1) {
                    sb.append(String.format("%2d | ", r));
                } else {
                    sb.append("   | ");
                }
                sb.append(rowBuilders.get(i)).append("\n");
            }
            sb.append(horizontalBorder).append("\n");
        }

        return sb.toString();
    }

    public boolean isMonsterNexusInvaded() {
        for (UnitToken heroToken : heroTokens) {
            if (heroToken.getRow() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isHeroNexusInvaded() {
        for (UnitToken monsterToken : monsterTokens) {
            if (monsterToken.getRow() == getRows() - 1) {
                return true;
            }
        }
        return false;
    }

    

    // -------------------------------
    // Public methods
    // -------------------------------

    public boolean hasMonsterAt(int row, int col) {
        for (UnitToken token : monsterTokens) {
            if (token.getRow() == row && token.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public boolean hasHeroAt(int row, int col) {
        for (UnitToken token: heroTokens) {
            if (token.getRow() == row && token.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public List<Monster> getMonstersInRange(Positionable center, int range) {
        List<Monster> inRange = new ArrayList<>();
        for (Monster m : monsters) {
            if (m == null) continue;
            int dr = Math.abs(m.getRow() - center.getRow());
            int dc = Math.abs(m.getCol() - center.getCol());
            if (dr <= range && dc <= range) {
                inRange.add(m);
            }
        }
        return inRange;
    }

    public boolean hasMonsterInSameRow(Positionable center) {
        for (Monster m : monsters) {
            if (m != null && m.getRow() == center.getRow() 
                && Math.abs(m.getCol() - center.getCol()) <=1 ) {
                return true;
            }
        }
        return false;
    }



    public int laneToColumn(Lane l) {
        switch (l.getId()) {
            case 0:
                return 0;
            case 1:
                return 3;
            default:
                return 6;
        }
    }

    public void addMonster(Monster m, int row, int col){
        monsters.add(m);
        monsterTokens.add(new UnitToken(m, row, col));
        move(m, row, col);
    }

    public void remove(Monster m){
        monsters.remove(m);
    }

    public void addHero(Hero h, int row, int col){
        heroes.add(h);
        heroTokens.add(new UnitToken(h, row, col));
        move(h, row, col);
    }

    public Party getHeros(){
        return heroes;
    }

    /**
     * Respawn a hero, send him to where he borns
     * @param h
     */
    public void respawn(Positionable h){
        for(UnitToken ut: heroTokens){
            if(ut.getOccupant().equals(h)){
                move(h, ut.getSpawnRow(), ut.getSpawnCol());
            }
        }
    }

    public UnitToken getSpawnPosition(Hero h) {
        for (UnitToken token : heroTokens) {
            if (token.getOccupant().equals(h)) { 
                return token;
            }
        }

        throw new IllegalArgumentException("No spawn position found for entity: " + h);
    }

    public boolean move(Positionable p, int row, int col){

        for(UnitToken target: heroTokens){
            if(target.getOccupant() != null && target.getOccupant().equals(p)){
                target.setPosition(row, col);
                p.setPosition(row, col);
                return true;
            }
        }
        for(UnitToken target: monsterTokens){
            if(target.getOccupant() != null && target.getOccupant().equals(p)){
                target.setPosition(row, col);
                p.setPosition(row, col);
                return true;
            }
        }
        return false;
    }


    @Override
    public Space getSpace(int row, int col) {
        return grids[col][row];
    }

    @Override
    public void setSpace(int row, int col, Space space) {
        grids[col][row] = space;
    }

    public List<Lane> getAllLanes(){
        return allLanes;
    }

    public Hero findAdjacentHero(Positionable p) {
        for (Hero h : heroes) {
            if (h.getStats().getHealth() <= 0) {
                continue;
            }
            int dr = Math.abs(h.getRow() - p.getRow());
            int dc = Math.abs(h.getCol() - p.getCol());
            if (dr + dc == 1) {
                return h;
            }
        }
        return null;
    }
    

    private Space generateRandomLaneSpace(int r, int c) {
        Random rand = new Random();
        int rand_num = rand.nextInt(10);
        if (rand_num % 2 == 0) {
            return new PlainSpace("Plain", r, c);
        } else {
            if (rand_num == 3) {
                return new KoulouSpace("Koulou", r, c);
            } else if (rand_num == 5) {
                return new CaveSpace("Cave", r, c);
            } else if (rand_num == 7) {
                return new BushSpace("Bush", r, c);
            } else if (rand_num == 9) {
                return new ObstacleSpace("Obstacle", r, c);
            } else {
                return new PlainSpace("Plain", r, c);
            }
        }
    }


    public Space getSpaceAt(int row, int col) {
        return grids[col][row];
    }

    private boolean isOccupied(List<? extends Positionable> units, int row, int col) {
        if (units == null) {
            return false;
        }
        for (Positionable p : units) {
            if (p != null && p.getRow() == row && p.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private String repeat(String s, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    private Inventory buildMarketInventory() {
        ItemSeeder seeder = new ItemSeeder(
                new WeaponFactory(),
                new ArmorFactory(),
                new SpellFactory(),
                new PotionFactory()
        );

        Inventory inv = new Inventory();
        addAll(inv, seeder.seedWeapons("src/TextFiles/Weaponry.txt"));
        addAll(inv, seeder.seedArmors("src/TextFiles/Armory.txt"));
        addAll(inv, seeder.seedSpells("src/TextFiles/FireSpells.txt"));
        addAll(inv, seeder.seedSpells("src/TextFiles/IceSpells.txt"));
        addAll(inv, seeder.seedSpells("src/TextFiles/LightningSpells.txt"));
        addAll(inv, seeder.seedPotions("src/TextFiles/Potions.txt"));
        return inv;
    }

    private void addAll(Inventory inv, List<? extends Item> items) {
        if (items == null) {
            return;
        }
        for (Items.Item item : items) {
            inv.addItem(item);
        }
    }

    public Market getMarket(){
        return heroNexusMarket;
    }
}
