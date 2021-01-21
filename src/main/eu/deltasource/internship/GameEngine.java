package eu.deltasource.internship;

import eu.deltasource.internship.heroes.Hero;

/**
 * Controls and uses {@link Hero} to create a fight between the participants.
 *
 * <p>
 * The game goes in rounds in which every hero attacks and defends.
 * After each round the details of the round are printed to the console.
 * If a hero dies then the other is declared the winner.
 * </p>
 *
 * @author Samir Dzhavid
 * @version January 2021
 */
public class GameEngine {

    private static final int TIME_BETWEEN_ROUNDS_MILLISECONDS = 2000;
    private boolean isGameOn = true;

    /**
     * Starts the game by initializing the two heroes when given as parameters.
     * The game continues until one of the heroes has died which is done by {@link GameEngine#heroIsKilled(Hero, Hero)}
     * Each round is printed to the console by {@link GameEngine#printRound(Hero, int, Hero)}
     *
     * @param firstHero  to enter the arena
     * @param secondHero to enter the arena
     * @throws InterruptedException if the game was been interrupted during the fight.
     */
    public void startGame(Hero firstHero, Hero secondHero) throws InterruptedException {
        printStartGameMessage(firstHero, secondHero);
        while (isGameOn) {
            timeBetweenRounds(TIME_BETWEEN_ROUNDS_MILLISECONDS);

            int damageDealtByFirstHero = secondHero.defend(firstHero.attack());
            printRound(firstHero, damageDealtByFirstHero, secondHero);

            if (isHeroKilled(secondHero)) {
                printWinnerMessage(firstHero);
                break;
            }

            int damageDealtBySecondHero = firstHero.defend(secondHero.attack());
            printRound(secondHero, damageDealtBySecondHero, firstHero);

            if (isHeroKilled(firstHero)) {
                printWinnerMessage(secondHero);
                break;
            }
        }
    }

    /**
     * Performs a check to decide whether or not a hero has been killed.
     *
     * @param heroToBeCheckedIfDead is the hero which might be killed
     * @return a boolean which describes if a hero is dead or not; true = alive, false = dead.
     */
    private boolean isHeroKilled(Hero heroToBeCheckedIfDead) {
        if (!heroToBeCheckedIfDead.isHeroAlive()) {
            isGameOn = false;
            return true;
        }
        return false;
    }

    /**
     * Displays a message to the console of a round in the arena.
     *
     * @param attackingHero the attacking hero of the round
     * @param damageDealt   the damage dealt by the attacking hero
     * @param defendingHero the defending hero of the round.
     * @return the details of the round.
     */
    private void printRound(Hero attackingHero, int damageDealt, Hero defendingHero) {
        String arenaRoundDetails = attackingHero.getHeroType() +
                " attacked for " + damageDealt + ", " +
                defendingHero.getHeroType() + "'s remaining health is " +
                defendingHero.getHealthPoints();
        System.out.println(arenaRoundDetails);
    }

    /**
     * Displays a welcoming message when the game begins
     *
     * @param firstHero to be displayed
     * @param secondHero to be displayed
     */
    private void printStartGameMessage(Hero firstHero, Hero secondHero) {
        String gameStartMessage = "The fight is starting NOW!! Fight is between ";
        gameStartMessage += firstHero.getHeroType() + " and " + secondHero.getHeroType() + "!\n";
        System.out.println(gameStartMessage);
    }

    /**
     * Displays the winner of the fight
     *
     * @param heroWhichHasKilled the hero which has killed their opponent.
     */
    private void printWinnerMessage(Hero heroWhichHasKilled) {
        System.out.println(heroWhichHasKilled.getHeroType() + " won the game");
    }

    /**
     * This method makes a pause for a certain amount of time between the rounds.
     *
     * @param waitTime is the time to be waited in milliseconds
     * @throws InterruptedException when the sleeping process is interrupted.
     */
    private void timeBetweenRounds(int waitTime) throws InterruptedException {
        Thread.sleep(TIME_BETWEEN_ROUNDS_MILLISECONDS);
    }
}