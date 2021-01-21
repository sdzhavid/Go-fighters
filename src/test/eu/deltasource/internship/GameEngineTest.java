package eu.deltasource.internship;

import eu.deltasource.internship.heroes.Assassin;
import eu.deltasource.internship.heroes.Hero;
import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEngineTest {

    private GameEngine gameEngine = new GameEngine();

    @Test
    void testWhenHeroDiesReturnTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Given
        Warrior warrior = new Warrior(3000,1000,200);
        Assassin assassin = new Assassin(100,100,50);
        assassin.defend(warrior.attack());

        Method isHeroKilled = gameEngine.getClass().getDeclaredMethod("isHeroKilled", Hero.class);
        isHeroKilled.setAccessible(true);

        // When
        boolean actualHeroIsDead = (boolean) isHeroKilled.invoke(gameEngine, assassin);

        // Then
        boolean expectedHeroIsDead = true;
        assertEquals(expectedHeroIsDead, actualHeroIsDead);
    }
}
