import java.util.ArrayList;

public class Character {
    // Attributes
    protected int hp = 20;
    protected int maxHp = 20;
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
    // potential "spawns" subclass of enemy
    public Character(){}
    public Character(String name) {
        this.name = name;
    }
    
    //Methods

    public void throwAway(Item item){
        this.inventory.remove(item);
    }

    public void equipItem(Item equipment){
        if (equipment instanceof Weapon){
            Weapon myWeapon = (Weapon) equipment;
            if (this.myWeapon != null) {
                unequipWeapon();
            }
            this.myWeapon = myWeapon;
            this.attack += myWeapon.getDamage();
        }
        else if (equipment instanceof Armor){
            Armor myArmor = (Armor) equipment;
            if (this.myArmor != null) {
                unequipArmor();
            }
            this.myArmor = myArmor;
            this.defense += myArmor.getProtection();
        }
    }
    
    public void unequipArmor() {
        this.defense -= this.myArmor.getProtection();
    }

    public void unequipWeapon() {
        this.attack -= this.myWeapon.getDamage();
    }

    public void levelUp(){
        this.maxHp += 4;
        this.defense += 1;
        this.attack += 1;
        this.lv += 1;
        this.exp = 0;
    }

    // Getters and setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hpDifference) {
        this.hp += hpDifference;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public Armor getMyArmor() {
        return myArmor;
    }

    public Weapon getMyWeapon() {
        return myWeapon;
    }
}
