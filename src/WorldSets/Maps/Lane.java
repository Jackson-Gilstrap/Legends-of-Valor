package WorldSets.Maps;

import java.util.ArrayList;
import java.util.List;

import Entities.Hero;

/**
 * Represents the three lanes in a MOBA-style map: TOP, MIDDLE, and BOTTOM.
 */
public enum Lane {
    TOP(0, "Top Lane"),
    MIDDLE(1, "Middle Lane"),
    BOTTOM(2, "Bottom Lane");

    private final int id;
    private final String name;
    private List<Hero> heroes;  // heroes on this lane

    Lane(int id, String name) {
        this.id = id;
        this.name = name;
        heroes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Get a Lane by its id.
     * @param id numeric lane id (0–2)
     * @return the Lane instance, or null if not found
     */
    public static Lane fromId(int id) {
        for (Lane lane : values()) {
            if (lane.id == id) return lane;
        }
        return null;
    }

    public void addHero(Hero h){
        heroes.add(h);
    }

    public void removeHero(Hero h){
        heroes.remove(h);
    }

    public List<Hero> getHeroes(){
        return heroes;
    }

    public boolean isEmpty(){
        return heroes.size()==0;
    }
}
