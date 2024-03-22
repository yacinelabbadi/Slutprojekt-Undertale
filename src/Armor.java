// A subclass of item, increases the defense of a Character instance that saves it in its attributes, might make specific
// methods for certain armors that might have special effects
public class Armor extends Item{
    // Attributes
    private int protection;

    // Constructor
    public Armor(int protection, String name, String description){
        super(name, description);
        this.protection = protection;
    }

    public Armor(Armor armor) {
        super(armor.getName(), armor.getDescription());
        this.protection = armor.getProtection();
    }

    // getter
    public int getProtection() {
        return protection;
    }
}
