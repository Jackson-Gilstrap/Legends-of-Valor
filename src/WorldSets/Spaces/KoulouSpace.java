package WorldSets.Spaces;

import Entities.Hero;
import WorldSets.Space;

public class KoulouSpace extends Space {
    private int buffAmount = 0;
    public KoulouSpace(String name, int row, int col){
        super(name,row,col);
    }

    @Override
    public boolean canEnter() {
        return true;
    }

    /**
     * When a hero enters a Koulou space, the space will increase his strength ny 10%.
     */
    @Override
    public void onEnter(Hero h) {
        buffAmount = (int)(0.1*h.getStats().getAttack());
        h.getStats().setAttack(h.getStats().getAttack()+buffAmount);
    }

    @Override
    public void onLeave(Hero h) {
        h.getStats().setAttack(h.getStats().getAttack()-buffAmount);
    }
}
