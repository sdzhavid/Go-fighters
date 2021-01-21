package eu.deltasource.internship.heroes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    private Warrior warrior;
    private double LOWER_LIMIT_PERCENTAGE = 0.8;
    private double UPPER_LIMIT_PERCENTAGE = 1.2;
    int startingHealthPoints;

    @BeforeEach
    void initializeWarrior() {
        warrior = new Warrior(1000, 100, 30);
        startingHealthPoints = warrior.getHealthPoints();
    }

    @RepeatedTest(500)
    void warriorNormalAttackHeroShouldReturnProperDamageInflicted() {
        // Given

        // When
        warrior.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (warrior.getAttackPoints() * LOWER_LIMIT_PERCENTAGE);
        int expectedMaximumDamageBeforeDefense = (int) (warrior.getAttackPoints() * UPPER_LIMIT_PERCENTAGE);
        int actualDamageBeforeDefense = warrior.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense);
        assertTrue(actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void warriorNormalDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();

        // When
        warrior.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = getHealthAtBorder(LOWER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int expectedMaxHealthRemaining = getHealthAtBorder(UPPER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int actualHealthRemaining = getHealthAtBorder(warriorThatHasAttacked.getRandomPercentageBetween80And120(),
                damageInflictedBeforeDefense);

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining);
        assertTrue(actualHealthRemaining <= expectedMaxHealthRemaining);
    }

    private int getHealthAtBorder(double limitPercentage, int damageInflicted) {
        return (int) (startingHealthPoints - (damageInflicted - warrior.getArmorPoints() * limitPercentage));
    }
}
