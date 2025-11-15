package World;

import Player.Party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TileMap {
    /*
    The tile map consists will be a 2d array of Tiles of the various Types Common, Blocking, Market
    Testing map size will be 5x5 so expect rebalancing
    The map should have ~50,20,30 split
    The map should know what tile is in what space on the map and based on that provide interactions

     */
    private final String tile_map_name;
    private final int rows;
    private final int cols;

    private final Tile[][] tile_map;
    private final Party player_party;
    private int party_row;
    private int party_col;
    private ArrayList<Tile> markets = new ArrayList<>();

    public TileMap(int rows, int cols, String tile_map_name, Party player_party) {
        this.tile_map = new Tile[rows][cols];
        this.tile_map_name = tile_map_name;
        this.rows = rows;
        this.cols = cols;

        this.player_party = player_party;

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
    public ArrayList<Tile> getMarkets() {return markets;}
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
        List<Tile> tiles = new ArrayList<>();
        int total_tiles = rows * cols;
        for(int i = 0; i < 4; i++){
            tiles.add(new MarketTile("Market",-1,-1));
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

                if(tile instanceof BlockingTile){
                    tile_map[row][col] = new BlockingTile("Inaccessible", row, col);
                }
                else if(tile instanceof MarketTile){
                    tile_map[row][col] = new MarketTile("Market", row, col);
                    markets.add(tile_map[row][col]);
                }
                else{
                    tile_map[row][col] = new CommonTile("Common", row, col);
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



}
