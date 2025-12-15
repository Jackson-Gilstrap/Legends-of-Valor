package WorldSets.Spaces;

import Entities.Hero;
import Game.GameUI;
import Utility.Color;
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
     * When a hero enters a Bush space, increase Dexterity by 10%.
     */
    @Override
    public void onEnter(Hero h) {
        int originalDex = h.getStats().getAttack();
        buffAmount = (int) (0.1 * originalDex);
        h.getStats().setAttack(originalDex + buffAmount);

        String message = String.format(
                "%s enters %s. Attack increased by %d → %d",
                h.getName(), getName(), buffAmount, h.getStats().getAttack()
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
            int newDex = h.getStats().getAttack() - buffAmount;
            h.getStats().setAttack(newDex);

            String message = String.format(
                    "%s leaves %s. Attack buff removed → %d",
                    h.getName(), getName(), newDex
            );
            System.out.println(Color.colorize(message, colorForSpace()));
        GameUI.sleep();

            buffAmount = 0; // 防止重复减属性
        }
    }

    @Override
    protected Color colorForSpace() {
            return Color.YELLOW;

    }

    @Override
    public String bgCodeForSpace() {
        return Color.YELLOW.getBgAnsiCode();
    }

}
