package WorldSets.Spaces;

public class ObstacleSpace extends PlainSpace{

    public ObstacleSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return false;
    }

    // NEED TO REPRESENT AT A SINGLE X OR A FEW LESS THAN THE WALL

}
