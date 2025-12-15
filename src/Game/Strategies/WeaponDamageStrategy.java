package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

public class WeaponDamageStrategy implements HeroDamageStrategy {
    @Override
    public int calculateDamage(Hero hero, Monster monster) {
        int heroAtk = hero.getStats().getAttack() + hero.getJacket().getBuffStats().getAttack();
        double monsterDr = monster.getStats().getDamage_reduction();
        return heroAtk - (int) (heroAtk * monsterDr);
    }
}
