package Game;


public class InputHandler {

    GameUI ui = new GameUI();
    public String getInput() {
        printValidCommands();
        while (true) {
            String choice = ui.askOneWord("Pick letter").toUpperCase();

            switch (choice) {
                case "W":
                case "S":
                case "A":
                case "D":
                case "Q":
                case "I":
                case "T":
                case "F":
                    return choice;
                default:
                    printValidCommands();
                    System.out.println("Invalid choice - Input again");

            }
        }

    }

    private void printValidCommands() {

        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃  W/A/S/D Move | F Interact | I Info | T Travel | Q Quit   ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

    }


}
