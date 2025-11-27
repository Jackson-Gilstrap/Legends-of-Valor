package World;

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
import World.TileTypes.BlockingTile;
import World.TileTypes.CommonTile;
import World.TileTypes.MarketTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TileMap {

    private final String tile_map_name;
    private final int rows;
    private final int cols;

    private final Tile[][] tile_map;
    private final Party player_party;
    private int party_row;
    private int party_col;
    private List<Tile> markets = new ArrayList<>();
    private ItemSeeder item_seeder;
    private List<Weapon> weapons = new ArrayList<>();
    private List<Armor> armors = new ArrayList<>();
    private List<Spell> spells = new ArrayList<>();
    private List<Potion> potions = new ArrayList<>();

    public TileMap(int rows, int cols, String tile_map_name, Party player_party) {
        this.tile_map = new Tile[rows][cols];
        this.tile_map_name = tile_map_name;
        this.rows = rows;
        this.cols = cols;

        this.player_party = player_party;
        this.item_seeder = new ItemSeeder(
                new WeaponFactory(),
                new ArmorFactory(),
                new SpellFactory(),
                new PotionFactory()
        );

        buildMap();
        spawnParty();
    }

    public String getTileMapName() {
        return tile_map_name;
    }
    public int getNumRows() {
        return rows;
    }
    public int getNumCols() {
        return cols;
    }
    public int getParty_row() {return party_row;}
    public int getParty_col() {return party_col;}
    public Party getPlayerParty() {return player_party;}
    public List<Tile> getMarkets() {return markets;}
    public Tile getTile(int row, int col) {return tile_map[row][col];}

    private int getRandomRow() {
        Random rand = new Random();
        return rand.nextInt(rows - 1);
    }
    private int getRandomCol() {
        Random rand = new Random();
        return rand.nextInt(cols - 1);
    }

    public boolean inBounds(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void spawnParty( ) {
        Random rand = new Random();

        while (true) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);

            // Must be a CommonTile
            if (tile_map[r][c] instanceof CommonTile) {
                this.party_row = r;
                this.party_col = c;
                return;
            }
        }
    }

    public void moveParty(int delta_row, int delta_col){

        int new_row = party_row + delta_row;
        int new_col = party_col + delta_col;

        if(delta_row != 0 && delta_col != 0){
            System.out.println("No diagonal moves");
        }

        if(!inBounds(new_row, new_col)){
            System.out.println("You stop in tracks and look over at the hazy fog with no ground in sight... ");
            System.out.println("You decide that its best not to proceed.");
        }

        if(tile_map[new_row][new_col] instanceof BlockingTile){
            System.out.println("There appears to be a massive rock formation blocking you path...");
            System.out.println("You imagine there is some way around it.");
        }

        if(tile_map[new_row][new_col] instanceof MarketTile) {
            System.out.println("You have reached a market... Press f to enter or t to traval to another market");
        }

        party_row = new_row;
        party_col = new_col;
    }


    public boolean fastTravel(int target_row, int target_col){
        if(!inBounds(target_row, target_col)){
            System.out.println("You look confused nothing happened...\nMaybe try to pick a location where land exists...");
            return false;
        }

        if(tile_map[target_row][target_col] instanceof MarketTile){
            party_row = target_row;
            party_col = target_col;
            System.out.println("You begin to see your body glow bright.");
            System.out.println("You wake up at a new location.");
            return true;
        }

        System.out.println("There doesn't seem to be a way to teleport there");
        return false;


    }

    private void buildMap() {
        loadMarketData();
        List<Market> mkt_objs = buildMarkets();
        List<Tile> tiles = new ArrayList<>();
        int total_tiles = rows * cols;
        for(int i = 0; i < 4; i++){
            tiles.add(new MarketTile("Market",-1,-1, mkt_objs.get(i)));
        }

        int remaining_tiles = total_tiles - 4;
        int num_blocking_tiles = (int)Math.round(remaining_tiles * 0.10);
        int num_common_tiles = remaining_tiles - num_blocking_tiles;
        for (int i = 0; i < num_blocking_tiles; i++) {
            tiles.add(new BlockingTile("Inaccessible", -1, -1));
        }
        for (int i = 0; i < num_common_tiles; i++) {
            tiles.add(new CommonTile("Common", -1, -1));
        }
        Collections.shuffle(tiles);

        int tiles_index = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){

                Tile tile = tiles.get(tiles_index++);

                tile.setPosition(row, col);

                tile_map[row][col] = tile;

                if(tile instanceof MarketTile){
                    markets.add(tile);
                }

            }
        }
    }

    public String render() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < rows; r++) {

            // Top border of the row
            sb.append(drawHorizontalBorder()).append('\n');

            sb.append("|");
            for (int c = 0; c < cols; c++) {

                if (r == party_row && c == party_col) {
                    sb.append(" P |");
                } else {
                    sb.append(" ").append(tile_map[r][c].getSymbol()).append(" |");
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
        for (int i = 0; i < cols; i++) {
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

        // Build Inventories
        Inventory weaponInv = new Inventory();
        for (Weapon w : weapons) weaponInv.addItem(w);

        Inventory armorInv = new Inventory();
        for (Armor a : armors) armorInv.addItem(a);

        Inventory spellInv = new Inventory();

        for (Spell s : spells) spellInv.addItem(s);

        Inventory potionInv = new Inventory();
        // add seeds later
        for (Potion p : potions) potionInv.addItem(p);

        markets.add(new Market(weaponInv));
        markets.add(new Market(armorInv));
        markets.add(new Market(spellInv));
        markets.add(new Market(potionInv));

        return markets;
    }


}
