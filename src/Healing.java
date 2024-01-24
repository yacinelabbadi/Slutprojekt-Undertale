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
    public void healCharacter(Character player) {
        player.setHp(this.heal);
    }
}
