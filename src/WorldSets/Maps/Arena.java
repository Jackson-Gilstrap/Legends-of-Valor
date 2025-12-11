package WorldSets.Maps;

import WorldSets.MapSet;
import WorldSets.Market;
import WorldSets.Space;
import WorldSets.Spaces.NexusSpace;
import WorldSets.Spaces.PlainSpace;
import WorldSets.Spaces.WallSpace;

import java.util.ArrayList;
import java.util.List;

import Items.Armor;
import Items.Potion;
import Items.Spell;
import Items.Weapon;
import Seeders.ItemSeeder;
import Utility.Inventory;

public class Arena extends MapSet {
    private final Space[][] arena;


    // items to build a market
    private Market globalMarket;
    private ItemSeeder item_seeder;
    private List<Weapon> weapons = new ArrayList<>();
    private List<Armor> armors = new ArrayList<>();
    private List<Spell> spells = new ArrayList<>();
    private List<Potion> potions = new ArrayList<>();

    public Arena(int rows, int cols) {
        super(rows, cols);
        this.arena = new Space[cols][rows];
        build();
    }

    @Override
    public Space getSpace(int row, int col) {
        return arena[row][col];
    }

    protected void build() {
        globalMarket = buildMarket();
        for (int r = 0; r < getRows() ; r++) {
            for (int c = 0; c < getCols(); c++) {

                //print the Monster Nexus
                if(c == 2 || c == 5) {
                    arena[c][r] = new WallSpace("Wall", r, c);
                    continue;
                }
                if(r == 0) {
                    arena[c][r] = new NexusSpace("Nexus", r, c, globalMarket, NexusSpace.NexusType.MONSTER);
                    continue;
                }

                if(r == getRows() - 1) {
                    arena[c][r] = new NexusSpace("Nexus",r,c, globalMarket, NexusSpace.NexusType.HERO);
                    continue;
                }


                arena[c][r] = generateRandomLaneSpace(r,c);
            }

        }

    }

    private Space generateRandomLaneSpace(int r,int c) {
        //return random but test with plain
        return new PlainSpace("Plain",r,c);
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        int spaceheight = 3;

        for (int r = 0; r < getRows(); r++) {

            List<StringBuilder> rowBuilders = new ArrayList<>();
            for (int i = 0; i < spaceheight; i++) {
                rowBuilders.add(new StringBuilder());
            }

            for(int c =0; c < getCols(); c++) {
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


    public Space getSpaceAt(int row, int col) {return arena[col][row];}

    /**
     * Build a global market in the Nexus.
     * @return the global market
     */
    private Market buildMarket(){
        loadMarketData();
        Inventory inventory = new Inventory();
        for (Weapon w : weapons) inventory.addItem(w);
        for (Armor a : armors) inventory.addItem(a);
        for (Spell s : spells) inventory.addItem(s);
        for (Potion p : potions) inventory.addItem(p);

        return new Market(inventory);
    }

    /**
     * Load the item data from the txt.
     */
    private void loadMarketData() {
        List<Weapon> weapon_data = item_seeder.seedWeapons("src/TextFiles/Weaponry.txt");
        for( Weapon weapon: weapon_data ) {
            weapons.add(weapon);
        }
        List<Armor> armor_data = item_seeder.seedArmors("src/TextFiles/Armory.txt");
        for( Armor armor: armor_data ) {
            armors.add(armor);
        }

        String[] files =  {"src/TextFiles/FireSpells.txt","src/TextFiles/IceSpells.txt", "src/TextFiles/LightningSpells.txt"};
        for (String file : files) {
            List<Spell> spell_data = item_seeder.seedSpells(file);
            for( Spell spell: spell_data ) {
                spells.add(spell);
            }
        }

        List<Potion> potion_data = item_seeder.seedPotions("src/TextFiles/Potions.txt");
        for( Potion potion: potion_data ) {
            potions.add(potion);
        }

    }
}
