package eu.deltasource.internship;

import eu.deltasource.internship.heroes.Hero;
import eu.deltasource.internship.heroes.Monk;
import eu.deltasource.internship.heroes.Warrior;

public class GameEngine {
    private boolean isGameOn = true;
    private Hero firstHero;
    private Hero secondHero;

    public void startGame(int timeBetweenRounds) throws InterruptedException {
        while (isGameOn) {
            Thread.sleep(timeBetweenRounds);

            int damageDealt = secondHero.defend(firstHero.attack());
            System.out.println("Monk attacked for " + damageDealt +
                    " , Warrior's remaining health is " + secondHero.getHealthPoints());

            if (secondHero.getHealthPoints() <= 0) {
                System.out.println("First player won the game!");
                isGameOn = false;
                break;
            }

            int damageDealt2 = firstHero.defend(secondHero.attack());
            System.out.println("Warrior attacked for " + damageDealt2 +
                    " , Warrior1's remaining health is " + firstHero.getHealthPoints() + "\n");

            if (firstHero.getHealthPoints() <= 0) {
                System.out.println("Second player won the game!");
                isGameOn = false;
                break;
            }
        }
    }

    public void initGame(Hero hero1, Hero hero2) {
        firstHero = hero1;
        secondHero = hero2;
    }

    public static void main(String[] args) throws InterruptedException {
        GameEngine gameEngine = new GameEngine();
        gameEngine.initGame(new Monk(300, 1000, 40),
                new Warrior(200, 1000, 100));
        gameEngine.startGame(2000);
    }
}