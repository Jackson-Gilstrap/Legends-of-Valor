package World;

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


    public TileMap(int num_rows, int num_cols, String tile_map_name) {
        this.tile_map = new Tile[num_rows][num_cols];
        this.tile_map_name = tile_map_name;
        this.num_rows = num_rows;
        this.num_cols = num_cols;

        buildMap();
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

    private void buildMap() {
        for (int row = 0; row < tile_map.length; row++) {
            for (int col = 0; col < tile_map[row].length; col++) {


                double r = Math.random();
                if (r < 0.1) {
                    tile_map[row][col] = new BlockingTile("Blocking Tile", row, col);
                } else if (r < 0.15) {
                    tile_map[row][col] = new MarketTile("Market Tile", row ,col);
                } else {
                    tile_map[row][col] = new CommonTile("Common Tile", row, col);
                }
            }
        }
    }


    public String render() {
        StringBuilder sb = new StringBuilder();

        // Top border
        sb.append(drawHorizontalBorder()).append('\n');

        for (int y = 0; y < getNumCols(); y++) {
            sb.append("|");
            for (int x = 0; x < getNumRows(); x++) {
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
