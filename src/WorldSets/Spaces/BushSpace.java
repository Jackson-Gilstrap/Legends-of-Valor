package WorldSets.Spaces;

import Entities.Hero;
import WorldSets.Space;

public class BushSpace extends Space {
    private int buffAmount = 0;

    public BushSpace(String name, int row, int col) {
        super(name, row, col);

    }

    @Override
    public boolean canEnter() {
        return true;
    }

    /**
     * When a hero enters a Bush space, the space will increase his dexeterity ny 10%.
     */
    @Override
    public void onEnter(Hero h) {
        buffAmount = (int)(0.1*h.getStats().getDexterity());
        h.getStats().setDexterity(h.getStats().getDexterity()+buffAmount);
    }

    @Override
    public void onLeave(Hero h) {
        h.getStats().setDexterity(h.getStats().getDexterity()-buffAmount);
    }
}
