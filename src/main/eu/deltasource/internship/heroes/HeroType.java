package eu.deltasource.internship.heroes;

/**
 * Enum for different heroTypes
 *
 * @author Samir Dzhavid
 * @version January 2021
 */
public enum HeroType {

    ASSASSIN("Assassin"),
    WARRIOR("Warrior"),
    KNIGHT("Knight"),
    MONK("Monk"),
    SHAMAN("Shaman");

    public final String heroType;

    HeroType(String heroType) {
        this.heroType = heroType;
    }
}
