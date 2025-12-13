package Controllers;

import Entities.Hero;
import Game.GameUI;
import Parties.Party;
import WorldSets.MapSet;
import WorldSets.Maps.World;

import java.util.List;

/**
 * This class receive lists of heroes and let the player to choose some of them.
 */
public class HeroSelectionController {

    private final GameUI ui;
    private final Party party;
    private final List<Hero> warriors;
    private final List<Hero> paladins;
    private final List<Hero> sorcerers;

    private static final int PARTY_CAPACITY = 3;

    public HeroSelectionController(GameUI ui, Party party, List<Hero> warriors, List<Hero> paladins, List<Hero> sorcerers) {
        this.ui = ui;
        this.party = party;
        this.warriors = warriors;
        this.paladins = paladins;
        this.sorcerers = sorcerers;
    }

    public Hero select() {

        System.out.println("MAX PARTY SIZE = " + PARTY_CAPACITY);


        if (party.size() >= PARTY_CAPACITY) {
            System.out.println("Party is full. Starting game...\n");
            return null;
        }
        
        while(true){
            printSelectionMenu();

            int input = ui.askInt();

            switch (input) {

                case 1:
                    return chooseHeroFrom(warriors);

                case 2:
                    return chooseHeroFrom(paladins);

                case 3:
                    return chooseHeroFrom(sorcerers);

                case 0:
                    return null;

                default:
                    System.out.println("Invalid choice. Please enter 0–3.");
            }
        }
    }

    private Hero chooseHeroFrom(List<Hero> list)  {
        Hero h = null;
        while(h == null){
            System.out.println("\nChoose a hero to add to your party:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + ". " + list.get(i));
            }
            System.out.println();

            System.out.print("Enter choice (1 - " + list.size() + "): ");
            int input = ui.askInt();
            System.out.println();

            int index = input - 1;

            // out of range
            if (index < 0 || index >= list.size()) {
                System.out.println("Invalid choice.");
                continue;
            }

            h = list.get(index);
            System.out.println(list.get(index).getName() + " has been added to the party");
            System.out.println();
            list.remove(index);
        }
        return h;
    }

    private void printSelectionMenu(){
        System.out.println("\n--- HERO SELECTION MENU ---\n");
        System.out.println("0. Start Game  |  1. Add Warrior  |  2. Add Paladin  |  3. Add Sorcerer\nSelect: ");
    }
}
