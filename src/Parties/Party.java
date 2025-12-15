package Parties;

import Market.MarketController;
import Entities.Hero;
import Market.Market;
import Market.MarketVisitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Party class represents a team (party) of heroes.
 * It provides methods for managing, querying, and displaying
 * information about the heroes in the party.
 */
public class Party implements Iterable<Hero>, MarketVisitor {

    /** List of heroes currently in the party */
    private final ArrayList<Hero> hero_party;

    /** 
     * Creates an empty hero party.
     */
    public Party() {
        hero_party = new ArrayList<>();
    }

    /* =========================================================
       Basic party management methods
       ========================================================= */

    /**
     * Add a hero to the party.
     * @param hero the hero to be added
     */
    public void add(Hero hero) {
        hero_party.add(hero);
    }

    /**
     * Remove a hero from the party.
     * @param hero the hero to be removed
     */
    public void remove(Hero hero) {
        hero_party.remove(hero);
    }

    /**
     * Get the hero in a specific party slot.
     * @param slot the index of the hero (0-based)
     * @return the hero in that slot
     */
    public Hero get(int slot) {
        return hero_party.get(slot);
    }

    /**
     * @return the number of heroes currently in the party
     */
    public int size() {
        return hero_party.size();
    }

    /* =========================================================
       Query methods
       ========================================================= */

    /**
     * Calculate the average level of the party.
     * @return the average level of all heroes (integer division)
     */
    public int getPartyLevel() {
        int totalLevel = 0;
        for (Hero hero : hero_party) {
            totalLevel += hero.getLevelObj().getCurrentLevel();
        }
        return totalLevel / hero_party.size();
    }

    /**
     * Get all heroes that are still alive (health > 0).
     * @return list of alive heroes
     */
    public ArrayList<Hero> getAliveHeroes() {
        ArrayList<Hero> aliveHeroes = new ArrayList<>();
        for (Hero hero : hero_party) {
            if (hero.getStats().getHealth() > 0) {
                aliveHeroes.add(hero);
            }
        }
        return aliveHeroes;
    }

    /**
     * Get all heroes that are dead (health <= 0).
     * Dead heroes’ health is reset to 0 for consistency.
     * @return list of dead heroes
     */
    public ArrayList<Hero> getDeadHeroes() {
        ArrayList<Hero> deadHeroes = new ArrayList<>();
        for (Hero hero : hero_party) {
            if (hero.getStats().getHealth() <= 0) {
                hero.getStats().setHealth(0);
                deadHeroes.add(hero);
            }
        }
        return deadHeroes;
    }

    /* =========================================================
       Display methods
       ========================================================= */

    /**
     * Print detailed information about all heroes in the party.
     */
    public void getPartyInfo() {
        System.out.println("===== PARTY INFORMATION =====");

        int index = 1;
        for (Hero hero : hero_party) {
            System.out.printf("Party Slot %d:%n", index);
            System.out.println(hero); // relies on Hero’s toString()
            System.out.println("-----------------------------");
            index++;
        }

        System.out.println("===== END OF PARTY =====\n");
    }

    /* =========================================================
       Interface implementations
       ========================================================= */

    @Override
    public Iterator<Hero> iterator() {
        return hero_party.iterator();
    }

    @Override
    public void visit(Market market, MarketController marketController) {
        boolean exit = false;
        while (!exit) {
            Hero hero = marketController.pickHero(this);
            exit = !marketController.displayMarket(market, hero);
        }
    }
}
