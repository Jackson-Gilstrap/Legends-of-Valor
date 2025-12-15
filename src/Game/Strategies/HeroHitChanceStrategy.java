package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

public interface HeroHitChanceStrategy {
    boolean hits(Hero hero, Monster monster);
}
