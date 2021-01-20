package eu.deltasource.internship.heroes_tests;

import eu.deltasource.internship.heroes.Assassin;
import eu.deltasource.internship.heroes.Hero;
import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssassinTest {
    private Assassin assassin;
    private int DAMAGE_INCREASE_MULTIPLIER = 3;
    private double LOWER_LIMIT_PERCENTAGE = 0.8;
    private double UPPER_LIMIT_PERCENTAGE = 1.2;

    @BeforeEach
    void initAssassin() {
        assassin = new Assassin(100, 1000, 30);
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

        // When
        assassin.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (assassin.getAttackPoints() * LOWER_LIMIT_PERCENTAGE);
        int expectedMaximumDamageBeforeDefense = (int) (assassin.getAttackPoints() * UPPER_LIMIT_PERCENTAGE);
        int actualDamageBeforeDefense = assassin.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }

    @RepeatedTest(500)
    void assassinNormalDefendFromHeroShouldReduceHealthPointsCorrectly() {
        // Given
        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = assassin.getHealthPoints();

        // When
        assassin.defend(damageInflictedBeforeDefense);

        // Then

        int expectedMinHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                assassin.getArmorPoints() * LOWER_LIMIT_PERCENTAGE));

        int expectedMaxHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                assassin.getArmorPoints() * UPPER_LIMIT_PERCENTAGE));

        int actualHealthRemaining = (int) (startingHealthPoints - (damageInflictedBeforeDefense -
                assassin.getArmorPoints() * assassin.getRandomPercentageBetween80And120()));

        assertTrue(actualHealthRemaining >= expectedMinHealthRemaining &&
                actualHealthRemaining <= expectedMaxHealthRemaining);
    }
}