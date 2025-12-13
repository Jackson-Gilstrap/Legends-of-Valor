package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Factories.DragonFactory;
import Factories.ExoskeletonFactory;
import Factories.SpiritFactory;
import Seeders.EntitySeeder;

public class MonsterPool {
    private final EntitySeeder entity_seeder;
    private List<Monster> monsters = null;

    public MonsterPool(){
        this.entity_seeder = new EntitySeeder(
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory()

        );
    }

    public List<Monster> generateDragons() {
        return  entity_seeder.seedDragons("src/TextFiles/Dragons.txt");
    }

    public List<Monster> generateExoskeletons() {
        return entity_seeder.seedExoSkeletons("src/TextFiles/Exoskeletons.txt");
    }

    public List<Monster> generateSpirits() {
        return entity_seeder.seedSpirits("src/TextFiles/Spirits.txt");
    }

    public List<Monster> generateMonsters() {
        List<Monster> monsters = new ArrayList<>();

        monsters.addAll(generateDragons());
        monsters.addAll(generateExoskeletons());
        monsters.addAll(generateSpirits());
        return monsters;
    }

    public Monster getRandomMonster(List<Monster> monsters) {
        if (monsters.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int index = random.nextInt(monsters.size());
        return monsters.get(index).copy();
    }

    public Monster getRandomMonster() {
        if (isEmpty()) {
            monsters = generateMonsters();
        }

        return getRandomMonster(monsters);
    }

    public boolean isEmpty(){
        return monsters == null || monsters.isEmpty();
    }

}
