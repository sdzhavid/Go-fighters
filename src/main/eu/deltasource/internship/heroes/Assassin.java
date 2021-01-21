package eu.deltasource.internship.heroes;

import eu.deltasource.internship.heroes.special.abilities.SpecialAttack;

/**
 * An assassin which is a {@link Hero} with a special attack.
 * <p>
 * The special attack is a 30% chance to inflict 300% damage of the base attack.
 * </p>
 *
 * @author Samir Dzhavid
 * @version January 2021
 * @see Hero
 */
public class Assassin extends Hero implements SpecialAttack {

    private static final double CRITICAL_PERCENTAGE = 0.3;
    private static final double CRITICAL_HIT_MULTIPLIER = 3;

    private String heroType;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Assassin(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints);
        this.heroType = this.getClass().getSimpleName();
    }

    /**
     * Creates an assassin with default values for {@see attackPoints}, {@see healthPoints} and {@see armorPoints}
     */
    public Assassin() {
        this(150, 1000, 100);
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
        if (isACriticalAttack(CRITICAL_PERCENTAGE)) {
            return specialAttack();
        }
        return super.attack();
    }

    /**
     * Attacks a hero with the special attack
     * Inflicting damage multiplied by {@see CRITICAL_HIT_INCREASE}
     *
     * @return the damage inflicted by the special attack.
     */
    @Override
    public int specialAttack() {
        return (int) (super.attack() * CRITICAL_HIT_MULTIPLIER);
    }
}
