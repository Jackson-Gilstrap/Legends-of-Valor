/**
 * Command to enter a market and trigger market interactions.
 */
package Commands;

import Game.GameContext;
import Market.MarketInteractionController;
import Controllers.MovementController;

public class EnterMarket implements Command{
    private MovementController<?,?> controller;
    private MarketInteractionController interaction;
    private GameContext gameContext;

    public EnterMarket(MovementController<?,?> controller, MarketInteractionController interaction,GameContext gameContext) {
        this.controller = controller;
        this.interaction = interaction;
        this.gameContext = gameContext;
    }

    @Override
    public boolean execute() {
        interaction.interactMarket(controller.getCurrentSpace(), gameContext.getMarketVisitor());
        return false;

    }

    @Override
    public String name() {
        return "MARKET";
    }
    
}
