package Game.Actions;

import Entities.Hero;
import Entities.Monster;
import Game.BattleView;
import Game.Strategies.HeroDamageStrategy;
import Game.Strategies.HeroHitChanceStrategy;
import Parties.MonsterParty;

import java.util.List;

public class AttackWithWeaponAction implements TargetedAction {
    private final Hero hero;
    private final MonsterParty monsterParty;
    private final HeroDamageStrategy damageStrategy;
    private final HeroHitChanceStrategy hitChanceStrategy;
    private final BattleView view;
    private Integer targetIndex;

    public AttackWithWeaponAction(Hero hero,
                                  MonsterParty monsterParty,
                                  HeroDamageStrategy damageStrategy,
                                  HeroHitChanceStrategy hitChanceStrategy,
                                  BattleView view) {
        this.hero = hero;
        this.monsterParty = monsterParty;
        this.damageStrategy = damageStrategy;
        this.hitChanceStrategy = hitChanceStrategy;
        this.view = view;
    }

    @Override
    public boolean canExecute() {
        List<Monster> alive = monsterParty.getAliveMonsters();
        return targetIndex != null && targetIndex >= 0 && targetIndex < alive.size();
    }

    @Override
    public void execute() {
        if (!canExecute()) {
            view.printInvalidTarget(hero);
            return;
        }
        Monster target = monsterParty.getAliveMonsters().get(targetIndex);
        if (hitChanceStrategy.hits(hero, target)) {
            int damage = damageStrategy.calculateDamage(hero, target);
            target.getStats().setHealth(target.getStats().getHealth() - damage);
            view.printWeaponAttack(hero, target, damage);
        } else {
            view.printAttackMiss(hero, target);
        }
    }

    @Override
    public String label() {
        return "Attack with weapon";
    }

    @Override
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
