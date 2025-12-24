package Views;

import WorldSets.Maps.Arena;

/**
 * CLI view for rendering the Legends of Valor arena state.
 */
public class CliArenaView {
    public void render(Arena arena, int round) {
        System.out.println(arena.render());
        System.out.printf("---- Round %d ----%n", round);
    }
}
