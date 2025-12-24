package Views;

import Controllers.InputHandler;
import WorldSets.Maps.World;

/**
 * CLI view for rendering the Monsters vs Heroes world and available commands.
 */
public class CliWorldView {
    public void render(World world) {
        System.out.println(world.render());
    }

    public void showCommands(InputHandler inputHandler) {
        inputHandler.printValidCommands();
    }
}
