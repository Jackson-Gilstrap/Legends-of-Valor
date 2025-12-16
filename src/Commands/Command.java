/**
 * Contract for executable game commands.
 */
package Commands;

public interface Command {
    boolean execute();

    String name();
}
