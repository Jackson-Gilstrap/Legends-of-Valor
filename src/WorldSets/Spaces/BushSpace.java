package World.Spaces;

import World.Space;

public class BushSpace extends Space {

    public BushSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return true;
    }

}
