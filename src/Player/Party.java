package Player;

import Entities.Hero;
import Interfaces.Positionable;

import java.util.ArrayList;
import java.util.List;

public class Party {

    private final ArrayList<Hero> hero_party;

    public Party() {
        hero_party = new ArrayList<Hero>();
    }

    public void addHeroToParty(Hero entity) {
        hero_party.add(entity);

    }
    public void removeHeroFromParty(Hero hero){
        hero_party.remove(hero);
    }

    public Hero getHeroFromParty(int slot){
        return hero_party.get(slot);
    }

    public int getPartySize() {
        return hero_party.size();
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
            if(hero.getStats().getHealth() >= 0){
                hero.getStats().setHealth(0);
            }
            dead_heroes.add(hero);
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

}
