
import Game.Launcher;
import Game.MonstersVsHeroes;


public class Main {

    public static void main(String[] args) {
        MonstersVsHeroes game = new MonstersVsHeroes();
        Launcher launcher = new Launcher();
        launcher.register(game).start();

    }
}
