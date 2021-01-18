package eu.deltasource.internship.heroes;

public class Monk extends Hero {

    private static final double DODGE_CHANCE = 0.3;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Monk(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints);
    }

    /**
     * Defends the hero, applying the armor resistance of the hero to the attack.
     * Also has 30% chance to avoid all incoming damage for this round.
     *
     * @param damageFromAttackBeforeDefense the points dealt by the attack of the opposing Hero
     * @return the actual damage inflicted after armor resistance
     */
    @Override
    public int defend(int damageFromAttackBeforeDefense) {
        if (getRandomChance() <= DODGE_CHANCE) {
            return super.defend(0);
        }
        return super.defend(damageFromAttackBeforeDefense);
    }
}
