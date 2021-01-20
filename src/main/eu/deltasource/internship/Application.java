package eu.deltasource.internship;

import eu.deltasource.internship.heroes.Monk;
import eu.deltasource.internship.heroes.Shaman;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame(new Monk(300, 2000, 100),
                new Shaman(400,1000,50));
    }
}
