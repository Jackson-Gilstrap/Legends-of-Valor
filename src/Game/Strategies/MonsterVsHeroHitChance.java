package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

import java.util.Random;

public class MonsterVsHeroHitChance implements MonsterHitChanceStrategy {
    private final Random random;

    public MonsterVsHeroHitChance(Random random) {
        this.random = random;
    }

    @Override
    public boolean hits(Monster monster, Hero hero) {
        double heroDodge = hero.getStats().getAgility() + hero.getJacket().getBuffStats().getAgility();
        double hittingChance = 1 - heroDodge;
        double roll = random.nextDouble();
        return roll <= hittingChance;
    }
}
