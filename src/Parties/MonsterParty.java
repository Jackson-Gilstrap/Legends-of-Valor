package Parties;

import Entities.Hero;
import Entities.Monster;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The MonsterParty class represents a group of monsters.
 * It provides methods to manage monsters, query their status,
 * and display information about the entire monster party.
 */
public class MonsterParty implements Iterable<Monster>{

    /** List of monsters currently in the party */
    private final ArrayList<Monster> monsters;

    /** 
     * Creates an empty monster party.
     */
    public MonsterParty() {
        monsters = new ArrayList<>();
    }

    /* =========================================================
       Basic party management methods
       ========================================================= */

    /**
     * Add a monster to the party.
     * @param monster the monster to be added
     */
    public void add(Monster monster) {
        monsters.add(monster);
    }

    /**
     * Remove a monster from the party by index.
     * @param monsterIndex the index of the monster to remove
     */
    public void remove(int monsterIndex) {
        Monster monster = monsters.get(monsterIndex);
        monsters.remove(monster);
    }

    /**
     * Remove a monster from the party.
     * @param monsterIndex the index of the monster to remove
     */
    public void remove(Monster monster) {
        monsters.remove(monster);
    }



    /**
     * Get a monster at a specific index.
     * @param index the index of the monster (0-based)
     * @return the monster at that index
     */
    public Monster get(int index) {
        return monsters.get(index);
    }

    /**
     * @return the number of monsters currently in the party
     */
    public int size() {
        return monsters.size();
    }

    /**
     * @return true if there are no monsters in the party
     */
    public boolean isEmpty() {
        return monsters.isEmpty();
    }

    /* =========================================================
       Query methods
       ========================================================= */

    /**
     * Get all monsters that are still alive (health > 0).
     * @return list of alive monsters
     */
    public ArrayList<Monster> getAliveMonsters() {
        ArrayList<Monster> aliveMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            if (monster.getStats().getHealth() > 0) {
                aliveMonsters.add(monster);
            }
        }
        return aliveMonsters;
    }

    /**
     * Get all monsters that are dead (health <= 0).
     * @return list of dead monsters
     */
    public ArrayList<Monster> getDeadMonsters() {
        ArrayList<Monster> deadMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            if (monster.getStats().getHealth() <= 0) {
                deadMonsters.add(monster);
            }
        }
        return deadMonsters;
    }

    /* =========================================================
       Display methods
       ========================================================= */

    /**
     * Print detailed information about all monsters in the party.
     */
    public void getPartyInfo() {
        System.out.println("===== MONSTER PARTY INFORMATION =====");

        int index = 1;
        for (Monster monster : monsters) {
            System.out.printf("Party Slot %d:%n", index);
            System.out.println(monster); // relies on Monster’s toString()
            System.out.println("-----------------------------");
            index++;
        }

        System.out.println("===== END OF PARTY =====\n");
    }

    @Override
    public Iterator<Monster> iterator() {
        return monsters.iterator();
    }
}
