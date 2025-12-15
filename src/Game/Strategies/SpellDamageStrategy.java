package Game.Strategies;

import Entities.Hero;
import Entities.Monster;
import Items.Spell;

public class SpellDamageStrategy implements HeroDamageStrategy {
    @Override
    public int calculateDamage(Hero hero, Monster monster) {
        if (!hero.hasSpellEquipped()) {
            return 0;
        }
        Spell spell = hero.getJacket().getSpell();
        int heroAtk = hero.getStats().getDexterity() + hero.getJacket().getBuffStats().getDexterity();
        int spellDmg = spell.getDamage();
        return heroAtk + spellDmg;
    }
}
