// Custom abstract class, super class for Enemy and Player subclasses
// Has attributes/stats and methods that Enemy and Player have in common
public abstract class Character {
    // Attributes
    protected int hp = 20;
    protected int maxHp = 20;
    protected int defense = 5;
    protected int attack = 5;
    protected String name;


    // Constructor
    public Character(){}

    //Methods
    // Method for calculating the damage a character takes when hit by the enemies in battle(), might also be possible outside battle
    // in certain scenarios
    // Different between Enemy and Player
    public abstract void gettingHit(int damage);

    // Checks if the users character or opponent is dead which returns a boolean which calls the gameOver or wonBattle method in Battle() if true
    public boolean isCharacterDead(){
        return this.hp <= 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
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
}
