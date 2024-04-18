import java.util.ArrayList;

public class Player extends Character{
    private int exp = 0;
    private int lv = 1;
    private int gold = 0;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Armor myArmor;
    private Weapon myWeapon;

    public Player(String name) {
        this.name = name;
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
}
