package World;
import Factories.*;
import Entities.*;

import java.util.ArrayList;
import java.util.List;

import FileReader.FileReaderUtility;
import Interfaces.HeroFactory;
import Interfaces.MonsterFactory;
import Utility.Stats;


public class Seeder {

    private final WarriorFactory warriorFactory;
    private final PaladinFactory paladinFactory;
    private final SorcererFactory sorcererFactory;
    private final DragonFactory dragonFactory;
    private final ExoskeletonFactory exoskeletonFactory;
    private final SpiritFactory spiritFactory;

    /**
     *
     *
     * @param wf --warrior factory
     * @param pf -- paladin factory
     * @param sf -- sorcerer factory
     */
    public Seeder(WarriorFactory wf, PaladinFactory pf, SorcererFactory sf, DragonFactory df, ExoskeletonFactory ef, SpiritFactory spf) {

        this.warriorFactory = wf;
        this.paladinFactory = pf;
        this.sorcererFactory = sf;
        this.dragonFactory = df;
        this.exoskeletonFactory = ef;
        this.spiritFactory = spf;


    }

    // HERO SEEDING

    private List<Hero> seedHeros(String file_path, HeroFactory hf) {
        List<Hero> heros = new ArrayList<>();

        List<String[]> rows = FileReaderUtility.readFile(file_path, ",");

        for( String[] data : rows) {
            Hero hero = hf.createEntity(data[0], new Stats.StatsBuilder()
                    .health(Integer.parseInt(data[1]))
                    .mana(Integer.parseInt(data[2]))
                    .attack(Integer.parseInt(data[3]))
                    .dexterity(Integer.parseInt(data[4]))
                    .agility(Double.parseDouble(data[5]))
                    .damage_reduction(Double.parseDouble(data[6]))
                    .buildStats());
            heros.add(hero);
        }

        return heros;

    }

    private List<Monster> seedMonsters(String file_path, MonsterFactory mf) {
        List<Monster> monsters = new ArrayList<>();

        List<String[]> rows = FileReaderUtility.readFile(file_path, ",");

        for( String[] data : rows) {
            Monster monster = mf.createEntity(data[0], new Stats.StatsBuilder()
                    .health(Integer.parseInt(data[1]))
                    .attack(Integer.parseInt(data[2]))
                    .damage_reduction(Double.parseDouble(data[3]))
                    .agility(Double.parseDouble(data[4]))
                    .buildStats());

            monsters.add(monster);
        }

        return monsters;
    }

    public List<Hero> seedWarriors(String file_path) {
        return  seedHeros(file_path, warriorFactory);
    }
    public List<Hero> seedPaladins(String file_path) {
        return  seedHeros(file_path, paladinFactory);
    }

    public List<Hero> seedSorcerers(String file_path) {
        return  seedHeros(file_path, sorcererFactory);
    }

    public List<Monster> seedDragons(String file_path) {
        return  seedMonsters(file_path, dragonFactory);
    }
    public List<Monster> seedExoSkeletons(String file_path) {
        return  seedMonsters(file_path, exoskeletonFactory);
    }
    public List<Monster> seedSpirits(String file_path) {
        return  seedMonsters(file_path, spiritFactory);
    }

}

