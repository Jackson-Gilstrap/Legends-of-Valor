package Player;

import Entities.Hero;
import Entities.Monster;

import java.util.ArrayList;

public class MonsterParty {
    private final ArrayList<Monster> monsters;

    public MonsterParty() {
        monsters = new ArrayList<>();
    }

    public Monster getMonsterFromParty(int index) {
        return monsters.get(index);
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public void removeMonster(int monster_index){
        Monster monster = monsters.get(monster_index);
        monsters.remove(monster);
    }

    public int getMonsterPartySize() {
        return monsters.size();
    }

    public ArrayList<Monster> getAliveMonsters() {
        ArrayList<Monster> alive_monsters = new ArrayList<>();
        for(Monster monster : monsters) {
            if(monster.getStats().getHealth() > 0 ){
                alive_monsters.add(monster);
            }
        }
        return alive_monsters;
    }

    public ArrayList<Monster> getDeadMonsters() {
        ArrayList<Monster> dead = new ArrayList<>();
        for (Monster monster: monsters) { // monsters = internal ArrayList<Monster>
            if (monster.getStats().getHealth() <= 0) {
                dead.add(monster);
            }
        }
        return dead;
    }

    public boolean isEmpty() {
        return monsters.isEmpty();
    }


    public void getPartyInfo() {
        System.out.println("===== PARTY INFORMATION =====");

        int index = 1;
        for (Monster monster : monsters) {
            System.out.printf("Party Slot %d:%n", index);
            System.out.println(monster);
            System.out.println("-----------------------------");
            index++;
        }

        System.out.println("===== END OF PARTY =====");
        System.out.println("\n");
    }

}
