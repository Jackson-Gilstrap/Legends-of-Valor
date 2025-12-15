package Game;

import Entities.Hero;
import Entities.Monster;
import Entities.MonsterPool;
import Game.Actions.AttackWithWeaponAction;
import Game.Actions.BattleAction;
import Game.Actions.CastSpellAction;
import Game.Actions.UsePotionAction;
import Game.Services.RegenerationService;
import Game.Services.RewardDistributor;
import Game.Strategies.HeroDamageStrategy;
import Game.Strategies.HeroHitChanceStrategy;
import Game.Strategies.HeroVsMonsterHitChance;
import Game.Strategies.MonsterDamageStrategy;
import Game.Strategies.MonsterHitChanceStrategy;
import Game.Strategies.MonsterPhysicalDamageStrategy;
import Game.Strategies.MonsterVsHeroHitChance;
import Game.Strategies.SpellDamageStrategy;
import Game.Strategies.WeaponDamageStrategy;
import Parties.MonsterParty;
import Parties.Party;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {
    private final Party playerParty;
    private final MonsterParty monsterParty;
    private final GameUI ui;
    private final BattleView view;
    private final BattleActionPresenter presenter;
    private final RewardDistributor rewardDistributor;
    private final RegenerationService regenerationService;
    private final HeroDamageStrategy weaponDamageStrategy;
    private final HeroDamageStrategy spellDamageStrategy;
    private final HeroHitChanceStrategy heroHitChanceStrategy;
    private final MonsterDamageStrategy monsterDamageStrategy;
    private final MonsterHitChanceStrategy monsterHitChanceStrategy;
    private final Random random;

    public Battle(Party playerParty) {
        this(playerParty, new GameUI(), new Random());
    }

    public Battle(Party playerParty, GameUI ui, Random random) {
        this.playerParty = playerParty;
        this.monsterParty = new MonsterParty();
        this.ui = ui;
        this.view = new BattleView();
        this.presenter = new BattleActionPresenter(ui, view);
        this.rewardDistributor = new RewardDistributor();
        this.regenerationService = new RegenerationService();
        this.weaponDamageStrategy = new WeaponDamageStrategy();
        this.spellDamageStrategy = new SpellDamageStrategy();
        this.heroHitChanceStrategy = new HeroVsMonsterHitChance(random);
        this.monsterDamageStrategy = new MonsterPhysicalDamageStrategy();
        this.monsterHitChanceStrategy = new MonsterVsHeroHitChance(random);
        this.random = random;
    }

    public boolean battle() {
        spawnMonsters();
        view.printBattleStart();
        applyPreBattleBuffs();

        while (true) {
            if (playerParty.getAliveHeroes().isEmpty()) {
                System.out.println("No heroes left alive in party!");
                return false;
            }

            if (monsterParty.getAliveMonsters().isEmpty()) {
                System.out.println("All monsters defeated");
                return true;
            }

            heroTurn();

            if (monsterParty.getAliveMonsters().isEmpty()) {
                System.out.println("All monsters defeated");
                return true;
            }

            monsterTurn();
            regenerationService.regenerate(playerParty);
        }
    }

    private void applyPreBattleBuffs() {
        for (int i = 0; i < playerParty.size(); i++) {
            playerParty.get(i).getJacket().updateBuffStats();
        }
    }

    private void heroTurn() {
        for (Hero fighter : new ArrayList<>(playerParty.getAliveHeroes())) {
            if (monsterParty.getAliveMonsters().isEmpty()) {
                return;
            }
            presenter.showStatus(playerParty, monsterParty);
            List<BattleAction> actions = buildActionsForHero(fighter);
            BattleAction action = presenter.chooseAction(fighter, monsterParty, actions);
            action.execute();
            if (action.consumesTurn()) {
                rewardDistributor.distribute(monsterParty, playerParty);
            }
        }
    }

    private List<BattleAction> buildActionsForHero(Hero hero) {
        List<BattleAction> actions = new ArrayList<>();
        actions.add(new AttackWithWeaponAction(hero, monsterParty, weaponDamageStrategy, heroHitChanceStrategy, view));
        actions.add(new CastSpellAction(hero, monsterParty, spellDamageStrategy, view));
        actions.add(new UsePotionAction(hero, view));
        return actions;
    }

    private void monsterTurn() {
        ArrayList<Monster> aliveMonsters = monsterParty.getAliveMonsters();
        for (Monster attackingMonster : aliveMonsters) {
            ArrayList<Hero> aliveHeroes = playerParty.getAliveHeroes();
            if (aliveHeroes.isEmpty()) {
                return;
            }
            Hero target = aliveHeroes.get(random.nextInt(aliveHeroes.size()));
            if (monsterHitChanceStrategy.hits(attackingMonster, target)) {
                int damage = monsterDamageStrategy.calculateDamage(attackingMonster, target);
                target.getStats().setHealth(target.getStats().getHealth() - damage);
                view.printMonsterAttack(attackingMonster, target, damage);
            } else {
                view.printMonsterMiss(attackingMonster, target);
            }
        }
    }

    private void spawnMonsters() {
        MonsterPool monsterPool = new MonsterPool();
        int numOfMonsters = playerParty.size();
        int levelCap = playerParty.getPartyLevel();

        for (int i = 0; i < numOfMonsters; i++) {
            Monster template = monsterPool.getRandomMonster();
            Monster monster = template.copy();
            monster.getLevelObj().setCurrentLevel(levelCap);
            monster.rescaleStatsForLevel();
            monster.setGoldDrop();
            monster.setExperienceDrop();
            monsterParty.add(monster);
        }
    }
}
