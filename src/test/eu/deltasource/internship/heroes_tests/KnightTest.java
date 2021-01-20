package eu.deltasource.internship.heroes_tests;

import eu.deltasource.internship.heroes.Hero;
import eu.deltasource.internship.heroes.Knight;
import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class KnightTest {
    private Knight knight;
    private int DAMAGE_INCREASE_MULTIPLIER = 2;
    private double LOWER_LIMIT_PERCENTAGE = 0.8;
    private double UPPER_LIMIT_PERCENTAGE = 1.2;

    @BeforeEach
    void initKnight() {
        knight = new Knight(100, 1000, 40);
    }

    @RepeatedTest(100)
    void testNoDamageTakenWhenKnightBlockedAttack() {
        // Given
        Hero hero = spy(knight);

        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = knight.getHealthPoints();

        // When
        when(hero.getRandomChance()).thenReturn(0.2);
        hero.defend(damageInflictedBeforeDefense);

        // Then
        int expectedHealthRemaining = startingHealthPoints;
        int actualRemainingHealth = hero.getHealthPoints();
        assertEquals(expectedHealthRemaining, actualRemainingHealth);
    }

    @RepeatedTest(100)
    void knightCriticalAttackHeroShouldReturnProperDamageInflicted() {
        // Given
        Hero hero = spy(knight);

        // When
        when(hero.getRandomChance()).thenReturn(0.1);
        hero.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (hero.getAttackPoints() * LOWER_LIMIT_PERCENTAGE
                * DAMAGE_INCREASE_MULTIPLIER);
        int expectedMaximumDamageBeforeDefense = (int) (hero.getAttackPoints() * UPPER_LIMIT_PERCENTAGE
                * DAMAGE_INCREASE_MULTIPLIER);
        int actualDamageBeforeDefense = hero.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void knightNormalAttackHeroShouldReturnProperDamageInflicted() {
        // Given

        // When
        knight.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (knight.getAttackPoints() * LOWER_LIMIT_PERCENTAGE);
        int expectedMaximumDamageBeforeDefense = (int) (knight.getAttackPoints() * UPPER_LIMIT_PERCENTAGE);
        int actualDamageBeforeDefense = knight.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void knightNormalDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = knight.getHealthPoints();

        // When
        knight.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                knight.getArmorPoints() * LOWER_LIMIT_PERCENTAGE));

        int expectedMaxHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                knight.getArmorPoints() * UPPER_LIMIT_PERCENTAGE));

        int actualHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                knight.getArmorPoints() * knight.getRandomPercentageBetween80And120()));

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining &&
                actualHealthRemaining <= expectedMaxHealthRemaining);
    }
}