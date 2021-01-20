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
     * The game continues until one of the heroes has died which is done by {@link GameEngine#HeroIsKilled(Hero, Hero)}
     * Each round is printed to the console by {@link GameEngine#printRound(Hero, int, Hero)}
     *
     * @param firstHero  to enter the arena
     * @param secondHero to enter the arena
     * @throws InterruptedException if the game was been interrupted during the fight.
     */
    public void startGame(Hero firstHero, Hero secondHero) throws InterruptedException {
        while (isGameOn) {
            Thread.sleep(TIME_BETWEEN_ROUNDS_MILLISECONDS);

            int damageDealtByFirstHero = secondHero.defend(firstHero.attack());
            System.out.println(printRound(firstHero, damageDealtByFirstHero, secondHero));
            if (HeroIsKilled(secondHero, firstHero)) break;

            int damageDealtBySecondHero = firstHero.defend(secondHero.attack());
            System.out.println(printRound(secondHero, damageDealtBySecondHero, firstHero));
            System.out.println("\n");
            if (HeroIsKilled(firstHero, secondHero)) break;
        }
    }

    /**
     * Performs a check to decide whether or not a hero has been killed.
     *
     * @param heroToBeCheckedIfDead is the hero which might be killed
     * @param heroWhichHasKilled    is the hero which have killed their enemy.
     * @return a boolean which describes if a hero is dead or not; true = alive, false = dead.
     */
    private boolean HeroIsKilled(Hero heroToBeCheckedIfDead, Hero heroWhichHasKilled) {
        if (!heroToBeCheckedIfDead.isHeroAlive()) {
            System.out.println(heroWhichHasKilled.getHeroType() + " won the game");
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
    private String printRound(Hero attackingHero, int damageDealt, Hero defendingHero) {
        StringBuilder arenaRoundStringBuilder = new StringBuilder();
        arenaRoundStringBuilder
                .append(attackingHero.getHeroType())
                .append(" attacked for ").append(damageDealt).append(", ")
                .append(defendingHero.getHeroType()).append("'s remaining health is ")
                .append(defendingHero.getHealthPoints());
        String arenaRoundDetails = arenaRoundStringBuilder.toString();
        return arenaRoundDetails;
    }
}