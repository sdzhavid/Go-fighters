package eu.deltasource.internship.heroes.special.abilities;

/**
 * An interface which implements a behaviour of a special defense.
 *
 * @author Samir Dzhavid
 * @version January 2021
 */
public interface SpecialDefense {

    /**
     * Defends the hero with the special defense.
     *
     * @return the actual damage inflicted after the special defense
     */
    int specialDefense();
}
