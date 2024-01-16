import java.util.ArrayList;

public class Character {
    // Attributes
    protected int hp = 20;
    protected int defense = 5;
    protected int attack = 5;
    private int exp = 0;
    private int lv = 1;
    private int gold = 0;
    protected String name;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private Armor myArmor;
    private Weapon myWeapon;


    // Constructor
    // Ask Liv if super() in subclass constructor needs to include all attributes that Super has in constructor
    // Ask Liv if I have inheritance "i två led" and if a potential "spawns" is acceptable subclass of enemy
    public Character(){}
    public Character(String name) {
        this.name = name;
    }

    // Getters and setters
    // Ask how the system remembers values, do I need getter and setter for health or does character.hp-1 save
}
