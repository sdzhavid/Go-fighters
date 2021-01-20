package eu.deltasource.internship.heroes_tests;

import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    private Warrior warrior;
    private double LOWER_LIMIT_PERCENTAGE = 0.8;
    private double UPPER_LIMIT_PERCENTAGE = 1.2;

    @BeforeEach
    void initializeWarrior() {
        warrior = new Warrior(1000, 100, 30);
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
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void warriorNormalDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = warrior.getHealthPoints();

        // When
        warrior.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                warrior.getArmorPoints() * LOWER_LIMIT_PERCENTAGE));

        int expectedMaxHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                warrior.getArmorPoints() * UPPER_LIMIT_PERCENTAGE));

        int actualHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                warrior.getArmorPoints() * warrior.getRandomPercentageBetween80And120()));

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining &&
                actualHealthRemaining <= expectedMaxHealthRemaining);
    }
}