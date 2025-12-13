package Controllers;

import Game.GameUI;
import java.util.HashMap;
import java.util.Map;

import Commands.*;

public class LVInputHandler {
    private final Map<String, Command> commandMap = new HashMap<>();    // the commands map
    private final GameUI ui;

    public LVInputHandler(GameUI ui) {
        this.ui = ui;
    }

    public LVInputHandler register(String key, Command cmd) {
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
        StringBuilder sb = new StringBuilder("┃ ");

        for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
            String key = entry.getKey();
            Command command = entry.getValue();
            sb.append(String.format("%s (%s)", key, command.name())).append(" | ");
        }

        if (sb.length() > 3) sb.setLength(sb.length() - 3);
        sb.append(" ┃");

        int totalWidth = sb.length();

        String repeat = repeatChar('━', totalWidth - 2);

        String top = "┏" + repeat + "┓";
        String bottom = "┗" + repeat + "┛";

        System.out.println(top);
        System.out.println(sb);
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
