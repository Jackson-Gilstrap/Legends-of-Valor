/**
 * Input handler that maps user commands to their executable implementations.
 */
package Controllers;

import Game.GameUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Commands.*;

public class InputHandler {
    private final Map<String, Command> commandMap = new HashMap<>();    // the commands map
    private final GameUI ui;

    public InputHandler(GameUI ui) {
        this.ui = ui;
    }

    public InputHandler register(String key, Command cmd) {
        key = key.toUpperCase();
        if (commandMap.containsKey(key)) {
            throw new IllegalArgumentException("Command for key '" + key + "' is already registered.");
        }
        commandMap.put(key, cmd);
        return this;
    }


    public String getInput() {
        return ui.askOneWord("Enter Command > ").toUpperCase();
    }

    public boolean handleCommand(String key) {
        Command cmd = commandMap.get(key.toUpperCase());
        if (cmd == null) {
            System.out.println("Invalid command.");
            return false;
        }
        return cmd.execute();
    }

    public void printValidCommands() {
        int commandsPerLine = 5;
        int count = 0;

        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder("┃ ");

        for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
            String key = entry.getKey();
            Command command = entry.getValue();

            currentLine.append(String.format("%s (%s)", key, command.name())).append(" | ");
            count++;

            if (count % commandsPerLine == 0) {
                currentLine.setLength(currentLine.length() - 3);
                currentLine.append(" ┃");
                lines.add(currentLine.toString());
                currentLine = new StringBuilder("┃ ");
            }
        }

        if (currentLine.length() > 3) {
            currentLine.setLength(currentLine.length() - 3);
            currentLine.append(" ┃");
            lines.add(currentLine.toString());
        }

        int maxWidth = 0;
        for (String line : lines) {
            if (line.length() > maxWidth) maxWidth = line.length();
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.length() < maxWidth) {
                int pad = maxWidth - line.length();
                line = line.substring(0, line.length() - 1) + repeatChar(' ', pad) + "┃";
                lines.set(i, line);
            }
        }

        String top = "┏" + repeatChar('━', maxWidth - 2) + "┓";
        System.out.println(top);

        // contents
        for (String line : lines) {
            System.out.println(line);
        }

        // bottom border
        String bottom = "┗" + repeatChar('━', maxWidth - 2) + "┛";
        System.out.println(bottom);
    }


    /**
     * String.repeat()
     */
    private static String repeatChar(char c, int count) {
        char[] chars = new char[count];
        for (int i = 0; i < count; i++) {
            chars[i] = c;
        }
        return new String(chars);
    }


}
