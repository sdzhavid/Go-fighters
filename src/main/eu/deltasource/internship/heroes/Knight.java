package eu.deltasource.internship.heroes;

import eu.deltasource.internship.heroes.special.abilities.SpecialAttack;
import eu.deltasource.internship.heroes.special.abilities.SpecialDefense;

/**
 * A knight which is a {@link Hero} with a special attack and a special defense.
 * <p>
 * The special attack is a 10% chance to inflict 200% damage of the base attack.
 * The special defense is a 20% chance to block all incoming damage from an attack.
 * </p>
 *
 * @author Samir Dzhavid
 * @version January 2021
 * @see Hero
 */
public class Knight extends Hero implements SpecialAttack, SpecialDefense {

    private static final double BLOCK_CHANCE = 0.2;
    private static final double CRITICAL_PERCENTAGE = 0.1;
    private static final double CRITICAL_HIT_INCREASE = 2;

    private String heroType;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Knight(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints);
        this.heroType = "Knight";
    }

    @Override
    public String getHeroType() {
        return heroType;
    }

    /**
     * Attacks the other hero with a {@see CRITICAL_PERCENTAGE} chance to do a critical hit
     * A critical hit is {@see CRITICAL_HIT_INCREASE} of the normal damage dealt
     *
     * @return the damage inflicted upon the other hero.
     */
    @Override
    public int attack() {
        if (getRandomChance() <= CRITICAL_PERCENTAGE) {
            specialAttack();
        }
        return super.attack();
    }

    /**
     * Defends the hero, applying the armor resistance of the hero to the attack.
     * Also has {@see BLOCK_CHANCE} chance to block all incoming damage for this round.
     *
     * @param damageFromAttackBeforeDefense the points dealt by the attack of the opposing Hero
     * @return the actual damage inflicted after armor resistance
     */
    @Override
    public int defend(int damageFromAttackBeforeDefense) {
        if (getRandomChance() <= BLOCK_CHANCE) {
            specialDefense();
        }
        return super.defend(damageFromAttackBeforeDefense);
    }

    /**
     * Attacks a hero with the special attack
     * Inflicting damage multiplied by {@see CRITICAL_HIT_INCREASE}
     *
     * @return the damage inflicted by the special attack.
     */
    @Override
    public int specialAttack() {
        return (int) (super.attack() * CRITICAL_HIT_INCREASE);
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
