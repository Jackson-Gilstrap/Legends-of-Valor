package World.Spaces;

public class ObstacleSpace extends PlainSpace{

    public ObstacleSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return false;
    }

}
