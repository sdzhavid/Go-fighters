package eu.deltasource.internship.exceptions;

/**
 * An exception for when a hero object is created with a null value.
 *
 * @author Samir Dzhavid
 * @version January 2021
 */
public class HeroNullException extends RuntimeException {
    public HeroNullException(String message) {
        super(message);
    }
}
