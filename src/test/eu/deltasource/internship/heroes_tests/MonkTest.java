package eu.deltasource.internship.heroes_tests;

import eu.deltasource.internship.heroes.Hero;
import eu.deltasource.internship.heroes.Monk;
import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class MonkTest {
    private static final double LOWER_LIMIT_PERCENTAGE = 0.8;
    private static final double UPPER_LIMIT_PERCENTAGE = 1.2;
    Monk monk;

    @BeforeEach
    void initMonk() {
        monk = new Monk(100, 1000, 30);
    }

    @Test
    void testNoDamageTakenWhenMonkDodgedAttack() {
        // Given
        Hero hero = spy(monk);

        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = monk.getHealthPoints();

        // When
        when(hero.getRandomChance()).thenReturn(0.3);
        hero.defend(damageInflictedBeforeDefense);

        // Then
        int expectedHealthRemaining = startingHealthPoints;
        int actualRemainingHealth = hero.getHealthPoints();
        assertEquals(expectedHealthRemaining, actualRemainingHealth);
    }

    @RepeatedTest(500)
    void monkNormalAttackHeroShouldReturnProperDamageInflicted() {
        // Given

        // When
        monk.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (monk.getAttackPoints() * LOWER_LIMIT_PERCENTAGE);
        int expectedMaximumDamageBeforeDefense = (int) (monk.getAttackPoints() * UPPER_LIMIT_PERCENTAGE);
        int actualDamageBeforeDefense = monk.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void monkNormalDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = monk.getHealthPoints();

        // When
        monk.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                monk.getArmorPoints() * LOWER_LIMIT_PERCENTAGE));

        int expectedMaxHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                monk.getArmorPoints() * UPPER_LIMIT_PERCENTAGE));

        int actualHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                monk.getArmorPoints() * monk.getRandomPercentageBetween80And120()));

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining &&
                actualHealthRemaining <= expectedMaxHealthRemaining);
    }
}