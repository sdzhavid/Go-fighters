package eu.deltasource.internship.arenas;
// Useless now The point is to create a timer arena and no timer arena
import eu.deltasource.internship.heroes.Hero;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    List<Hero> heroesInArena;

    public Arena() {
        heroesInArena = new ArrayList<>();
    }

    /**
     * Returns a list of the heroes that have entered the arena.
     *
     * @return the heroes that have entered the Arena
     */
    public List<Hero> getHeroesInArena() {
        return null;
    }

    public void enterArena(Hero heroToEnter){
        if (heroToEnter == null){
            return;// throw an exception
        }
        if(heroesInArena.contains(heroToEnter)){
            // error a hero can't join the arena 2 times
            return;
        }
        heroesInArena.add(heroToEnter);
    }
}
