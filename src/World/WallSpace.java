package World;

public class WallSpace extends Space{
    /**
     *
     * @param name
     * @param row
     * @param col
     */
    public WallSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
