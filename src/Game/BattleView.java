package Game;

import Entities.Hero;
import Entities.Monster;
import Items.Potion;
import Items.Spell;
import Parties.MonsterParty;
import Parties.Party;

import java.util.ArrayList;

/**
 * Presentation logic for battle-related messaging.
 */
public class BattleView {
    public void showBattleStatus(Party heroes, MonsterParty monsters) {
        ArrayList<Hero> aliveHeroes = heroes.getAliveHeroes();
        ArrayList<Monster> aliveMonsters = monsters.getAliveMonsters();

        System.out.println("=== HEROES ===");
        for (int i = 0; i < aliveHeroes.size(); i++) {
            Hero h = aliveHeroes.get(i);
            System.out.println(
                    "[" + i + "] " + h.getName() +
                            " (Lvl " + h.getLevelObj().getCurrentLevel() + ")" +
                            " | HP: " + h.getStats().getHealth() +
                            " | MP: " + h.getStats().getMana()
            );
        }

        System.out.println("\n=== MONSTERS ===");
        for (int i = 0; i < aliveMonsters.size(); i++) {
            Monster m = aliveMonsters.get(i);
            System.out.println(
                    "[" + i + "] " + m.getName() +
                            " (Lvl " + m.getLevelObj().getCurrentLevel() + ")" +
                            " | HP: " + m.getStats().getHealth()
            );
        }

        System.out.println();
    }

    public void printBattleStart() {
        System.out.println("Battle started!");
        System.out.println("Your party walks up to the monsters");
    }

    public void printInvalidTarget(Hero hero) {
        System.out.println(hero.getName() + " has no valid target.");
    }

    public void printWeaponAttack(Hero hero, Monster target, int damage) {
        System.out.println(hero.getName() + " attacked " + target.getName() + " and dealt " + damage + " damage!");
    }

    public void printAttackMiss(Hero hero, Monster target) {
        System.out.println(hero.getName() + " attacked " + target.getName() + " but missed!");
    }

    public void printSpellAttack(Hero hero, Monster target, Spell spell, int damage) {
        System.out.println(hero.getName() + " used " + spell.getName() + " on " + target.getName() + " dealing " + damage);
    }

    public void printSpellDebuff(Monster target, String statName) {
        System.out.println(target.getName() + "'s " + statName + " dropped");
    }

    public void printSpellUnavailable(Hero hero) {
        System.out.println(hero.getName() + " cannot cast a spell right now.");
    }

    public void printPotionUnavailable(Hero hero) {
        System.out.println(hero.getName() + " doesn't have a usable potion.");
    }

    public void printPotionUse(Hero hero, Potion potion) {
        System.out.println(hero.getName() + " used " + potion.getName());
    }

    public void printMonsterAttack(Monster monster, Hero target, int damage) {
        System.out.println(monster.getName() + " attacked " + target.getName() + " and dealt " + damage + " damage!");
    }

    public void printMonsterMiss(Monster monster, Hero target) {
        System.out.println(monster.getName() + " attacked " + target.getName() + " but missed!");
    }
}
