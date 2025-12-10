package WorldSets.Spaces;

import WorldSets.Space;

public class BushSpace extends Space {

    public BushSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return true;
    }

}
