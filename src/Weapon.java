public class Weapon extends Item{
    // Attributes
    private int damage;

    //Constructor
    public Weapon(int damage, String name, String description){
        super(name, description);
        this.damage = damage;
    }

    // Getters and setters

    public int getDamage() {
        return damage;
    }
}
