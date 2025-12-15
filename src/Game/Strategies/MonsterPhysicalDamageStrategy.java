package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

public class MonsterPhysicalDamageStrategy implements MonsterDamageStrategy {
    @Override
    public int calculateDamage(Monster monster, Hero hero) {
        int monAttack = monster.getStats().getAttack();
        double totalHeroDr = hero.getStats().getDamage_reduction() + hero.getJacket().getBuffStats().getDamage_reduction();
        return monAttack - (int) (monAttack * totalHeroDr);
    }
}
