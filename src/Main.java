
import Game.Launcher;
import Game.MonstersVsHeroes;
import Game.LegendsOfValor;

// Main.java
// Handles execution of program
public class Main {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher
                .register(new MonstersVsHeroes())
                .register(new LegendsOfValor())
                .start();

    }
}
