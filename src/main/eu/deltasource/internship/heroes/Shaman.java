package eu.deltasource.internship.heroes;

import eu.deltasource.internship.heroes.special.abilities.SpecialAttack;

/**
 * A shaman is {@link Hero} with a special attack and defense.
 *
 * <p>
 * The special attack is a double attack.
 * Because of his strong attack the {@link Shaman} takes always {@see DAMAGE_TAKEN_INCREASE} damage from an attack.
 * </p>
 *
 * @author Samir Dzhavid
 * @version January 2021
 */
public class Shaman extends Hero implements SpecialAttack {

    private static final double DOUBLE_ATTACK_CHANCE = 0.7;
    private static final double DAMAGE_TAKEN_MULTIPLIER = 1.2;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Shaman(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints, HeroType.SHAMAN);
    }

    /**
     * Creates a shaman with default values for {@see attackPoints}, {@see healthPoints} and {@see armorPoints}
     */
    public Shaman() {
        this(100, 1000, 200);
    }

    /**
     * Attacks the other hero with a chance of a double attack of {@see DOUBLE_ATTACK_CHANCE}
     * A double attack is a normal attack with the damage multiplied by 2.
     *
     * @return damage inflicted upon the other hero
     */
    @Override
    public int attack() {
        if (isACriticalAttack(DOUBLE_ATTACK_CHANCE)) {
            return specialAttack();
        }
        return super.attack();
    }

    /**
     * Defends the hero, applying the armor resistance of the hero to the attack.
     * The damage is always increased by {@see DAMAGE_TAKEN_INCREASE}
     *
     * @param damageFromAttackBeforeDefense the points dealt by the attack of the opposing Hero
     * @return the damage dealt to the hero
     */
    @Override
    public int defend(int damageFromAttackBeforeDefense) {
        return (int) (super.defend(damageFromAttackBeforeDefense) * DAMAGE_TAKEN_MULTIPLIER);
    }

    /**
     * Attacks a hero with the special attack.
     * Inflicted damage is double the amount of the normal attack.
     *
     * @return inflicted by the special attack.
     */
    @Override
    public int specialAttack() {
        return super.attack() + super.attack();
    }
}
