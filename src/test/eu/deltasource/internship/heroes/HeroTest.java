package eu.deltasource.internship.heroes;

import eu.deltasource.internship.exceptions.InvalidPassedParametersToHeroException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void testIfHeroAttackPointsAreZeroWhenCreated() {
        // Given
        Executable heroZeroAttackPointsCreation = () -> new Warrior(0, 200, 300);

        // When
        Exception actualException = assertThrows(InvalidPassedParametersToHeroException.class,
                heroZeroAttackPointsCreation);

        // Then
        String actualMessage = actualException.getMessage();
        String expectedMessage = "A hero can't have zero attackPoints";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void testIfHeroAttackPointsAreNegativeWhenCreated() {
        // Give
        Executable heroNegativeAttackPointsCreation = () -> new Assassin(-2, 200, 300);

        // When
        Exception actualException = assertThrows(InvalidPassedParametersToHeroException.class,
                heroNegativeAttackPointsCreation);

        // Then
        String actualMessage = actualException.getMessage();
        String expectedMessage = "A hero can't have negative attackPoints";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void testIfHeroHealthPointsAreZeroWhenCreated() {
        // Give
        Executable heroZeroHealthPointsCreation = () -> new Assassin(30, 0, 300);

        // When
        Exception actualException = assertThrows(InvalidPassedParametersToHeroException.class,
                heroZeroHealthPointsCreation);

        // Then
        String actualMessage = actualException.getMessage();
        String expectedMessage = "A hero can't have zero healthPoints when created.";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void testIfHeroHealthPointsAreNegativeWhenCreated() {
        // Give
        Executable heroNegativeHealthPointsCreation = () -> new Assassin(30, -2, 300);

        // When
        Exception actualException = assertThrows(InvalidPassedParametersToHeroException.class,
                heroNegativeHealthPointsCreation);

        // Then
        String actualMessage = actualException.getMessage();
        String expectedMessage = "A hero can't have negative healthPoints when created";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void testIfHeroArmorPointsAreNegative() {
        // Give
        Executable heroNegativeArmorPointsCreation = () -> new Assassin(30, 300, -3);

        // When
        Exception actualException = assertThrows(InvalidPassedParametersToHeroException.class,
                heroNegativeArmorPointsCreation);

        // Then
        String actualMessage = actualException.getMessage();
        String expectedMessage = "A hero can't have negative armorPoints";
        Assertions.assertEquals(actualMessage, expectedMessage);
    }
}
