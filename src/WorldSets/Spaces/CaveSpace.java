package WorldSets.Spaces;

import Entities.Hero;
import Utility.Color;
import WorldSets.Space;

public class CaveSpace extends Space {
    private int buffAmount = 0;
    public CaveSpace(String name, int row, int col){
        super(name,row,col);
    }

    @Override
    public boolean canEnter() {
        return true;
    }

    /**
     * When a hero enters a Cave space, the space will increase his agility ny 10%.
     */
    @Override
    public void onEnter(Hero h) {
        buffAmount = (int) (0.1*h.getStats().getAgility());
        h.getStats().setAgility(h.getStats().getAgility()+buffAmount);
    }

    @Override
    public void onLeave(Hero h) {
        h.getStats().setAgility(h.getStats().getAgility()-buffAmount);
    }

    @Override
    protected Color colorForSpace() {
            return Color.BLUE;

    }

    @Override
    public String bgCodeForSpace() {
        return Color.WHITE.getBgAnsiCode();
    }


}
