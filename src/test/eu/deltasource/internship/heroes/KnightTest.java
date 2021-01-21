package eu.deltasource.internship.heroes;

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
    int startingHealthPoints;

    @BeforeEach
    void initializeKnight() {
        knight = new Knight(100, 1000, 40);
        startingHealthPoints = knight.getHealthPoints();
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
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense);
        assertTrue(actualDamageBeforeDefense<= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void knightNormalAttackHeroShouldReturnProperDamageInflicted() {
        // Given
        Hero hero = spy(knight);
        double chanceAboveCritical = 0.4;

        // When
        when(hero.getRandomChance()).thenReturn(chanceAboveCritical);
        hero.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (hero.getAttackPoints() * LOWER_LIMIT_PERCENTAGE);
        int expectedMaximumDamageBeforeDefense = (int) (hero.getAttackPoints() * UPPER_LIMIT_PERCENTAGE);
        int actualDamageBeforeDefense = hero.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense);
        assertTrue(actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void knightNormalDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();

        // When
        knight.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = getHealthAtBorder(LOWER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int expectedMaxHealthRemaining = getHealthAtBorder(UPPER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int actualHealthRemaining = getHealthAtBorder(knight.getRandomPercentageBetween80And120(),
                damageInflictedBeforeDefense);

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining);
        assertTrue(actualHealthRemaining <= expectedMaxHealthRemaining);
    }

    private int getHealthAtBorder(double limitPercentage, int damageInflicted){
        return (int) (startingHealthPoints - (damageInflicted - knight.getArmorPoints() * limitPercentage));
    }
}
