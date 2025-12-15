package WorldSets.Maps;

import Factories.*;
import Game.GameUI;
import Items.Item;
import Seeders.ItemSeeder;
import Utility.Inventory;
import Interfaces.Positionable;
import WorldSets.MapSet;
import Market.Market;
import Parties.MonsterParty;
import Parties.Party;
import WorldSets.Space;
import WorldSets.Spaces.*;
import Entities.Entity;
import Entities.Hero;
import Entities.Monster;

import java.util.*;

/**
 * The Arena represents the main battleground where Heroes and Monsters fight.
 * It is a type of MapSet that contains Lanes, Nexuses, and various Space types.
 * <p>
 * Rows increase downward; columns increase rightward.
 * Top row (row = 0): Monster Nexus
 * Bottom row (row = rows - 1): Hero Nexus
 * Walls divide lanes at columns 2 and 5.
 */
public class Arena extends MapSet {

    // -------------------------------
    // Fields
    // -------------------------------

    private final List<Lane> allLanes;              // All lanes in the map
    private final Market heroNexusMarket;           // Hero nexus market
    private final Market monsterNexusMarket;        // Monster nexus (usually empty)
    private final List<UnitToken> heroTokens;       // Active hero positions
    private final List<UnitToken> monsterTokens;    // Active monster positions
    private final Party heroes;                     // Hero party (read-only)
    private final MonsterParty monsters;            // Monster party (read-only)

    // -------------------------------
    // Constructor
    // -------------------------------

    public Arena(int rows, int cols, Party heroes, MonsterParty monsters) {
        super(rows, cols);
        this.allLanes = Arrays.asList(Lane.values());
        this.heroes = heroes;
        this.monsters = monsters;
        this.heroTokens = new ArrayList<>();
        this.monsterTokens = new ArrayList<>();
        this.heroNexusMarket = new Market(buildMarketInventory());
        this.monsterNexusMarket = new Market(new Inventory());
        build();
    }

    // -------------------------------
    // Map Building
    // -------------------------------

