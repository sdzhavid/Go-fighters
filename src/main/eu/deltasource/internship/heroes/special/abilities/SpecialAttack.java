package eu.deltasource.internship.heroes.special.abilities;

/**
 * An interface which implements a behaviour of a special attack.
 *
 * @author Samir Dzhavid
 * @version January 2021
 */
public interface SpecialAttack {

    /**
     * Attacks a hero with the special attack
     *
     * @return the damage inflicted by the special attack.
     */
    int specialAttack();
}
