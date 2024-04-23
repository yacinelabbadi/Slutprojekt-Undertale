import java.util.ArrayList;

// A custom class giving "stats" to the user which gets shown in an interface and is used in a lot of calculations, essentially the user itself
// A lot of the code is based around what the Player can and can't do
public class Player extends Character{
    // Attributes
    private int exp = 0;
    private int lv = 1;
    private int gold = 0;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Armor myArmor;
    private Weapon myWeapon;

    // Constructor
    public Player(String name, Armor myArmor, Weapon myWeapon) {
        this.name = name;
        this.myArmor = myArmor;
        this.myWeapon = myWeapon;
    }

    // Another constructor using another Character, essentially making a copy to save the players state upon entering a battle
    // In case they want to restart upon dying
    public Player(Player player){
        this.hp = player.getHp();
        this.maxHp = player.getMaxHp();
        this.defense = player.getDefense();
        this.attack = player.getAttack();
        this.exp = player.getExp();
        this.lv = player.getLv();
        this.gold = player.getGold();
        this.name = player.getName();
        this.inventory = copyInventory(player.getInventory());
        this.myArmor = player.getMyArmor();
        this.myWeapon = player.getMyWeapon();
    }

    // Methods

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

    // Method takes in an item as a parameter and then checks whether it is an instance of the weapon subclass or armor subclass
    // and then changes appropriate attributes according to each scenario like attack/defense and myWeapon/myArmor and then puts the
    // old equipped item back in inventory and removes the equipped item from the inventory
    // I never tested it in the beginning, I just assumed that it would return null if the attribute was empty, so I prepared an if statement
    public void equipItem(Item equipment){
        if (equipment instanceof Weapon newWeapon){
            changeWeapon(newWeapon);
        }
        else if (equipment instanceof Armor newArmor){
            changeArmor(newArmor);
        }
        this.throwAway(equipment);
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

    @Override
    public void gettingHit(int damage){
        this.hp -= damage - this.defense/5;
    }

    // removes old armors protection from defense, so it goes back to default before adding the new armors defense
    public void changeArmor(Armor newArmor) {
        setInventory(this.myArmor);
        this.defense -= this.myArmor.getProtection();
        this.myArmor = newArmor;
        this.defense += myArmor.getProtection();
    }

    // removes old weapons damage from attack, so it goes back to default before adding the new weapons damage
    public void changeWeapon(Weapon newWeapon) {
        setInventory(this.myWeapon);
        this.attack -= this.myWeapon.getDamage();
        this.myWeapon = newWeapon;
        this.attack += myWeapon.getDamage();

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
