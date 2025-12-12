package Controllers;

import Entities.Hero;
import Game.GameUI;
import Parties.Party;

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

    public void startSelectionMenu() {

        System.out.println("MAX PARTY SIZE = " + PARTY_CAPACITY);

        while (true) {

            if (party.getPartySize() >= PARTY_CAPACITY) {
                System.out.println("Party is full. Starting game...\n");
                return;
            }

            System.out.println("\n--- HERO SELECTION MENU ---\n");
            System.out.println("0. Start Game  |  1. Add Warrior  |  2. Add Paladin  |  3. Add Sorcerer\nSelect: ");

            int input = ui.askInt();

            switch (input) {

                case 1:
                    addHeroToParty(warriors);
                    break;

                case 2:
                    addHeroToParty(paladins);
                    break;

                case 3:
                    addHeroToParty(sorcerers);
                    break;

                case 0:
                    if (party.getPartySize() == 0) {
                        System.out.println("You need at least one hero in your party.");
                        break;
                    }

                    party.getPartyInfo();
                    String confirm = ui.askOneWord("Start the game? (yes/no): ");

                    if (confirm.equalsIgnoreCase("yes")) {
                        System.out.println("Starting game!");
                        return;
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 0–3.");
            }
        }
    }

    private void addHeroToParty(List<Hero> list)  {

        System.out.println("\nChoose a hero to add to your party:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println();

        System.out.print("Enter choice (1 - " + list.size() + "): ");
        int input = ui.askInt();
        System.out.println();

        int index = input - 1;

        if (index < 0 || index >= list.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        party.addHeroToParty(list.get(index));
        System.out.println(list.get(index).getName() + " has been added to the party");
        System.out.println();
        list.remove(index);
    }
}
