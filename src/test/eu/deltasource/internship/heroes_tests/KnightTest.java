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
    Knight knight;

    @BeforeEach
    void initKnight() {
        knight = new Knight(100, 1000, 40);
    }

    @RepeatedTest(100)
    void testNoDamageTakenWhenKnightBlockedAttack() {
        // Given
        Hero hero = spy(knight);
        when(hero.getRandomChance()).thenReturn(0.2);

        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = knight.getHealthPoints();

        // When
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
        when(hero.getRandomChance()).thenReturn(0.1);

        // When
        hero.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (hero.getAttackPoints() * 0.8 * 2);
        int expectedMaximumDamageBeforeDefense = (int) (hero.getAttackPoints() * 1.2 * 2);
        int actualDamageBeforeDefense = hero.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }
}