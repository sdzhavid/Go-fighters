package eu.deltasource.internship.heroes;

/**
 * A warrior which is a {@link Hero}
 * <p>
 * Nothing special about him.
 * </p>
 *
 * @author Samir Dzhavid
 * @version January 2021
 * @see Hero
 */
public class Warrior extends Hero {

    private String heroType;

    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param attackPoints the Attack Points of the Hero.
     * @param healthPoints the Health Points of the Hero.
     * @param armorPoints  the Armor Points of the Hero.
     */
    public Warrior(int attackPoints, int healthPoints, int armorPoints) {
        super(attackPoints, healthPoints, armorPoints);
        this.heroType = "Warrior";
    }

    @Override
    public String getHeroType() {
        return heroType;
    }
}
