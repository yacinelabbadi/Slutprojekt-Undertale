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
    public Character(Character player){
        this.hp = player.getHp();
        this.maxHp = player.getMaxHp();
        this.defense = player.getDefense();
        this.attack = player.getAttack();
        copyPlayer(player);
    }
    
    //Methods

    public void copyPlayer(Character player) {
        if (!(this instanceof Enemy)) {
            this.exp = player.getExp();
            this.lv = player.getLv();
            this.gold = player.getGold();
            this.name = player.getName();
            this.inventory = player.getInventory();
            this.myArmor = player.getMyArmor();
            this.myWeapon = player.getMyWeapon();
        } else {
            this.name = player.getName() + "???";
        }
    }

    public void checkEnemy(Enemy enemy) {
        System.out.println(enemy.getDescription());
    }

    public void throwAway(Item item){
        this.inventory.remove(item);
        this.inventory.remove(null);
    }

    public void useHealing(Healing heal) {
        this.hp += heal.getHeal();
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        heal.gotUsed(this);
    }

    public void gettingHit(int damage){
        this.hp -= damage - this.defense/5;
    }

    public boolean isCharacterDead(){
        boolean dead = false;
        if (this.hp <= 0) {
            dead = true;
        }
        return dead;
    }

    public void equipItem(Item equipment){
        if (equipment instanceof Weapon){
            setInventory(this.myWeapon);
            Weapon myWeapon = (Weapon) equipment;
            if (this.myWeapon != null) {
                unequipWeapon();
            }
            this.myWeapon = myWeapon;
            this.attack += myWeapon.getDamage();
        }
        else if (equipment instanceof Armor){
            setInventory(this.myArmor);
            Armor myArmor = (Armor) equipment;
            if (this.myArmor != null) {
                unequipArmor();
            }
            this.myArmor = myArmor;
            this.defense += myArmor.getProtection();
        }
        this.throwAway(equipment);
    }
    
    public void unequipArmor() {
        this.defense -= this.myArmor.getProtection();
    }

    public void unequipWeapon() {
        this.attack -= this.myWeapon.getDamage();
    }

    public void expGain(int exp) {
        this.exp += exp;
        if (exp >= this.lv*20+4) {
            levelUp();
        }
    }

    public void levelUp(){
        this.maxHp += 4;
        this.defense += 1;
        this.attack += 1;
        this.lv += 1;
        this.exp = 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
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

    public int getExp(){
        return exp;
    }

    public int getLv() {
        return lv;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(Item item) {
        this.inventory.add(item);
    }

    public Armor getMyArmor() {
        return myArmor;
    }

    public Weapon getMyWeapon() {
        return myWeapon;
    }
}
