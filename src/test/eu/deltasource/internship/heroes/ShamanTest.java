package eu.deltasource.internship.heroes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ShamanTest {
    private Shaman shaman;
    private static final double DAMAGE_TAKEN_MULTIPLIER = 1.2;
    private double LOWER_LIMIT_PERCENTAGE = 0.8;
    private double UPPER_LIMIT_PERCENTAGE = 1.2;
    private int startingHealthPoints;

    @BeforeEach
    void initializeShaman(){
        shaman = new Shaman(100,2000,100);
        startingHealthPoints = shaman.getHealthPoints();
    }

    @RepeatedTest(10)
    void testShamanNormalDefenseFromHeroShouldReturnCorrectHealthPointsIncreasedByTwentyPercent(){
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();

        // When
        shaman.defend(damageInflictedBeforeDefense);

        // Then
        int expectedMinHealthRemaining = getHealthAtBorder(LOWER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int expectedMaxHealthRemaining = getHealthAtBorder(UPPER_LIMIT_PERCENTAGE, damageInflictedBeforeDefense);

        int actualHealthRemaining = getHealthAtBorder(shaman.getRandomPercentageBetween80And120(),
                damageInflictedBeforeDefense);

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining);
        assertTrue(actualHealthRemaining <= expectedMaxHealthRemaining);
    }
    private int getHealthAtBorder(double limitPercentage, int damageInflicted){
        return (int) (startingHealthPoints - (damageInflicted - shaman.getArmorPoints() * limitPercentage)
        * DAMAGE_TAKEN_MULTIPLIER);
    }

    @Test
    void testShamanNormalAttackShouldReturnProperDamageInflicted(){
        // Given
        Hero hero = spy(shaman);
        double chanceAboveCritical = 0.8;

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

    @Test
    void shamanDoubleAttackShouldReturnProperDamageInflicted(){
        // Given
        Hero hero = spy(shaman);

        // When
        when(hero.getRandomChance()).thenReturn(0.2);
        hero.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (hero.getAttackPoints() * LOWER_LIMIT_PERCENTAGE
                * 2);
        int expectedMaximumDamageBeforeDefense = (int) (hero.getAttackPoints() * UPPER_LIMIT_PERCENTAGE
                * 2);
        int actualDamageBeforeDefense = hero.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense);
        assertTrue(actualDamageBeforeDefense<= expectedMaximumDamageBeforeDefense);
    }
}