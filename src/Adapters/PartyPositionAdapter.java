/**
 * Adapter exposing the party's position through the Positionable interface.
 */
package Adapters;

import Interfaces.Positionable;
import WorldSets.Maps.World;

public class PartyPositionAdapter implements Positionable {

    private final World world_map;

    public PartyPositionAdapter(World world_map) {
        this.world_map = world_map;
    }

    @Override
    public int getCol() {
        return world_map.getParty_col();
    }

    @Override
    public int getRow() {
        return world_map.getParty_row();
    }

    @Override
    public void setPosition(int row, int col) {
        world_map.setPartyPosition(row, col);
    }
}
