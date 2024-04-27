// A subclass of item, used to let the player heal themselves and consume the item during the process unless it has multiple uses
public class Healing extends Item{
    // Attributes
    private int heal;
    private int uses;

    // Constructor
    public Healing(int heal, int uses, String name, String description) {
        super(name, description);
        this.heal = heal;
        this.uses = uses;
    }

    // Another constructor using another Healing item, essentially making a copy
    public Healing(Healing healingItem) {
        super(healingItem.getName(), healingItem.getDescription());
        this.heal = healingItem.getHeal();
        this.uses = healingItem.getUses();
    }

    // Methods
    // called from the player class whenever they use a healing item from checkInventory, checks uses and
    // tells the player to remove the item from its arraylist if the uses reaches 0
    public void gotUsed(Player playerCharacter) {
        this.uses--;
        if (this.uses == 0){
            playerCharacter.throwAway(this);
        }
    }

    // Getters
    public int getHeal() {
        return heal;
    }

    public int getUses(){
        return uses;
    }
}
