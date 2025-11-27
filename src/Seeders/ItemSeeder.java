package Seeders;

import Enums.PotionType;
import Enums.SpellType;
import Factories.ArmorFactory;
import Factories.PotionFactory;
import Factories.SpellFactory;
import Factories.WeaponFactory;
import FileReader.FileReaderUtility;
import Items.Armor;
import Items.Potion;
import Items.Spell;
import Items.Weapon;
import Utility.Stats;

import java.util.ArrayList;
import java.util.List;

public class ItemSeeder {
    private final WeaponFactory weaponFactory;
    private final ArmorFactory armorFactory;
    private final SpellFactory spellFactory;
    private final PotionFactory potionFactory;

    public ItemSeeder(WeaponFactory wf, ArmorFactory af, SpellFactory sf, PotionFactory pf) {
        this.weaponFactory = wf;
        this.armorFactory = af;
        this.spellFactory = sf;
        this.potionFactory = pf;
    }


    private List<Weapon> seedWeapons(String file_path, WeaponFactory wpf) {
        List<Weapon> weapons = new ArrayList<>();

        List<String[]> rows = FileReaderUtility.readFile(file_path, ",");

        for( String[] data : rows) {
            Weapon weapon = wpf.createWeapon(data[0], new Stats.StatsBuilder()
                            .attack(Integer.parseInt(data[1])).buildStats(),
                    Integer.parseInt(data[2]),
                    Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]));

            weapons.add(weapon);
        }
        return weapons;

    }

    private List<Armor> seedArmors(String file_path, ArmorFactory af) {
        List<Armor> armors = new ArrayList<>();
        List<String[]> rows = FileReaderUtility.readFile(file_path, ",");
        for( String[] data : rows) {
            Armor armor = af.createArmor(
                    data[0],
                    data[1],
                    new Stats.StatsBuilder()
                            .damage_reduction(Double.parseDouble(data[2]))
                            .buildStats(),
                    Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]));

            armors.add(armor);

        }

        return armors;
    }

    private List<Spell> seedSpells(String file_path, SpellFactory sf) {
        List<Spell> spells = new ArrayList<>();
        List<String[]> rows = FileReaderUtility.readFile(file_path, ",");

        for (String[] data : rows) {
            Spell spell = sf.createSpell(
                    data[1],                       // Name
                    Integer.parseInt(data[2]),     // Dexterity
                    Integer.parseInt(data[5]),     // level
                    Integer.parseInt(data[4]),     // Price
                    Integer.parseInt(data[3]),     // Mana cost
                    SpellType.valueOf(data[0])     // Type
            );

            spells.add(spell);
        }

        return spells;
    }

    private List<Potion> seedPotions(String file_path, PotionFactory pf) {
        List<Potion> potions = new ArrayList<>();
        List<String[]> rows = FileReaderUtility.readFile(file_path, ",");
        for (String[] data : rows) {
            Potion potion = pf.createPotion(
                    data[1],
                    Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]),

                    PotionType.valueOf(data[0]),
                    (int) Double.parseDouble(data[2])


            );

            potions.add(potion);
        }
        return potions;
    }


    public List<Weapon> seedWeapons(String file_path) {
        return seedWeapons(file_path, weaponFactory );
    }

    public List<Armor> seedArmors(String file_path) {
        return seedArmors(file_path, armorFactory);
    }

    public List<Spell> seedSpells(String file_path) {
        return seedSpells(file_path, spellFactory);
    }

    public List<Potion> seedPotions(String file_path) {
        return seedPotions(file_path, potionFactory);
    }
}
