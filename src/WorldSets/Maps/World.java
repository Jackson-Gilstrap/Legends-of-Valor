package WorldSets.Maps;

import Factories.ArmorFactory;
import Factories.PotionFactory;
import Factories.SpellFactory;
import Factories.WeaponFactory;
import Items.Armor;
import Items.Potion;
import Items.Spell;
import Items.Weapon;
import Parties.Party;
import Seeders.ItemSeeder;
import Utility.Inventory;
import WorldSets.MapSet;
import Market.Market;
import WorldSets.Space;
import WorldSets.Spaces.MarketSpace;
import WorldSets.Spaces.ObstacleSpace;
import WorldSets.Spaces.PlainSpace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// World.java
// represents the map in MvH
public class World extends MapSet {
    private final Space[][] world_map;
    private final Party player_party;
    private int party_row;
    private int party_col;
    private final List<Space> markets = new ArrayList<>();
    private final ItemSeeder item_seeder;
    private final List<Weapon> weapons = new ArrayList<>();
    private final List<Armor> armors = new ArrayList<>();
    private final List<Spell> spells = new ArrayList<>();
    private final List<Potion> potions = new ArrayList<>();

    public World(int rows, int cols, Party player_party) {
        super(rows, cols);
        this.world_map = new Space[rows][cols];


        this.player_party = player_party;
        this.item_seeder = new ItemSeeder(
                new WeaponFactory(),
                new ArmorFactory(),
                new SpellFactory(),
                new PotionFactory()
        );

        build();
        spawnParty();
    }

    public int getParty_row() {return party_row;}
    public int getParty_col() {return party_col;}
    public void setPartyPosition(int row, int col) {
        this.party_row = row;
        this.party_col = col;
    }

    public Party getPlayerParty() {return player_party;}
    public List<Space> getMarkets() {return markets;}
    public Space getSpace(int row, int col) {return world_map[row][col];}
    @Override
    public void setSpace(int row, int col, Space space) {
        world_map[row][col] = space;
    }



    private void spawnParty( ) {
        Random rand = new Random();

        while (true) {
            int r = rand.nextInt(super.getRows());
            int c = rand.nextInt(super.getCols());
            // only on a plain space
            if (world_map[r][c] instanceof PlainSpace) {
                this.party_row = r;
                this.party_col = c;
                return;
            }
        }
    }


    public boolean fastTravel(int target_row, int target_col){
        if(!inBounds(target_row, target_col)){
            System.out.println("You look confused nothing happened...\nMaybe try to pick a location where land exists...");
            return false;
        }

        if(world_map[target_row][target_col] instanceof MarketSpace){
            party_row = target_row;
            party_col = target_col;
            System.out.println("You begin to see your body glow bright.");
            System.out.println("You wake up at a new location.");
            return true;
        }

        System.out.println("There doesn't seem to be a way to teleport there");
        return false;


    }

    protected void build() {
        loadMarketData();
        List<Market> mkt_objs = buildMarkets();
        List<Space> spaces = new ArrayList<>();
        int total_tiles = super.getRows() * super.getCols();
        for(int i = 0; i < 4; i++){
            spaces.add(new MarketSpace("Market",-1,-1, mkt_objs.get(i)));
        }

        int remaining_tiles = total_tiles - 4;
        int num_blocking_tiles = (int)Math.round(remaining_tiles * 0.10);
        int num_common_tiles = remaining_tiles - num_blocking_tiles;
        for (int i = 0; i < num_blocking_tiles; i++) {
            spaces.add(new ObstacleSpace("Obstacle", -1, -1));
        }
        for (int i = 0; i < num_common_tiles; i++) {
            spaces.add(new PlainSpace("Plain", -1, -1));
        }
        Collections.shuffle(spaces);

        int tiles_index = 0;
        for(int row = 0; row < super.getRows(); row++){
            for(int col = 0; col < super.getCols(); col++){

                Space space = spaces.get(tiles_index++);

                space.setPosition(row, col);

                world_map[row][col] = space;

                if(space instanceof MarketSpace){
                    markets.add(space);
                }

            }
        }
    }

    public String render() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < super.getRows(); r++) {

            // Top border of the row
            sb.append(drawHorizontalBorder()).append('\n');

            sb.append("|");
            for (int c = 0; c < super.getCols(); c++) {

                if (r == party_row && c == party_col) {
                    sb.append(" P |");
                } else {
                    sb.append(" ").append(world_map[r][c].getSymbol()).append(" |");
                }
            }
            sb.append('\n');
        }

        // Bottom border after the last row
        sb.append(drawHorizontalBorder()).append('\n');

        return sb.toString();
    }


    private String drawHorizontalBorder() {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < super.getCols(); i++) {
            sb.append("---+");
        }
        return sb.toString();
    }






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
//

        }

        List<Potion> potion_data = item_seeder.seedPotions("src/TextFiles/Potions.txt");
        for( Potion potion: potion_data ) {
            potions.add(potion);
        }

    }

    private List<Market> buildMarkets() {
        List<Market> markets = new ArrayList<>();

        Inventory weaponInv = new Inventory();
        for (Weapon w : weapons) weaponInv.addItem(w);

        Inventory armorInv = new Inventory();
        for (Armor a : armors) armorInv.addItem(a);

        Inventory spellInv = new Inventory();

        for (Spell s : spells) spellInv.addItem(s);

        Inventory potionInv = new Inventory();
        for (Potion p : potions) potionInv.addItem(p);

        markets.add(new Market(weaponInv));
        markets.add(new Market(armorInv));
        markets.add(new Market(spellInv));
        markets.add(new Market(potionInv));

        return markets;
    }


}
