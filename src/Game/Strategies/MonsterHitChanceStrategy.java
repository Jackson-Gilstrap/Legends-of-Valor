package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

public interface MonsterHitChanceStrategy {
    boolean hits(Monster monster, Hero hero);
}
