package eu.deltasource.internship.exceptions;

/**
 * An exception for when the parameters passed to a Hero constructor are invalid.
 *
 * @author Samir Dzhavid
 * @version January 2021
 */

public class InvalidPassedParametersToHeroException extends RuntimeException {
    public InvalidPassedParametersToHeroException(String message) {
        super(message);
    }
}
