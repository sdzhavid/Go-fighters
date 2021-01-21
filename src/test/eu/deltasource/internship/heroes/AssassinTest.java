package eu.deltasource.internship.heroes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssassinTest {
    private Assassin assassin;
    private int DAMAGE_INCREASE_MULTIPLIER = 3;
    private double LOWER_LIMIT_PERCENTAGE = 0.8;
    private double UPPER_LIMIT_PERCENTAGE = 1.2;
    private int startingHealthPoints;
    private Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);;

    @BeforeEach
    void initializeAssassin() {
        assassin = new Assassin(100, 1000, 30);
        startingHealthPoints = assassin.getHealthPoints();
    }

    @RepeatedTest(500)
    void assassinCriticalAttackShouldReturnProperDamageInflicted() {
        // Given
        Hero hero = spy(assassin);

        // When
        when(hero.getRandomChance()).thenReturn(0.3);
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
    void assassinNormalAttackHeroShouldReturnProperDamageInflicted() {
        // Given
        Hero hero = spy(assassin);
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
    void assassinNormalDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();

        // When
        assassin.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = getHealthAtBorder(LOWER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int expectedMaxHealthRemaining = getHealthAtBorder(UPPER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int actualHealthRemaining = getHealthAtBorder(assassin.getRandomPercentageBetween80And120(),
                damageInflictedBeforeDefense);

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining);
        assertTrue(actualHealthRemaining <= expectedMaxHealthRemaining);
    }
    private int getHealthAtBorder(double limitPercentage, int damageInflicted){
        return (int) (startingHealthPoints - (damageInflicted - assassin.getArmorPoints() * limitPercentage));
    }
}
