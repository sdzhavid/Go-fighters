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
        this.heroType = this.getClass().getSimpleName();
    }

    public Warrior() {
        this(200, 2000, 150);
    }

    @Override
    public String getHeroType() {
        return heroType;
    }

    /**
     * Method is used when there are 2 heroes of the same class in the arena.
     */
    @Override
    public void setHeroTypeToSecond() {
        this.heroType = "Second " + getHeroType();
    }

    public static void main(String[] args) {
        Warrior warrior = new Warrior(100,2,3);
        System.out.println(warrior.attack());
    }
}
