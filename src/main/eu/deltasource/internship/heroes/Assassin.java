package eu.deltasource.internship.heroes;

public class Assassin extends Hero {

    private static final double CRITICAL_PERCENTAGE = 0.3;
    private static final double CRITICAL_HIT_INCREASE = 3;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Assassin(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints);
    }

    /**
     * Attacks the other hero with a 30% chance to do a critical hit
     * A critical hit is 300% of the normal damage dealt
     *
     * @return the damage inflicted upon the other hero.
     */
    @Override
    public int attack() {
        if (getRandomChance() <= CRITICAL_PERCENTAGE) {
            return (int) (super.attack() * CRITICAL_HIT_INCREASE);
        } else {
            return super.attack();
        }
    }
}
