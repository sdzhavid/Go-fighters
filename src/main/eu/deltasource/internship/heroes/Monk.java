package eu.deltasource.internship.heroes;

import eu.deltasource.internship.heroes.special.abilities.SpecialDefense;

/**
 * A monk which is a {@link Hero} with a special defense.
 * <p>
 * The special defense is a 30% chance to avoid all incoming damage from an attack.
 * </p>
 *
 * @author Samir Dzhavid
 * @version January 2021
 * @see Hero
 */
public class Monk extends Hero implements SpecialDefense {

    private static final double DODGE_CHANCE = 0.3;

    private String heroType;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Monk(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints);
        this.heroType = "Monk";
    }

    @Override
    public String getHeroType() {
        return heroType;
    }

    /**
     * Defends the hero, applying the armor resistance of the hero to the attack.
     * Also has {@see DODGE_CHANCE} chance to avoid all incoming damage for this round.
     *
     * @param damageFromAttackBeforeDefense the points dealt by the attack of the opposing Hero
     * @return the actual damage inflicted after armor resistance
     */
    @Override
    public int defend(int damageFromAttackBeforeDefense) {
        if (getRandomChance() <= DODGE_CHANCE) {
            specialDefense();
        }
        return super.defend(damageFromAttackBeforeDefense);
    }

    /**
     * Defends the hero with the special defense.
     * Reduces the damage taken by the attack to 0.
     *
     * @return the actual damage after the special defense
     */
    @Override
    public int specialDefense() {
        return super.defend(0);
    }
}
