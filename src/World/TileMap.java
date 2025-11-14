package World;

import Player.Party;

import java.util.ArrayList;
import java.util.Random;

public class TileMap {
    /*
    The tile map consists will be a 2d array of Tiles of the various Types Common, Blocking, Market
    Testing map size will be 5x5 so expect rebalancing
    The map should have ~50,20,30 split
    The map should know what tile is in what space on the map and based on that provide interactions

     */
    private final int num_rows;
    private final int num_cols;
    private final String tile_map_name;
    private final Tile[][] tile_map;
    private final Party player_party;
    private ArrayList<Integer> x_positions;
    private ArrayList<Integer> y_positions;

//    public TileMap(int num_rows, int num_cols) {}


    public TileMap(int num_rows, int num_cols, String tile_map_name) {
        this.tile_map = new Tile[num_rows][num_cols];
        this.tile_map_name = tile_map_name;
        this.num_rows = num_rows;
        this.num_cols = num_cols;

        buildMap();
        this.player_party = spawnParty();
    }

    public String getTileMapName() {
        return tile_map_name;
    }
    public int getNumRows() {
        return num_rows;
    }
    public int getNumCols() {
        return num_cols;
    }

    public String getPartyPosition() {
        return "You are currently at " + player_party.getXPosition() + ", " + player_party.getYPosition() + " on the map";
    }

    private void buildMap() {
        for (int row = 0; row < tile_map.length; row++) {
            for (int col = 0; col < tile_map[row].length; col++) {


                double r = Math.random();
                if (r < 0.15) {
                    tile_map[row][col] = new BlockingTile("Blocking Tile", row, col);
                } else if (r < 0.2) {
                    tile_map[row][col] = new MarketTile("Market Tile",row ,col);
                } else {
                    tile_map[row][col] = new CommonTile("Common Tile", row, col);
                }
            }
        }
    }

    // This method is responsible for placing the player_party down on the map in a valid spot
    // Non blocking and no market spaces
    public Party spawnParty() {
        /* First we should loop through the tilemap and see what tiles are vaild tiles
        Then store them in a temp array
        pick a random Position from the array
        and then call get x and get y as the parameters of the new player party
         */
        x_positions = new ArrayList<>();
        y_positions = new ArrayList<>();
        for (int i = 0; i < num_rows; i++) {
            for (int j = 0; j < num_cols; j++) {
                if(tile_map[i][j].getName().equals("Common Tile")) {
                    int xPos = tile_map[i][j].getXPosition();
                    int yPos = tile_map[i][j].getYPosition();
                    x_positions.add(xPos);
                    y_positions.add(yPos);
                }
            }
        }

        int random_index = getRandomIndex(x_positions.size() - 1);
        int random_x;
        int random_y;
        random_x = x_positions.get(random_index);
        random_y = y_positions.get(random_index);
        Party party =  new Party(random_x, random_y);
        Tile spawn_tile = tile_map[random_x][random_y];
        spawn_tile.addOverlay("P");

        System.out.println("Spawning party at " + random_x + "," + random_y);
        return party;

    }

    int getRandomIndex(int ceiling) {
        Random r = new Random();
        return r.nextInt(ceiling);
    }


    public String render() {
        StringBuilder sb = new StringBuilder();

        // Top border
        sb.append(drawHorizontalBorder()).append('\n');

        for (int x = 0; x < getNumRows(); x++) {
            sb.append("|");
            for (int y = 0; y < getNumCols(); y++) {
                sb.append(" ").append(tile_map[x][y].symbolRepresentation()).append(" |");
            }
            sb.append('\n');
            sb.append(drawHorizontalBorder()).append('\n');
        }

        return sb.toString();
    }

    private String drawHorizontalBorder() {
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < getNumRows(); i++) border.append("+---");
        border.append("+");
        return border.toString();
    }
}
