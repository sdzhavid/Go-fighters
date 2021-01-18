package eu.deltasource.internship.heroes;

import eu.deltasource.internship.arenas.Arena;
import eu.deltasource.internship.exceptions.InvalidHeroConstructorException;

import java.util.Random;

/**
 * Provides a partial functionality required for {@link Hero}s.
 * <p>
 * Every Hero has 3 attributes attackPoints, healthPoints and armorPoints.
 * A hero can attack, defend and enter an {@link Arena}
 * </p>
 * <p>
 * Methods that may be overridden for each Hero are
 * {@link Hero#attack()} and {@link Hero#defend(int)}.
 * </p>
 */
public abstract class Hero {

    private Random random = new Random();
    private int attackPoints;
    private int healthPoints;
    private int armorPoints;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Hero(int attackPoints, int healthPoints, int armorPoints) {
        validateHero(attackPoints, healthPoints, armorPoints);
        this.attackPoints = attackPoints;
        this.healthPoints = healthPoints;
        this.armorPoints = armorPoints;
    }

    /**
     * Returns the attack points of a Hero
     *
     * @return the attack points of the Hero
     */
    public int getAttackPoints() {
        return attackPoints;
    }

    /**
     * Returns the armor points of a Hero
     *
     * @return the armor points of the Hero
     */
    public int getArmorPoints() {
        return armorPoints;
    }

    /**
     * Returns the health points of a hero
     *
     * @return the armor points of the Hero
     */

    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Reduces the health points by the damage dealt from an attack.
     *
     * @param damageDealt the actual damage after resistance
     */
    public void reduceHealthPoints(int damageDealt) {
        if (damageDealt >= 0) {
            healthPoints = healthPoints - damageDealt;
        }
    }

    /**
     * Enters an {@link Arena} in order to fight
     *
     * @param arenaToBeJoined the arena to be joined
     */
    protected void enterArena(Arena arenaToBeJoined) {
        return;
    }

    /**
     * Generates a random percentage between 80% and 120%
     *
     * @return the random percentage,
     */
    public double getRandomPercentageBetween80And120() {
        double randomPercentageOfDamageInflicted = random.nextInt(120 - 80 + 1) + 80;
        randomPercentageOfDamageInflicted = randomPercentageOfDamageInflicted / 100;
        return randomPercentageOfDamageInflicted;
    }

    /**
     * Validates the {@link Hero} constructor.
     *
     * @param attackPoints to be checked
     * @param healthPoints to be checked
     * @param armorPoints  to be chechked
     * @throws InvalidHeroConstructorException when a hero isn't created with proper properties.
     */
    private void validateHero(int attackPoints, int healthPoints, int armorPoints)
            throws InvalidHeroConstructorException {
        if (attackPoints == 0) {
            throw new InvalidHeroConstructorException("A hero can't have zero attackPoints");
        }
        if (attackPoints < 0) {
            throw new InvalidHeroConstructorException("A hero can't have negative attackPoints");
        }
        if (healthPoints == 0) {
            throw new InvalidHeroConstructorException("A hero can't have zero healthPoints when created.");
        }
        if (healthPoints < 0) {
            throw new InvalidHeroConstructorException("A hero can't have negative healthPoints when created");
        }
        if (armorPoints < 0) {
            throw new InvalidHeroConstructorException("A hero can't have negative armorPoints");
        }
    }

    /**
     * Defends the hero from an attack
     * Uses defensePoints as a resistance tool to measure the health points that should be taken
     *
     * @param damageFromAttackBeforeDefense the points dealt by the attack of the opposing Hero
     * @return the damage dealt to the hero
     */
    public int defend(int damageFromAttackBeforeDefense) {
        int damageInflicted = damageFromAttackBeforeDefense - (int) (getArmorPoints() * getRandomPercentageBetween80And120());
        if (damageInflicted < 0) {
            damageInflicted = 0;
            return damageInflicted;
        }
        reduceHealthPoints(damageInflicted);
        return damageInflicted;
    }

    /**
     * Attacks another hero
     *
     * @return the Points that will be used in {@link #defend(int)} to take health points away from the attacked Hero.
     */
    public int attack() {
        int damageBeforeDefense = (int) (getAttackPoints() * getRandomPercentageBetween80And120());
        return damageBeforeDefense;
    }

    /**
     * Returns a random number between 0 and 1 representing percentage
     * Used in Heroes with special abilities which are based on percentage
     *
     * @return The random number
     */
    public double getRandomChance() {
        int randomChance = 100;
        double dropNumber = random.nextInt(randomChance);
        return dropNumber / 100;
    }
}
