package WorldSets.Spaces;

import Entities.Hero;
import Game.GameUI;
import Utility.Color;
import WorldSets.Space;

public class CaveSpace extends Space {
    private double buffAmount = 0;
    public CaveSpace(String name, int row, int col){
        super(name,row,col);
    }

    @Override
    public boolean canEnter() {
        return true;
    }

        /**
     * When a hero enters a Bush space, increase Dexterity by 10%.
     */
    @Override
    public void onEnter(Hero h) {
        double originalDex = h.getStats().getAgility();
        buffAmount = (0.1 * originalDex);
        h.getStats().setAgility(originalDex + buffAmount);

        String message = String.format(
                "%s enters %s. Agility increased by %f → %f",
                h.getName(), getName(), buffAmount, h.getStats().getAgility()
        );
        System.out.println(Color.colorize(message, colorForSpace()));
        GameUI.sleep();

    }

    /**
     * When a hero leaves the Bush space, remove the Dexterity buff.
     */
    @Override
    public void onLeave(Hero h) {
        if (buffAmount > 0) {
            double newDex = h.getStats().getAgility() - buffAmount;
            h.getStats().setAgility(newDex);

            String message = String.format(
                    "%s leaves %s. Agility buff removed → %f",
                    h.getName(), getName(), newDex
            );
            System.out.println(Color.colorize(message, colorForSpace()));
        GameUI.sleep();

            buffAmount = 0.0; // 防止重复减属性
        }
    }

    @Override
    protected Color colorForSpace() {
            return Color.BLUE;

    }

    @Override
    public String bgCodeForSpace() {
        return Color.BLUE.getBgAnsiCode();
    }


}
