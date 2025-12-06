package World;

public class PlainSpace extends Space{

    public PlainSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return true;
    }
}
