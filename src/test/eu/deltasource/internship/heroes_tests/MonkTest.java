package eu.deltasource.internship.heroes_tests;

import eu.deltasource.internship.heroes.Hero;
import eu.deltasource.internship.heroes.Monk;
import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class MonkTest {
    Monk monk;

    @BeforeEach
    void initMonk() {
        monk = new Monk(100, 1000, 30);
    }

    @Test
    void testNoDamageTakenWhenMonkDodgedAttack() {
        // Given
        Hero hero = spy(monk);
        when(hero.getRandomChance()).thenReturn(0.3);

        Warrior warriorThatHasAttacked = new Warrior(2000, 300, 200);
        int damageInflictedBeforeDefense = warriorThatHasAttacked.attack();
        int startingHealthPoints = monk.getHealthPoints();

        // When
        hero.defend(damageInflictedBeforeDefense);

        // Then
        int expectedHealthRemaining = startingHealthPoints;
        int actualRemainingHealth = hero.getHealthPoints();
        assertEquals(expectedHealthRemaining, actualRemainingHealth);
    }
}