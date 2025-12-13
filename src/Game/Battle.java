package Game;

import Entities.Hero;
import Entities.Monster;
import Entities.MonsterPool;
import Enums.PotionType;
import Items.Potion;
import Items.Spell;
import Parties.MonsterParty;
import Parties.Party;

import java.util.*;


public class Battle {
    private final Party player_party;
    private final MonsterParty monster_party;
    private final GameUI ui = new GameUI();
    private final Set<Monster> rewarded_monsters = new HashSet<>();
    private final Random rand = new Random();

    public Battle(Party player_party) {
        this.player_party = player_party;
        this.monster_party = new MonsterParty();
    }

    public boolean battle() {
        //set flag for battle state
        spawnMonsters();
        System.out.println("Battle started!");
        System.out.println("Your party walks up to the monsters");
        //apply all the buffs from the player's equipment before the first moves are made
        for(int i = 0; i < player_party.size(); i++) {
            player_party.get(i).getJacket().updateBuffStats();
        }

        while(true) {

            ArrayList<Hero> heroesFighting = player_party.getAliveHeroes();
            if (heroesFighting.isEmpty()) {
                System.out.println("No heroes left alive in party!");
                return false;
            }

            if (monster_party.getAliveMonsters().isEmpty()) {
                System.out.println("All monsters defeated");
               return true;
            }

            for (Hero fighter : heroesFighting) {
                showBattleInfo();

                boolean valid_input = false;
                int monster_choice = 0;


                while (!valid_input) {
                    int aliveCount = monster_party.getAliveMonsters().size();
                    if (aliveCount == 0) {
                        System.out.println("All monsters defeated");
                        return true;
                    }
                    System.out.println(fighter.getName() + ": Pick a monster to attack");
                    monster_choice = ui.askInt();
                    if (monster_choice >= 0 && monster_choice < aliveCount) {
                        valid_input = true;
                    }


                }
                showBattleOptions(fighter, monster_choice);

                rewardNewlyDeadMonsters();

                if (monster_party.getAliveMonsters().isEmpty()) {
                    System.out.println("All monsters defeated");
                    return true;
                }
            }


            monstersAttackTurn();

            //heal heros and regen mana
            for(Hero fighter : player_party.getAliveHeroes()) {
                if (fighter.getStats().getHealth() > 0) {
                    // give ten percent back
                    int regen_health = (int) (fighter.getStats().getHealth() * .10);
                    int regen_mana = (int) (fighter.getStats().getMana() * .10);
                    int current_health = fighter.getStats().getHealth();
                    int current_mana = fighter.getStats().getMana();
                    fighter.getStats().setHealth(current_health +  regen_health);
                    fighter.getStats().setMana(current_mana + regen_mana);
                }
            }

        }

    }


    private void spawnMonsters() {
        // modify
        MonsterPool monsterPool = new MonsterPool();
        int num_of_monsters = player_party.size();
        int level_cap = player_party.getPartyLevel();

        for (int i = 0; i < num_of_monsters; i++) {

            Monster template = monsterPool.getRandomMonster();
            Monster monster = template.copy();
            monster.getLevelObj().setCurrentLevel(level_cap);
            monster.rescaleStatsForLevel();
            monster.setGoldDrop();
            monster.setExperienceDrop();

            monster_party.addMonster(monster);
        }

    }

    private boolean heroAttackWithSpell(Hero attacking_hero, int monster_index) {
        // get valid monster targets
        boolean success = false;
        ArrayList<Monster> alive_monsters = monster_party.getAliveMonsters();

        // get target

        if (monster_index < 0 || monster_index >= alive_monsters.size()) {
            System.out.println("Invalid monster target.");
            return success;
        }

        Monster target_monster = alive_monsters.get(monster_index);

        //damage calc and effect calc
        int hero_atk = attacking_hero.getStats().getDexterity() + attacking_hero.getJacket().getBuffStats().getDexterity();


        // check if spell is equipped
        if(!attacking_hero.hasSpellEquipped()) {
            System.out.println(attacking_hero.getName() + " tried to attack with a spell but doesn't have one equipped");
            return success;
        }

        //get the spell and its effect.
        Spell spell = attacking_hero.getJacket().getSpell();
        int spell_dmg = spell.getDamage();
        int spell_cost = spell.getManaCost();

        //check if the hero has enough mana to use the spell
        if(attacking_hero.getStats().getMana() < spell_cost) {
            System.out.println(attacking_hero.getName() + " tried to attack with a spell but doesn't have enough mana");
            return success;
        }

        double spell_debuff = spell.getType().debuffMultiplier(spell.getLevel());

        int total_dmg = hero_atk + spell_dmg;

        // The hit
        System.out.println(attacking_hero.getName() + "used " + spell.getName() + " on " + target_monster.getName() + " dealing " + total_dmg);
        target_monster.getStats().setHealth(target_monster.getStats().getHealth() - total_dmg);
        attacking_hero.getStats().setMana(attacking_hero.getStats().getMana() - spell_cost);

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
        success = true;
        return success;
    }

    private boolean heroAttackWithWeapon(Hero attacking_hero, int monster_index) {
        boolean success = false;
        // get valid monster targets
        ArrayList<Monster> alive_monsters = monster_party.getAliveMonsters();

        if (monster_index < 0 || monster_index >= alive_monsters.size()) {
            System.out.println("Invalid monster target.");
            return success;
        }

        // get target
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
        success = true;
        return success;
    }

