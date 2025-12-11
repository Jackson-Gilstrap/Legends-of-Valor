package Utility;

/**
 * Represents player colors with their ANSI escape codes for console output.
 */
public enum Color {
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    GREEN("\u001B[32m"),
    LIGHTGREEN("\u001B[92m"),
    YELLOW("\u001B[33m"),
    CYAN("\u001B[36m"),
    MAGENTA("\u001B[35m"),
    WHITE("\u001B[37m"),
    BLACK("\u001B[30m"),
    NONE(""),
    RESET("\u001B[0m"); // used to reset color

    private final String ansiCode;

    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    /**
     * Get ANSI color code for this color.
     */
    public String getAnsiCode() {
        return ansiCode;
    }

    /**
     * Reset ANSI code (useful for restoring default console color).
     */
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
