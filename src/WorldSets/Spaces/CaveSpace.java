package WorldSets.Spaces;

import WorldSets.Space;

public class CaveSpace extends Space {

    public CaveSpace(String name, int row, int col){
        super(name,row,col);
    }

    @Override
    public boolean canEnter() {
        return true;
    }

}
