public class Weapon extends Item{
    // Attributes
    private int damage;
    private String damageType;

    //Constructor
    public Weapon(int damage, String damageType, String name){
        this.damage = damage;
        this.damageType = damageType;
        this.name = name;
    }
}
