package Parties;

import Market.MarketController;
import Entities.Hero;
import Market.Market;
import Market.MarketVisitor;

import java.util.ArrayList;
import java.util.Iterator;


public class Party implements Iterable<Hero>, MarketVisitor {

    private final ArrayList<Hero> hero_party;

    public Party() {
        hero_party = new ArrayList<>();

    }


    public void add(Hero entity) {
        hero_party.add(entity);
    }

    public void remove(Hero hero){
        hero_party.remove(hero);
    }

    public Hero get(int slot){
        return hero_party.get(slot);
    }

    public int size() {
        return hero_party.size();
    }

    public int getPartyLevel() {
        int  level = 0;
        for (Hero hero : hero_party) {
           level += hero.getLevelObj().getCurrentLevel();
        }
        return level / hero_party.size();
    }

    public ArrayList<Hero> getAliveHeroes(){
        ArrayList<Hero> alive_heroes = new ArrayList<>();
        for(Hero hero : hero_party){
            if(hero.getStats().getHealth() > 0 ){
                alive_heroes.add(hero);
            }
        }
        return alive_heroes;
    }

    public ArrayList<Hero> getDeadHeroes(){
        ArrayList<Hero> dead_heroes = new ArrayList<>();
        for(Hero hero : hero_party){
            if(hero.getStats().getHealth() <= 0){
                hero.getStats().setHealth(0);
                dead_heroes.add(hero);
            }
        }
        return dead_heroes;
    }


    public void getPartyInfo() {
        System.out.println("===== PARTY INFORMATION =====");

        int index = 1;
        for (Hero character : hero_party) {
            System.out.printf("Party Slot %d:%n", index);
            System.out.println(character); // relies on Hero/Warrior’s toString()
            System.out.println("-----------------------------");
            index++;
        }

        System.out.println("===== END OF PARTY =====");
        System.out.println("\n");
    }


    @Override
    public Iterator<Hero> iterator() {
        return hero_party.iterator();
    }

    @Override
    public void visit(Market market,  MarketController marketController) {
        boolean exit = false;
        while (!exit) {
            Hero hero = marketController.pickHero(this);
            exit = !marketController.displayMarket(market, hero);
        }
    }
}
