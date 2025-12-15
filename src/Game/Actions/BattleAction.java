package Game.Actions;

/**
 * Command abstraction for a hero action in battle.
 */
public interface BattleAction {
    /**
     * @return true if this action can run right now (has resources/targets/etc).
     */
    boolean canExecute();

    /**
     * Performs the action.
     */
    void execute();

    /**
     * Label used by the presenter/menu.
     */
    String label();

    /**
     * @return true if executing this action should consume the hero's turn.
     */
    default boolean consumesTurn() {
        return true;
    }
}
