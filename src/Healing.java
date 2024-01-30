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

    // Methods
    public void gotUsed(Character player) {
        this.uses--;
        if (this.uses == 0){
            player.throwAway(this);
        }
    }

    // Getters and Setters
    public int getHeal() {
        return heal;
    }
}
