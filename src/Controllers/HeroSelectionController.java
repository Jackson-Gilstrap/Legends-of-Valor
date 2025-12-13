package Controllers;

import Entities.Hero;
import Game.GameUI;
import Parties.Party;
import WorldSets.Maps.World;

import java.util.List;

public class HeroSelectionController {

    private final GameUI ui;
    private final World worldMap;

    private final List<Hero> warriors;
    private final List<Hero> paladins;
    private final List<Hero> sorcerers;

    private static final int PARTY_CAPACITY = 3;

    public HeroSelectionController(GameUI ui, World worldMap, List<Hero> warriors, List<Hero> paladins, List<Hero> sorcerers) {
        this.ui = ui;
        this.worldMap = worldMap;
        this.warriors = warriors;
        this.paladins = paladins;
        this.sorcerers = sorcerers;
    }

    public void startSelectionMenu() {

        Party party = worldMap.getPlayerParty();

        System.out.println("MAX PARTY SIZE = " + PARTY_CAPACITY);

        while (true) {

            if (party.size() >= PARTY_CAPACITY) {
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
                    if (party.size() == 0) {
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
        worldMap.getPlayerParty().add(list.get(index));
        System.out.println(list.get(index).getName() + " has been added to the party");
        System.out.println();
        list.remove(index);
    }
}
