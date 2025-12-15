package Game.Services;

import Entities.Hero;
import Parties.Party;

/**
 * Handles between-turn regeneration.
 */
public class RegenerationService {
    public void regenerate(Party party) {
        for (Hero fighter : party.getAliveHeroes()) {
            if (fighter.getStats().getHealth() > 0) {
                int regenHealth = (int) (fighter.getStats().getHealth() * .10);
                int regenMana = (int) (fighter.getStats().getMana() * .10);
                int currentHealth = fighter.getStats().getHealth();
                int currentMana = fighter.getStats().getMana();
                fighter.getStats().setHealth(currentHealth + regenHealth);
                fighter.getStats().setMana(currentMana + regenMana);
            }
        }
    }
}
