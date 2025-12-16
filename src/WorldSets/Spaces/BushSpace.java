package WorldSets.Spaces;

import Entities.Hero;
import Game.GameUI;
import Utility.Color;
import WorldSets.Space;

// BushSpace.Java
// represents a special space in the arena that enhances the Dexterity of any hero on the space
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
     * When a hero enters a Bush space, increase Dexterity by 10%.
     */
    @Override
    public void onEnter(Hero h) {
        int originalDex = h.getStats().getDexterity();
        buffAmount = (int) (0.1 * originalDex);
        h.getStats().setDexterity(originalDex + buffAmount);
        String message = String.format(
                "%s enters %s. Dexterity increased by %d → %d",
                h.getName(), getName(), buffAmount, h.getStats().getDexterity()
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
            int newDex = h.getStats().getDexterity() - buffAmount;
            h.getStats().setDexterity(newDex);

            String message = String.format(
                    "%s leaves %s. Dexterity buff removed → %d",
                    h.getName(), getName(), newDex
            );
            System.out.println(Color.colorize(message, colorForSpace()));

            buffAmount = 0; // 防止重复减属性
            GameUI.sleep();

        }
    }

    /**
     * Foreground color used for rendering Bush space.
     */
    @Override
    protected Color colorForSpace() {
        return Color.GREEN;
    }

    /**
     * Background color ANSI code for Bush space.
     */
    @Override
    public String bgCodeForSpace() {
        return Color.WHITE.getBgAnsiCode();
    }
}
