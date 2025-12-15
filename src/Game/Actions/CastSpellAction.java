package Game.Actions;

import Entities.Hero;
import Entities.Monster;
import Game.BattleView;
import Game.Strategies.HeroDamageStrategy;
import Items.Spell;
import Parties.MonsterParty;

import java.util.List;

public class CastSpellAction implements TargetedAction {
    private final Hero hero;
    private final MonsterParty monsterParty;
    private final HeroDamageStrategy spellDamageStrategy;
    private final BattleView view;
    private Integer targetIndex;

    public CastSpellAction(Hero hero,
                           MonsterParty monsterParty,
                           HeroDamageStrategy spellDamageStrategy,
                           BattleView view) {
        this.hero = hero;
        this.monsterParty = monsterParty;
        this.spellDamageStrategy = spellDamageStrategy;
        this.view = view;
    }

    @Override
    public boolean canExecute() {
        List<Monster> alive = monsterParty.getAliveMonsters();
        if (targetIndex == null || targetIndex < 0 || targetIndex >= alive.size()) {
            return false;
        }
        if (!hero.hasSpellEquipped()) {
            return false;
        }
        Spell spell = hero.getJacket().getSpell();
        return spell != null && hero.getStats().getMana() >= spell.getManaCost();
    }

    @Override
    public void execute() {
        if (!canExecute()) {
            view.printSpellUnavailable(hero);
            return;
        }
        Monster target = monsterParty.getAliveMonsters().get(targetIndex);
        Spell spell = hero.getJacket().getSpell();
        int manaCost = spell.getManaCost();
        int damage = spellDamageStrategy.calculateDamage(hero, target);
        hero.getStats().setMana(hero.getStats().getMana() - manaCost);
        target.getStats().setHealth(target.getStats().getHealth() - damage);
        view.printSpellAttack(hero, target, spell, damage);
        applyDebuff(spell, target);
    }

    private void applyDebuff(Spell spell, Monster target) {
        double spellDebuff = spell.getType().debuffMultiplier(spell.getLevel());
        switch (spell.getType()) {
            case FIRE:
                target.getStats().setDamage_reduction(target.getStats().getDamage_reduction() - spellDebuff);
                view.printSpellDebuff(target, "defence");
                break;
            case ICE:
                target.getStats().setAttack(target.getStats().getAttack() - (int) (target.getStats().getAttack() * spellDebuff));
                view.printSpellDebuff(target, "attack");
                break;
            case LIGHTNING:
                target.getStats().setAgility(target.getStats().getAgility() - spellDebuff);
                view.printSpellDebuff(target, "agility");
                break;
        }
    }

    @Override
    public String label() {
        return "Cast equipped spell";
    }

    @Override
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
