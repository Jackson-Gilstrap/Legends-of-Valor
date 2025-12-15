package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

public interface HeroDamageStrategy {
    int calculateDamage(Hero hero, Monster monster);
}