    /**
     * Build the arena grid with Nexuses, Walls, and random lane spaces.
     */
    @Override
    protected void build() {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {

                // Walls separate lanes
                if (c == 2 || c == 5) {
                    grids[r][c] = new WallSpace("Wall", r, c);
                    continue;
                }

                // Top row: Monster Nexus
                if (r == 0) {
                    grids[r][c] = new NexusSpace("Nexus", r, c, monsterNexusMarket, NexusSpace.NexusType.MONSTER);
                    continue;
                }

                // Bottom row: Hero Nexus
                if (r == getRows() - 1) {
                    grids[r][c] = new NexusSpace("Nexus", r, c, heroNexusMarket, NexusSpace.NexusType.HERO);
                    continue;
                }

                // Regular space within lane
                grids[r][c] = generateRandomLaneSpace(r, c);
            }
        }
    }

    // -------------------------------
    // Rendering
    // -------------------------------

    /**
     * Renders the arena grid as a text-based map.
     */
    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();

        // Column headers
        sb.append("    ");
        for (int c = 0; c < getCols(); c++) {
            sb.append(String.format("%-11d", c));
        }
        sb.append("\n");

        String border = repeat("-", getCols() * 11 + 4);
        sb.append(border).append("\n");

        // Render each row (3 lines per cell)
        for (int r = 0; r < getRows(); r++) {
            List<StringBuilder> rowLines = Arrays.asList(new StringBuilder(), new StringBuilder(), new StringBuilder());

            for (int c = 0; c < getCols(); c++) {
                Space space = grids[r][c];
                boolean heroHere = hasHeroAt(r, c);
                boolean monsterHere = hasMonsterAt(r, c);

                List<String> lines = space.renderLinesWithOccupants(heroHere, monsterHere);
                for (int i = 0; i < lines.size(); i++) {
                    rowLines.get(i).append(lines.get(i)).append(" ");
                }
            }

            // Add row label in the middle line
            for (int i = 0; i < 3; i++) {
                sb.append(i == 1 ? String.format("%2d | ", r) : "   | ");
                sb.append(rowLines.get(i)).append("\n");
            }
            sb.append(border).append("\n");
        }

        return sb.toString();
    }

    // -------------------------------
    // Game Logic
    // -------------------------------

    /** @return true if any hero reaches the monster nexus row (0) */
    public boolean isMonsterNexusInvaded() {
        return heroTokens.stream().anyMatch(h -> h.getRow() == 0);
    }

    /** @return true if any monster reaches the hero nexus row (rows - 1) */
    public boolean isHeroNexusInvaded() {
        return monsterTokens.stream().anyMatch(m -> m.getRow() == getRows() - 1);
    }

    /** Check whether a monster occupies a cell */
    public boolean hasMonsterAt(int row, int col) {
        return monsterTokens.stream().anyMatch(t -> t.getRow() == row && t.getCol() == col);
    }

    /** Check whether a hero occupies a cell */
    public boolean hasHeroAt(int row, int col) {
        return heroTokens.stream().anyMatch(t -> t.getRow() == row && t.getCol() == col);
    }

    /**
     * Find all monsters within Manhattan range of a given position.
     */
    public List<Monster> getMonstersInRange(Positionable center, int range) {
        List<Monster> inRange = new ArrayList<>();
        for (UnitToken mToken : monsterTokens) {
            int dr = Math.abs(mToken.getRow() - center.getRow());
            int dc = Math.abs(mToken.getCol() - center.getCol());
            if (dr <= range && dc <= range) {
                inRange.add((Monster) mToken.getOccupant());
            }
        }
        return inRange;
    }

    /**
     * Check if any monster is in the same row and adjacent column.
     */
    public boolean hasMonsterInSameRow(Positionable center) {
        for (UnitToken m : monsterTokens) {
            if (m.getRow() == center.getRow() && Math.abs(m.getCol() - center.getCol()) <= 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convert a lane ID to the representative column index.
     */
    public int laneToColumn(Lane l) {
        switch (l.getId()) {
            case 0: return 0;
            case 1: return 3;
            default: return 6;
        }
    }

    /** Spawn a monster at (row, col) */
    public void spawnMonster(Monster m, int row, int col) {
        monsterTokens.add(new UnitToken(m, row, col));
    }

    /** Spawn a hero at (row, col) */
    public void spawnHero(Hero h, int row, int col) {
        heroTokens.add(new UnitToken(h, row, col));
    }

    /**
     * Respawn a hero back to their original spawn position.
     */
    public void respawn(Entity h) {
        for (UnitToken ut : heroTokens) {
            if (ut.getOccupant().equals(h)) {
                move(h, ut.getSpawnRow(), ut.getSpawnCol());
            }
        }
    }

    /**
     * Get the original spawn token for a hero.
     */
    public UnitToken getSpawnPosition(Hero h) {
        return heroTokens.stream()
                .filter(t -> t.getOccupant().equals(h))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No spawn position found for hero: " + h));
    }

    /**
     * Move an entity (hero or monster) to the given position.
     * The space may do some effect on the entity.
     */
    public boolean move(Entity p, int row, int col) {
        Space old_s = grids[p.getRow()][p.getCol()];
        Space new_s = grids[row][col];
        if(!new_s.canEnter()){
            GameUI.println("The space is not accessible!");
            return false;
        }
        if(p instanceof Hero){
            old_s.onLeave((Hero)p);
            for (UnitToken t : heroTokens) {
                if (t.getOccupant().equals(p)) {
                    t.setPosition(row, col);
                    new_s.onEnter((Hero)p);
                    return true;
                }
            }
        }
        for (UnitToken t : monsterTokens) {
            if (t.getOccupant().equals(p)) {
                t.setPosition(row, col);
                return true;
            }
        }
        return false;
    }

    // -------------------------------
    // Utility and Accessors
    // -------------------------------

    @Override
    public Space getSpace(int row, int col) {
        return grids[row][col];
    }

    @Override
    public void setSpace(int row, int col, Space space) {
        grids[row][col] = space;
    }

    public List<Lane> getAllLanes() {
        return allLanes;
    }

    /**
     * Find an adjacent hero (up/down/left/right) from a given position.
     */
    public Hero findAdjacentHero(Positionable p) {
        for (UnitToken h : heroTokens) {
            Hero hero = (Hero) h.getOccupant();
            if (hero.getStats().getHealth() <= 0) continue;
            int dr = Math.abs(h.getRow() - p.getRow());
            int dc = Math.abs(h.getCol() - p.getCol());
            if (dr + dc == 1) return hero;
        }
        return null;
    }

    /**
     * Randomly generate a space type within a lane.
     */
    private Space generateRandomLaneSpace(int r, int c) {
        Random rand = new Random();
        int n = rand.nextInt(10);
        switch (n) {
            case 3: return new KoulouSpace("Koulou", r, c);
            case 5: return new CaveSpace("Cave", r, c);
            case 7: return new BushSpace("Bush", r, c);
            case 9: return new ObstacleSpace("Obstacle", r, c);
            default: return new PlainSpace("Plain", r, c);
        }
    }

    public Space getSpaceAt(int row, int col) {
        return grids[col][row];
    }

    private String repeat(String s, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Build the hero market inventory using seeded item data.
     */
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
        if (items == null) return;
        for (Item item : items) {
            inv.addItem(item);
        }
    }

    public Market getMarket() {
        return heroNexusMarket;
    }

    public Party getHeroes() {
        return heroes;
    }

    /**
     * Remove a monster from the arena (usually when dead or off-map).
     */
    public void removeMonster(Monster m) {
        monsters.remove(m);
        monsterTokens.removeIf(t -> t.getOccupant().equals(m));
    }
}
