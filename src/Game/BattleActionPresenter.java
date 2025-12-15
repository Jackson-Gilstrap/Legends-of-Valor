package Game;

import Entities.Hero;
import Game.Actions.BattleAction;
import Game.Actions.TargetedAction;
import Parties.MonsterParty;
import Parties.Party;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps menu choices to commands and gathers any required input.
 */
public class BattleActionPresenter {
    private final GameUI ui;
    private final BattleView view;

    public BattleActionPresenter(GameUI ui, BattleView view) {
        this.ui = ui;
        this.view = view;
    }

    public void showStatus(Party heroes, MonsterParty monsters) {
        view.showBattleStatus(heroes, monsters);
    }

    public BattleAction chooseAction(Hero hero, MonsterParty monsterParty, List<BattleAction> actions) {
        while (true) {
            System.out.println(hero.getName() + ": choose an action");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println((i + 1) + ". " + actions.get(i).label());
            }
            int choice = ui.askInt() - 1;
            if (choice < 0 || choice >= actions.size()) {
                System.out.println("Invalid choice! Pick again");
                continue;
            }
            BattleAction action = actions.get(choice);
            if (action instanceof TargetedAction) {
                int targetIndex = requestMonsterTarget(monsterParty);
                ((TargetedAction) action).setTargetIndex(targetIndex);
            }
            if (action.canExecute()) {
                return action;
            }
            System.out.println("Action cannot be used right now. Pick another.");
        }
    }

    private int requestMonsterTarget(MonsterParty monsterParty) {
        ArrayList<?> alive = monsterParty.getAliveMonsters();
        while (true) {
            System.out.println("Pick a monster to target");
            int choice = ui.askInt();
            if (choice >= 0 && choice < alive.size()) {
                return choice;
            }
            System.out.println("Invalid monster target.");
        }
    }
}
