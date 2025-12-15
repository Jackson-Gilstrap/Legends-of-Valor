package Game.Actions;

import Entities.Hero;
import Enums.PotionType;
import Game.BattleView;
import Items.Potion;

public class UsePotionAction implements BattleAction {
    private final Hero hero;
    private final BattleView view;

    public UsePotionAction(Hero hero, BattleView view) {
        this.hero = hero;
        this.view = view;
    }

    @Override
    public boolean canExecute() {
        if (!hero.hasPotionEquipped()) {
            return false;
        }
        Potion potion = hero.getJacket().getPotion();
        return potion != null && !potion.isConsumed();
    }

    @Override
    public void execute() {
        if (!canExecute()) {
            view.printPotionUnavailable(hero);
            return;
        }
        Potion potion = hero.getJacket().getPotion();
        PotionType type = potion.getType();
        potion.setEffectAmount(hero.getLevelObj().getCurrentLevel(), type);
        double effect = potion.getEffectAmount();
        switch (type) {
            case HP:
                hero.getStats().setHealth(hero.getStats().getHealth() + (int) effect);
                break;
            case MP:
                hero.getStats().setMana(hero.getStats().getMana() + (int) effect);
                break;
            case Attack:
                hero.getStats().setAttack(hero.getStats().getAttack() + (int) effect);
                break;
            case Dexterity:
                hero.getStats().setDexterity(hero.getStats().getDexterity() + (int) effect);
                break;
            case Agility:
                hero.getStats().setAgility(hero.getStats().getAgility() + effect);
                break;
        }
        potion.consume();
        view.printPotionUse(hero, potion);
    }

    @Override
    public String label() {
        return "Use potion";
    }
}
