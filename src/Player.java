import java.util.ArrayList;

// A subclass of abstract class Character, giving "stats" to the user which gets shown in an interface
// and is used in a lot of calculations, essentially the user itself
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

    // Another constructor using another Player, essentially making a copy to save the
    // players state upon entering a battle in case they want to restart upon dying
    public Player(Player originalPlayer){
        this.hp = originalPlayer.getHp();
        this.maxHp = originalPlayer.getMaxHp();
        this.defense = originalPlayer.getDefense();
        this.attack = originalPlayer.getAttack();
        this.exp = originalPlayer.getExp();
        this.lv = originalPlayer.getLv();
        this.gold = originalPlayer.getGold();
        this.name = originalPlayer.getName();
        this.inventory = copyInventory(originalPlayer.getInventory());
        this.myArmor = originalPlayer.getMyArmor();
        this.myWeapon = originalPlayer.getMyWeapon();
    }

    // Methods

    // Method called in the copy constructor to actually copy the inventory and the objects within it
    // and not just copying the references, which would be pointless
    public ArrayList<Item> copyInventory(ArrayList<Item> originalInventory) {
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
    // And runs the appropriate change method, which puts back the old equipped item back in inventory and throws away
    // the item that is getting equipped so the user can't duplicate items
    public void equipItem(Item equipment){
        if (equipment instanceof Weapon newWeapon){
            changeWeapon(newWeapon);
        }
        else if (equipment instanceof Armor newArmor){
            changeArmor(newArmor);
        }
        this.throwAway(equipment);
    }

    // Method for removing an item from the players inventory arraylist
    public void throwAway(Item trash){
        this.inventory.remove(trash);
        this.inventory.remove(null);
    }

    // Method for using healing items to increase current hp and then lets the
    // Healing item check if it is out of uses and needs to be thrown away
    public void useHealing(Healing healingItem) {
        this.hp += healingItem.getHeal();
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        healingItem.gotUsed(this);
    }

    // Method for when the enemy attacks playerCharacter in battle() in Mount_Ebott
    public void gettingHit(int damage){
        this.hp -= damage - this.defense/5;
    }

    // Removes old armors protection from defense, so it goes back to default before adding the new armors defense
    // and puts back the old armor in the playerCharacters inventory while equipping the new armor
    public void changeArmor(Armor newArmor) {
        setInventory(this.myArmor);
        this.defense -= this.myArmor.getProtection();
        this.myArmor = newArmor;
        this.defense += myArmor.getProtection();
    }

    // Removes old weapons damage from attack, so it goes back to default before adding the new weapons damage
    // and puts back the old weapon in the playerCharacters inventory while equipping the new armor
    public void changeWeapon(Weapon newWeapon) {
        setInventory(this.myWeapon);
        this.attack -= this.myWeapon.getDamage();
        this.myWeapon = newWeapon;
        this.attack += myWeapon.getDamage();
    }

    // Calculates if the user levels up after increasing the exp attribute from wonBattle() after killing the enemy
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
