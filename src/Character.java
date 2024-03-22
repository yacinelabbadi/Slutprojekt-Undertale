import java.util.ArrayList;

// A custom class giving "stats" to the user which gets shown in an interface and is used in a lot of calculations, essentially the user itself
// A lot of the code is based around what the Character can and can't do
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
    private ArrayList<Item> inventory = new ArrayList<>();
    private Armor myArmor;
    private Weapon myWeapon;


    // Constructor
    // potential "spawns" subclass of enemy
    public Character(){}
    public Character(String name) {
        this.name = name;
    }
    // Another constructor using another Character, essentially making a copy to save the players state upon entering a battle
    // In case they want to restart upon dying, the reason why a lot of the values is moved to a separate method is because
    // this gives a possibility for an Enemy to copy the players stats for battle, idea for a mimic battle
    public Character(Character player){
        this.hp = player.getHp();
        this.maxHp = player.getMaxHp();
        this.defense = player.getDefense();
        this.attack = player.getAttack();
        copyPlayer(player);
    }
    
    //Methods

    // The method saves the rest of the values if it is saving a character or giving it an ominous name if it's a mimic
    public void copyPlayer(Character player) {
        if (!(this instanceof Enemy)) {
            this.exp = player.getExp();
            this.lv = player.getLv();
            this.gold = player.getGold();
            this.name = player.getName();
            this.inventory = copyInventory(player.getInventory());
            this.myArmor = player.getMyArmor();
            this.myWeapon = player.getMyWeapon();
        } else {
            this.name = player.getName() + "???";
        }
    }

    public ArrayList<Item> copyInventory(ArrayList<Item> originalInventory) { //Todo Can this be improved?
        ArrayList<Item> inventoryCopy = new ArrayList<>();
        for (Item item: originalInventory) {
            if (item instanceof Healing) {
                inventoryCopy.add(new Healing((Healing) item));
            } else if (item instanceof Weapon) {
                inventoryCopy.add(new Weapon((Weapon) item));
            } else if (item instanceof Armor) {
                inventoryCopy.add(new Armor((Armor) item));
            } else {
                inventoryCopy.add(new Item(item));
            }
        }
        return inventoryCopy;
    }

    public void throwAway(Item item){
        this.inventory.remove(item);
        this.inventory.remove(null);
    }

    // Method for using healing items to increase current hp and then lets the Healing item check if it needs to be thrown away
    public void useHealing(Healing heal) {
        this.hp += heal.getHeal();
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        heal.gotUsed(this);
    }

    // Method for calculating the damage the player takes when hit by the enemies in battle(), might also be possible outside battle
    // in certain scenarios
    public void gettingHit(int damage){
        this.hp -= damage - this.defense/5;
    }

    // Checks if the users character is dead which returns a boolean which calls the gameOver method in Battle() if true
    public boolean isCharacterDead(){
        return this.hp <= 0;
    }

    // Method takes in an item as a parameter and then checks whether it is an instance of the weapon subclass or armor subclass
    // and then changes appropriate attributes according to each scenario like attack/defense and myWeapon/myArmor and then puts the
    // old equipped item back in inventory and removes the equipped item from the inventory
    // I never tested it in the beginning, I just assumed that it would return null if the attribute was empty, so I prepared an if statement
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

    // removes old armors protection from defense, so it goes back to default before adding the new armors defense
    public void unequipArmor() {
        this.defense -= this.myArmor.getProtection();
    }

    // removes old weapons damage from attack, so it goes back to default before adding the new weapons damage
    public void unequipWeapon() {
        this.attack -= this.myWeapon.getDamage();
    }

    // Calculates if the user levels up after increasing the exp attribute from battle() after killing the enemy
    public void expGain(int exp) {
        this.exp += exp;
        if (exp >= this.lv*20+4) {
            levelUp();
        }
    }

    // Increases multiple "stats" (attributes) whenever the level of violence increases and decreases exp by the required amount to level up
    public void levelUp(){
        this.maxHp += 4;
        this.defense += 1;
        this.attack += 1;
        this.lv += 1;
        this.exp -= this.lv*20+4;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
