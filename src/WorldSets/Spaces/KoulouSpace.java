package WorldSets.Spaces;

import WorldSets.Space;

public class KoulouSpace extends Space {

    public KoulouSpace(String name, int row, int col){
        super(name,row,col);


    }

    @Override
    public boolean canEnter() {
        return true;
    }
}
