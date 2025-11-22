package Game;

import Entities.Hero;
import Entities.Monster;
import Items.Spell;
import Player.Party;
import Seeders.EntitySeeder;
import Factories.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Battle {
    private Party player_party;
    private ArrayList<Monster> monster_party;
    private EntitySeeder entity_seeder;
    private GameUI ui = new GameUI();

    public Battle(Party player_party) {
        this.player_party = player_party;
        this.monster_party = new ArrayList<Monster>();
        this.entity_seeder = new EntitySeeder(
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory()

        );

    }

    public Party getPlayerParty() {
        return player_party;
    }

    public ArrayList<Monster> getMonsterParty() {
        return monster_party;
    }

    private List<Monster> generateDragons() {
       return  entity_seeder.seedDragons("src/TextFiles/Dragons.txt");
    }

    private List<Monster> generateExoskeletons() {
        return entity_seeder.seedExoSkeletons("src/TextFiles/Exoskeletons.txt");
    }

    private List<Monster> generateSpirits() {
        return entity_seeder.seedSpirits("src/TextFiles/Spirits.txt");
    }

    private List<Monster> generateMonsters() {
        List<Monster> monsters = new ArrayList<>();
        List<Monster> dragons = generateDragons();
        List<Monster> exoskeletons = generateExoskeletons();
        List<Monster> spirits = generateSpirits();
        monsters.addAll(dragons);
        monsters.addAll(exoskeletons);
        monsters.addAll(spirits);

        Collections.shuffle(monsters);
        return monsters;
    }

    private void spawnMonsters() {
        int num_of_monsters = player_party.getPartySize();
        List<Monster> all_monsters = generateMonsters();
        for (int i = 0; i < num_of_monsters; i++) {
            Monster monster = all_monsters.get(generateRandomInt(all_monsters.size() - 1));
            monster_party.add(monster);
            System.out.println("Added monster " + (i+1) + ": " + monster.getName());
        }


    }

    private int generateRandomInt(int max) {
        Random rand = new Random();
        return rand.nextInt(max);

    }

    private void heroAttackWithSpell(int hero_index, int monster_index) {

        // get valid monster targets
        ArrayList<Monster> alive_monsters = new ArrayList<>();
        for (Monster monster : monster_party) {
            if(monster.getStats().getHealth() > 0) {
                alive_monsters.add(monster);
            }
        }

        // get target and attacker
        Hero attacking_hero = player_party.getHeroFromParty(hero_index);
        Monster target_monster = alive_monsters.get(monster_index);

        //damage calc and effect calc
        int hero_atk = attacking_hero.getStats().getDexterity() + attacking_hero.getJacket().getBuffStats().getDexterity();
        //get the spell and its effect.
        Spell spell = attacking_hero.getJacket().getSpells().getItem();
        int spell_dmg = spell.getDamage();
        int spell_cost = spell.getManaCost();
        double spell_debuff = spell.getType().debuffMultiplier(spell.getLevel());

        int total_dmg = hero_atk + spell_dmg;

        // The hit
        System.out.println(attacking_hero.getName() + "used " + spell.getName() + "on " + target_monster.getName() + "dealing " + total_dmg);
        target_monster.getStats().setHealth(target_monster.getStats().getHealth() - total_dmg);
        switch(spell.getType()) {
            case FIRE:
                System.out.println(target_monster.getName() + "'s defence dropped");
                target_monster.getStats().setDamage_reduction(target_monster.getStats().getDamage_reduction() - spell_debuff);
                break;
            case ICE:
                System.out.println(target_monster.getName() + "'s attack dropped");
                target_monster.getStats().setAttack(target_monster.getStats().getAttack() - (int)(target_monster.getStats().getAttack() * spell_debuff));
                break;
            case LIGHTNING:
                System.out.println(target_monster.getName() + "'s agility dropped");
                target_monster.getStats().setAgility(target_monster.getStats().getAgility() - spell_debuff);
                break;
        }

    }

    private void heroAttackWithWeapon(int hero_index, int monster_index) {

        // get valid monster targets
        ArrayList<Monster> alive_monsters = new ArrayList<>();
        for (Monster monster : monster_party) {
            if(monster.getStats().getHealth() > 0) {
                alive_monsters.add(monster);
            }
        }

        // get target and attacker
        Hero attacking_hero = player_party.getHeroFromParty(hero_index);
        Monster target_monster = alive_monsters.get(monster_index);

        // damage calc
        int hero_atk = attacking_hero.getStats().getAttack() + attacking_hero.getJacket().getBuffStats().getAttack();
        double monster_dr = target_monster.getStats().getDamage_reduction();

        int total_attack = hero_atk - (int) (hero_atk * monster_dr);

        // hitting calc
        double monster_dodge = target_monster.getStats().getAgility();
        if (calculateDodge(monster_dodge)) {
            System.out.println(attacking_hero.getName() + " attacked " + target_monster.getName() + " and dealt " + total_attack + " damage!");
            target_monster.getStats().setHealth(target_monster.getStats().getHealth() - total_attack);
        } else {
            System.out.println(attacking_hero.getName() + " attacked " + target_monster.getName() + " but missed!");
        }

    }

    private void monsterAttack(int monster_index) {
        Random rand = new Random();
        // gets valid hero targets
        ArrayList<Hero> alive_heroes = player_party.getAliveHeroes();
        if(alive_heroes.isEmpty()) {return;}

        // get target and attacker
        Monster attacking_monster = monster_party.get(monster_index);
        Hero target_hero = alive_heroes.get(rand.nextInt(alive_heroes.size()));



        //Damage calc

        int mon_attack = attacking_monster.getStats().getAttack();
        double total_hero_dr = target_hero.getStats().getDamage_reduction() + target_hero.getJacket().getBuffStats().getDamage_reduction();
                ;
        int total_attack = mon_attack - (int)(mon_attack * total_hero_dr);

        // Hitting calc
        double total_hero_dodge = target_hero.getStats().getAgility() + target_hero.getJacket().getBuffStats().getAgility();
        if (calculateDodge(total_hero_dodge)) {
            //attack
            System.out.println(attacking_monster.getName()+" attacked " + target_hero.getName() + " and dealt " + total_attack + " damage!");
            target_hero.getStats().setHealth(target_hero.getStats().getHealth() - total_attack);

        } else {
            //miss
            System.out.println(attacking_monster.getName()+" attacked " + target_hero.getName() + " but missed!");
        }

    }

    private void heroTurn(int move_choice, int hero_index, int monster_index) {
        boolean turnOver = false;
        while (!turnOver) {
            switch (move_choice) {
                case 1:
                    heroAttackWithWeapon(hero_index, monster_index);
                    turnOver = true;
                    break;
                case 2:
                    heroAttackWithSpell(hero_index, monster_index);
                    turnOver = true;
                    break;
                case 3: // use a potion
                    System.out.println("using a potion");
                    turnOver = true;
                    break;
                default:
                    System.out.println("Invalid choice! Pick again");
                    break;
            }
        }
    }


    private void monstersAttackTurn() {

        for (int i= 0; i < monster_party.size(); i++) {
            monsterAttack(i);
        }

    }

    private void showBattleInfo() {
        System.out.println("=== HEROES ===");
        for (int i = 0; i < player_party.getPartySize(); i++) {
            Hero h = player_party.getHeroFromParty(i);
            System.out.println(
                    "[" + i + "] " + h.getName() +
                            " | HP: " + h.getStats().getHealth() +
                            " | MP: " + h.getStats().getMana()
            );
        }

        System.out.println("\n=== MONSTERS ===");
        for (int i = 0; i < monster_party.size(); i++) {
            Monster m = monster_party.get(i);
            System.out.println(
                    "[" + i + "] " + m.getName() +
                            " | HP: " + m.getStats().getHealth()
            );
        }

        System.out.println();
    }

    private int showBattleOptions(){
        System.out.println("Battle options");
        System.out.println("1. View battle info\n2. Attack");
        boolean valid_options = false;
        int num = -1;
        while (!valid_options) {
            num = ui.askInt();
            if (num == 1 || num == 2) {
                valid_options = true;
            } else {

                System.out.println("Invalid choice! Pick again");
            }
        }
        return num;
    }

    private int showHeroOptions(){
        System.out.println("Hero options");
        System.out.println("1. Attack with weapon\t2. Attack with Spell\t3. Use a potion");
        boolean valid_options = false;
        int num = -1;
        while (!valid_options) {
            num = ui.askInt();
            if (num == 1 || num == 2 || num == 3) {
                valid_options = true;
            }else {

                System.out.println("Invalid choice! Pick again");
            }
        }
        return num;
    }

    public boolean battle() {
        boolean isOver = false;
        boolean player_lost = false;
        //spawn monsters
        spawnMonsters();
        //display both the player party and the monsters party
        System.out.println("Battle started!");
        System.out.println("Monsters on the battle field\n");
        for(Monster monster : monster_party) {
            System.out.println(monster.getName());
        }
        System.out.println("Your party walks up to the monsters");
        System.out.println("Player Party\n");
        player_party.getPartyInfo();
        for(int i = 0; i < player_party.getPartySize(); i++) {
            player_party.getHeroFromParty(i).getJacket().updateBuffStats();
        }

        while(!isOver) {

            int choice = showBattleOptions();

            switch (choice) {
                case 1:
                    showBattleInfo();
                    continue;
                case 2:
                    ArrayList<Hero> heroesFighting = new ArrayList<>(player_party.getAliveHeroes());
                    for(int i = 0; i < heroesFighting.size(); i++) {
                        int choice_2 = showHeroOptions();
                        showBattleInfo();
                        System.out.println("Pick a monster to attack");
                        int monster_choice = ui.askInt();
                        heroTurn(choice_2, i, monster_choice);
                    }
                    monstersAttackTurn();
                    break;
                default:
                    System.out.println("Invalid choice! Pick again");
                    continue;

            }
            if (player_party.getAliveHeroes().isEmpty()) {
                player_lost = true;
                isOver = true;
            } else if (allMonstersDead()) {
                isOver = true;
            }


        }
        return !player_lost;
    }

    private boolean calculateDodge(double number) {
        double hitting_chance =  1 - number;
        double attack_roll = Math.random();
        return attack_roll <= hitting_chance;
    }


    private boolean allMonstersDead() {
        for (Monster m : monster_party) {
            if (m.getStats().getHealth() > 0) return false;
        }
        return true;
    }
}
