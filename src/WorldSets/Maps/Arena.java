package WorldSets.Maps;

import Factories.ArmorFactory;
import Factories.PotionFactory;
import Factories.SpellFactory;
import Factories.WeaponFactory;
import Items.Item;
import Seeders.ItemSeeder;
import Utility.Inventory;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Market;
import WorldSets.Space;
import WorldSets.Spaces.BushSpace;
import WorldSets.Spaces.CaveSpace;
import WorldSets.Spaces.KoulouSpace;
import WorldSets.Spaces.NexusSpace;
import WorldSets.Spaces.ObstacleSpace;
import WorldSets.Spaces.PlainSpace;
import WorldSets.Spaces.WallSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena extends MapSet {
    private final Space[][] arena;
    private final Market heroNexusMarket;
    private final Market monsterNexusMarket;

    public Arena(int rows, int cols) {
        super(rows, cols);
        this.arena = new Space[cols][rows];
        this.heroNexusMarket = new Market(buildMarketInventory());
        this.monsterNexusMarket = new Market(new Inventory());
        build();
    }

    @Override
    public Space getSpace(int row, int col) {
        return arena[col][row];
    }

    @Override
    public void setSpace(int row, int col, Space space) {
        arena[col][row] = space;
    }

    public String render(List<? extends Positionable> heroes, List<? extends Positionable> monsters) {
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
                Space space = arena[c][r];
                boolean heroHere = isOccupied(heroes, r, c);
                boolean monsterHere = isOccupied(monsters, r, c);
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

    @Override
    protected void build() {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {

                if (c == 2 || c == 5) {
                    arena[c][r] = new WallSpace("Wall", r, c);
                    continue;
                }

                if (r == 0) { // monster nexus
                    arena[c][r] = new NexusSpace("Nexus", r, c, monsterNexusMarket, NexusSpace.NexusType.MONSTER);
                    continue;
                }

                if (r == getRows() - 1) { // hero nexus
                    arena[c][r] = new NexusSpace("Nexus", r, c, heroNexusMarket, NexusSpace.NexusType.HERO);
                    continue;
                }

                arena[c][r] = generateRandomLaneSpace(r, c);
            }
        }
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

    public String render() {
        StringBuilder sb = new StringBuilder();
        int spaceheight = 3;

        for (int r = 0; r < getRows(); r++) {

            List<StringBuilder> rowBuilders = new ArrayList<StringBuilder>();
            for (int i = 0; i < spaceheight; i++) {
                rowBuilders.add(new StringBuilder());
            }

            for (int c = 0; c < getCols(); c++) {
                Space space = arena[c][r];
                List<String> lines = space.renderLines();

                for (int i = 0; i < spaceheight; i++) {
                    rowBuilders.get(i).append(lines.get(i)).append(" ");
                }
            }

            for (StringBuilder line : rowBuilders) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    public Space getSpaceAt(int row, int col) {
        return arena[col][row];
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
}
