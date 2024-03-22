// A subclass of item, increases the attack of a Character instance that saves it in its attributes, might make specific
// methods for certain weapons that might have special effects
public class Weapon extends Item{
    // Attributes
    private int damage;

    //Constructor
    public Weapon(int damage, String name, String description){
        super(name, description);
        this.damage = damage;
    }

    public Weapon(Weapon weapon) {
        super(weapon.getName(), weapon.getDescription());
        this.damage = weapon.getDamage();
    }

    // Getters and setters

    public int getDamage() {
        return damage;
    }
}
