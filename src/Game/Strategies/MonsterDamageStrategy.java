package Game.Strategies;

import Entities.Hero;
import Entities.Monster;

public interface MonsterDamageStrategy {
    int calculateDamage(Monster monster, Hero hero);
}
