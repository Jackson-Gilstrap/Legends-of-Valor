package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

import java.util.Random;

public class HeroVsMonsterHitChance implements HeroHitChanceStrategy {
    private final Random random;

    public HeroVsMonsterHitChance(Random random) {
        this.random = random;
    }

    @Override
    public boolean hits(Hero hero, Monster monster) {
        double monsterDodge = monster.getStats().getAgility();
        double hittingChance = 1 - monsterDodge;
        double roll = random.nextDouble();
        return roll <= hittingChance;
    }
}
