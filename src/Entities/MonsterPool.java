/**
 * Pool responsible for spawning and tracking monsters on the map.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Factories.DragonFactory;
import Factories.ExoskeletonFactory;
import Factories.SpiritFactory;
import Parties.MonsterParty;
import Seeders.EntitySeeder;

public class MonsterPool {
    private final EntitySeeder entity_seeder;

    private final ArrayList<Monster> pool = new ArrayList<>();

    public MonsterPool(){
        this.entity_seeder = new EntitySeeder(
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory()
        );
        generateMonsters();
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

    private void generateMonsters() {
        pool.addAll(generateDragons());
        pool.addAll(generateExoskeletons());
        pool.addAll(generateSpirits());
    }

    public Monster getRandomMonster() {
        if (pool.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int index = random.nextInt(pool.size());
        Monster m = pool.get(index);
        return m.copy();
    }



}
