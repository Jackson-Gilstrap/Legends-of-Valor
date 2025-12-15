package Game.Actions;

/**
 * Marker for actions that need a monster target before execution.
 */
public interface TargetedAction extends BattleAction {
    void setTargetIndex(int targetIndex);
}
