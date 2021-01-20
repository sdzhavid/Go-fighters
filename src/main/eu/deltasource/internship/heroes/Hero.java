package eu.deltasource.internship.heroes;

import eu.deltasource.internship.exceptions.InvalidHeroConstructorException;

import java.util.Random;

/**
 * Provides a partial functionality required for {@link Hero}s.
 * <p>
 * Every Hero has 3 attributes attackPoints, healthPoints and armorPoints.
 * </p>
 * <p>
 * Methods that may be overridden for each Hero are
 * {@link Hero#attack()} and {@link Hero#defend(int)}.
 * </p>
 */
public abstract class Hero {

    private final static int numberToDetermineRandomBorder = 41;

    private Random random = new Random();
    private int attackPoints;
    private int healthPoints;
    private int armorPoints;
    private boolean isHeroAlive;

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
        isHeroAlive = true;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public boolean isHeroAlive() {
        if (healthPoints <= 0) {
            return false;
        }
        return isHeroAlive;
    }

    public void setHeroAlive(boolean isDead) {
        this.isHeroAlive = isDead;
    }

    public abstract String getHeroType();

    /**
     * Reduces the health points by the damage dealt from an attack.
     *
     * @param damageDealt the actual damage after resistance
     */
    public void reduceHealthPoints(int damageDealt) {
        if (damageDealt >= 0) {
            healthPoints = healthPoints - damageDealt;
        }
        if (healthPoints < 0) {
            healthPoints = 0;
            setHeroAlive(false);
        }
    }

    /**
     * Generates a random percentage between 80% and 120%
     *
     * @return the random percentage,
     */
    public double getRandomPercentageBetween80And120() {
        double randomPercentageOfDamageInflicted = random.nextInt(numberToDetermineRandomBorder) + 80;
        return getValueInPercents(randomPercentageOfDamageInflicted);
    }

    /**
     * Converts the value from a whole number to percents.
     *
     * @param randomPercentage the percentage as a whole number
     * @return the value in percents
     */
    public double getValueInPercents(double randomPercentage) {
        return randomPercentage / 100;
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
        validateAttackPoints(attackPoints);
        validateHealthPoints(healthPoints);
        validateArmorPoints(armorPoints);
    }

    /**
     * Validates the {@link Hero} attack points
     *
     * @param attackPoints to be checked
     * @throws InvalidHeroConstructorException when a hero is initialized with 0 or negative attack points.
     */
    private void validateAttackPoints(int attackPoints) throws InvalidHeroConstructorException {
        if (attackPoints == 0) {
            throw new InvalidHeroConstructorException("A hero can't have zero attackPoints");
        }
        if (attackPoints < 0) {
            throw new InvalidHeroConstructorException("A hero can't have negative attackPoints");
        }
    }

    /**
     * Validates the {@link Hero} health points.
     *
     * @param healthPoints to be checked
     * @throws InvalidHeroConstructorException when a hero is initialized with 0 or negative health points.
     */
    private void validateHealthPoints(int healthPoints) throws InvalidHeroConstructorException {
        if (healthPoints == 0) {
            throw new InvalidHeroConstructorException("A hero can't have zero healthPoints when created.");
        }
        if (healthPoints < 0) {
            throw new InvalidHeroConstructorException("A hero can't have negative healthPoints when created");
        }
    }

    /**
     * Validates the {@link Hero} armor points.
     *
     * @param armorPoints to be checked
     * @throws InvalidHeroConstructorException when a hero is initialized with negative armor points.
     */
    private void validateArmorPoints(int armorPoints) throws InvalidHeroConstructorException {
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
        int damageInflicted = damageFromAttackBeforeDefense - getActualDefense();
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
        return getActualDamageToInflict();
    }

    /**
     * Returns a random number between 0 and 1 representing percentage
     * Used in Heroes with special abilities which are based on percentage
     *
     * @return The random number
     */
    public double getRandomChance() {
        int randomChance = 100;
        return getValueInPercents(randomChance);
    }

    /**
     * The actual damage that will be done in an attack.
     *
     * @return the actual damage inflicted
     */
    private int getActualDamageToInflict() {
        return (int) (getAttackPoints() * getRandomPercentageBetween80And120());
    }

    /**
     * The actual defense to be reduced from the actual attack.
     *
     * @return the actual defense to reduce the attack
     */
    private int getActualDefense() {
        return (int) (getArmorPoints() * getRandomPercentageBetween80And120());
    }
}
