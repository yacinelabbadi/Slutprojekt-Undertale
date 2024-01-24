public class Armor extends Item{
    // Attributes
    private int protection;

    // Constructor
    public Armor(int protection, String name, String description){
        super(name, description);
        this.protection = protection;
    }

    // getters and setters
    public int getProtection() {
        return protection;
    }
}
