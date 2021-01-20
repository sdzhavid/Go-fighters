package eu.deltasource.internship;

import eu.deltasource.internship.heroes.Assassin;
import eu.deltasource.internship.heroes.Hero;
import eu.deltasource.internship.heroes.Warrior;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameEngineTest {

    private GameEngine gameEngine;

    @BeforeEach
    void initGameEngine(){
        gameEngine = new GameEngine();
    }

    @Test
    void testStartGame(){
        // to be implemented
    }

    @Test
    void testIfPrintRoundPrintsCorrectDetails() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // Given
        Warrior warrior = new Warrior(200,3000,200);
        Assassin assassin = new Assassin(100,2000,300);
        int damageDealtByWarrior = warrior.attack();

        Method printRound = GameEngine.class.getDeclaredMethod("printRound", Hero.class, int.class, Hero.class);
        printRound.setAccessible(true);

        // When
        printRound.invoke(gameEngine, warrior, damageDealtByWarrior, assassin);

        // Then
        String expectedMessage = warrior.getHeroType() +
                " attacked for " + damageDealtByWarrior + ", " +
                assassin.getHeroType() + "'s remaining health is " +
                assassin.getHealthPoints();
        String actualMessage = (String) printRound.invoke(gameEngine, warrior, damageDealtByWarrior, assassin);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testWhenHeroDiesReturnTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Given
        Warrior warrior = new Warrior(3000,1000,200);
        Assassin assassin = new Assassin(100,100,50);
        assassin.defend(warrior.attack());

        Method isHeroKilled = gameEngine.getClass().getDeclaredMethod("isHeroKilled", Hero.class, Hero.class);
        isHeroKilled.setAccessible(true);

        // When
        boolean isHeroDead = (boolean) isHeroKilled.invoke(gameEngine, assassin, warrior);

        // Then
        boolean expectedHeroIsDead = true;
        boolean actualHeroIsDead = isHeroDead;
        Assertions.assertEquals(expectedHeroIsDead, actualHeroIsDead);
    }
}