    private boolean heroUsePotion(Hero hero) {
        boolean success = false;

        //make sure potion exists
        if(!hero.hasPotionEquipped()) {
            System.out.println(hero.getName() + " doesn't have any potion equipped");
            return success;
        }

        //get the potion
        Potion potion = hero.getJacket().getPotion();

        //make sure it's not consumed
        if( potion.isConsumed()) {
            System.out.println(hero.getName() + " has already consumed potion can't consume anymore");
            return success;
        }

        //potion calc - get type - apply buff
        PotionType type = potion.getType();
        potion.setEffectAmount(hero.getLevelObj().getCurrentLevel(), type);
        double effect_buff = potion.getEffectAmount();

        //use the potion
        switch(type) {
            case HP:
                hero.getStats().setHealth(hero.getStats().getHealth() + (int) effect_buff);
                potion.consume();
                break;
            case MP:
                hero.getStats().setMana(hero.getStats().getMana() + (int) effect_buff);
                potion.consume();
                break;
            case Attack:
                hero.getStats().setAttack(hero.getStats().getAttack() + (int) effect_buff);
                potion.consume();
                break;
            case Dexterity:
                hero.getStats().setDexterity(hero.getStats().getDexterity() + (int) effect_buff);
                potion.consume();
                break;
            case Agility:
                hero.getStats().setAgility(hero.getStats().getAgility() + effect_buff);
                potion.consume();
                break;
        }

        success = true;
        return success;

    }

    private void monsterAttack(int monster_index) {

        ArrayList<Hero> alive_heroes = player_party.getAliveHeroes();
        if(alive_heroes.isEmpty()) {return;}

        // get target and attacker
        Monster attacking_monster = monster_party.getAliveMonsters().get(monster_index);
        Hero target_hero = alive_heroes.get(rand.nextInt(alive_heroes.size()));

        //Damage calc

        int mon_attack = attacking_monster.getStats().getAttack();
        double total_hero_dr = target_hero.getStats().getDamage_reduction() + target_hero.getJacket().getBuffStats().getDamage_reduction();
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

    private void monstersAttackTurn() {
        ArrayList<Monster> alive_monsters = monster_party.getAliveMonsters();
        for (int i= 0; i < alive_monsters.size(); i++) {
            monsterAttack(i);
        }

    }

    private void showBattleInfo() {
        ArrayList<Hero> alive_heroes = player_party.getAliveHeroes();
        ArrayList<Monster> alive_monsters = monster_party.getAliveMonsters();

        System.out.println("=== HEROES ===");
        for (int i = 0; i < alive_heroes.size(); i++) {
            Hero h = alive_heroes.get(i);
            System.out.println(
                    "[" + i + "] " + h.getName() +
                            " (Lvl " + h.getLevelObj().getCurrentLevel() + ")" +
                            " | HP: " + h.getStats().getHealth() +
                            " | MP: " + h.getStats().getMana()
            );
        }

        System.out.println("\n=== MONSTERS ===");
        for (int i = 0; i < alive_monsters.size(); i++) {
            Monster m = alive_monsters.get(i);
            System.out.println(
                    "[" + i + "] " + m.getName() +
                            " (Lvl " + m.getLevelObj().getCurrentLevel() + ")" +
                            " | HP: " + m.getStats().getHealth()
            );
        }

        System.out.println();
    }

    // for each hero
    private void showBattleOptions(Hero hero, int monster_index) {
        boolean use_turn = false;
        do {
            System.out.println("Battle options");
            System.out.println("1. View battle status\n2. Attack the monster\n3. Use spell on monster\n4. Use potion on hero");
            int choice = ui.askInt();
            switch (choice) {
                case 1:
                    //show battle info
                    showBattleInfo();
                    break;
                case 2:
                    // get current hero in rotation index
                    // get monster target choice
                    use_turn = heroAttackWithWeapon(hero,monster_index);
                    break;
                case 3:
                    // get current hero in rotation index
                    // get monster target choice
                    use_turn = heroAttackWithSpell(hero,monster_index);
                    break;
                case 4:
                    //get current hero in rotation index
                    use_turn = heroUsePotion(hero);
                    break;

                default:
                    System.out.println("Invalid choice! Pick again");
                    break;
            }
        } while (!use_turn);
    }

    private boolean calculateDodge(double number) {
        double hitting_chance =  1 - number;
        double attack_roll = Math.random();
        return attack_roll <= hitting_chance;
    }

    

    private void rewardNewlyDeadMonsters() {
        ArrayList<Monster> dead_monsters = monster_party.getDeadMonsters();
        ArrayList<Hero> aliveHeroes = player_party.getAliveHeroes();

        if (dead_monsters.isEmpty() || aliveHeroes.isEmpty()) {
            return;
        }

        for (Monster m : dead_monsters) {
            if (rewarded_monsters.contains(m)) {
                continue;
            }
            int goldDrop = m.getGoldDrop();
            int expDrop  = m.getExperienceDrop();

            int goldPerHero = goldDrop / aliveHeroes.size();
            int expPerHero  = expDrop / aliveHeroes.size();

            for (Hero h : aliveHeroes) {
                h.getWallet().addGold(goldPerHero);
                h.gainExperiencePoints(expPerHero);
            }

            rewarded_monsters.add(m);
        }
    }

}
