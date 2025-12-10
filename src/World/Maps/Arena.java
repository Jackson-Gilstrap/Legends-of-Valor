package World.Maps;

import World.Map;
import World.Space;

public class Arena extends Map {
    private final Space[][] arena;

    public Arena(int rows, int cols) {
        super(rows, cols);
        this.arena = new Space[rows][cols];
    }

    public void build() {
        return;
    }

    public String render() {
        return "";
    }

}
