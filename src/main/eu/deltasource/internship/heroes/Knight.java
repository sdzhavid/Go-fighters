package eu.deltasource.internship.heroes;

public class Knight extends Hero {

    private static final double BLOCK_CHANCE = 0.2;
    private static final double CRITICAL_PERCENTAGE = 0.1;
    private static final double CRITICAL_HIT_INCREASE = 2;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Knight(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints);
    }

    /**
     * Attacks the other hero with a 10% chance to do a critical hit
     * A critical hit is 200% of the normal damage dealt
     *
     * @return the damage inflicted upon the other hero.
     */
    @Override
    public int attack() {
        if (getRandomChance() <= CRITICAL_PERCENTAGE) {
            return (int) (super.attack() * CRITICAL_HIT_INCREASE);
        }
        return super.attack();
    }

    /**
     * Defends the hero, applying the armor resistance of the hero to the attack.
     * Also has 20% chance to block all incoming damage for this round.
     *
     * @param damageFromAttackBeforeDefense the points dealt by the attack of the opposing Hero
     * @return the actual damage inflicted after armor resistance
     */
    @Override
    public int defend(int damageFromAttackBeforeDefense) {
        if (getRandomChance() <= BLOCK_CHANCE) {
            return super.defend(0);
        }
        return super.defend(damageFromAttackBeforeDefense);
    }
}
