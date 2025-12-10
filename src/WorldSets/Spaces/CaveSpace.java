package World.Spaces;

import World.Space;

public class CaveSpace extends Space {

    public CaveSpace(String name, int row, int col){
        super(name,row,col);
    }

    @Override
    public boolean canEnter() {
        return true;
    }

}
