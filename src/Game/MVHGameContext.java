package Game;

import Adapters.PartyPositionAdapter;
import Interfaces.Positionable;
import Market.MarketVisitor;
import Parties.Party;

public class MVHGameContext implements GameContext {

    private final Party party;
    private final PartyPositionAdapter partyPosition;

    public MVHGameContext(Party party, PartyPositionAdapter partyPosition) {
        this.party = party;
        this.partyPosition = partyPosition;
    }

    @Override
    public MarketVisitor getMarketVisitor() {
        return party; // Party implements MarketVisitor
    }

    @Override
    public Positionable getPositionable() {
        return partyPosition;
    }
}

