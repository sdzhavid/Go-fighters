package eu.deltasource.internship.heroes_tests;

import eu.deltasource.internship.heroes.Assassin;
import eu.deltasource.internship.heroes.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssassinTest {
    Assassin assassin;

    @BeforeEach
    void initAssassin() {
        assassin = new Assassin(100, 1000, 30);
    }

    @RepeatedTest(500)
    void assassinCriticalAttackShouldReturnProperDamageInflicted() {
        // Given
        Hero hero = spy(assassin);
        when(hero.getRandomChance()).thenReturn(0.3);

        // When
        hero.attack();

        // Then
        int expectedMinimalDamageBeforeDefense = (int) (hero.getAttackPoints() * 0.8 * 3);
        int expectedMaximumDamageBeforeDefense = (int) (hero.getAttackPoints() * 1.2 * 3);
        int actualDamageBeforeDefense = hero.attack();
        assertTrue(actualDamageBeforeDefense >= expectedMinimalDamageBeforeDefense &&
                actualDamageBeforeDefense <= expectedMaximumDamageBeforeDefense);
    }
}