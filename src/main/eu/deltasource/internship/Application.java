package eu.deltasource.internship;

import eu.deltasource.internship.heroes.Assassin;
import eu.deltasource.internship.heroes.Monk;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame(new Monk(), new Assassin());
    }
}
