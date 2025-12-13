package Utility;

/**
 * Represents player colors with their ANSI escape codes for console output.
 */
public enum Color {
    RED("\u001B[31m", "\u001B[41m"),
    LIGHTRED("\u001B[91m", "\u001B[101m"),
    BLUE("\u001B[34m", "\u001B[44m"),
    LIGHTGREEN("\u001B[92m", "\u001B[102m"),
    GREEN("\u001B[32m", "\u001B[42m"),
    YELLOW("\u001B[33m", "\u001B[43m"),
    CYAN("\u001B[36m", "\u001B[46m"),
    MAGENTA("\u001B[35m", "\u001B[45m"),
    WHITE("\u001B[37m", "\u001B[47m"),
    BLACK("\u001B[30m", "\u001B[40m"),
    PURPLE("\u001B[95m", "\u001B[105m"),
    LIGHTGRAY("\u001B[37m", "\u001B[47m"),
    NONE("", ""),
    RESET("\u001B[0m", "\u001B[0m"); // used to reset color

    private final String ansiCode;
    private final String bgAnsiCode;

    Color(String ansiCode, String bgAnsiCode) {
        this.ansiCode = ansiCode;
        this.bgAnsiCode = bgAnsiCode;
    }

    public String getAnsiCode() {
        return ansiCode;
    }

    public String getBgAnsiCode() {
        return bgAnsiCode;
    }

    public static String reset() {
        return RESET.ansiCode;
    }
    /**
     * Check whether the given string corresponds to a valid Color name.
     */
    public static boolean isColor(String input) {
        if (input == null) return false;
        try {
            Color.valueOf(input.trim().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Convert a string to a Color enum value (case-insensitive).
     * Returns null if no match.
     */
    public static Color fromString(String input) {
        if (input == null) return null;
        try {
            return Color.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Wrap the given text with the ANSI color code of this Color.
     * Example: Color.colorize("Hello", Color.RED)
     * will return a red-colored "Hello" in the console.
     */
    public static String colorize(String text, Color color) {
        if (text == null || color == null) return text;
        return color.getAnsiCode() + text + RESET.getAnsiCode();
    }

}
