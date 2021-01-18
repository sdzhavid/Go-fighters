package eu.deltasource.internship.heroes_tests;

import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    Warrior warrior;


    @BeforeEach
    void initializeWarrior() {
        warrior = new Warrior(1000, 100, 30);
    }

    @RepeatedTest(500)
    void warriorAttackHeroShouldReturnProperDamageInflicted() {
        // Given

        // When
        warrior.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (warrior.getAttackPoints() * 0.8);
        int expectedMaximumDamageBeforeDefense = (int) (warrior.getAttackPoints() * 1.2);
        int actualDamageBeforeDefense = warrior.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void warriorDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = warrior.getHealthPoints();

        // When
        warrior.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                warrior.getArmorPoints() * 0.8));

        int expectedMaxHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                warrior.getArmorPoints() * 1.2));

        int actualHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                warrior.getArmorPoints() * warrior.getRandomPercentageBetween80And120()));

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining &&
                actualHealthRemaining <= expectedMaxHealthRemaining);
    }
}