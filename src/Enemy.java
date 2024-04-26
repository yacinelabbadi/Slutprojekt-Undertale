import java.util.Random;
import java.util.Scanner;

// A subclass of Character, using both protected attributes from character and a multitude of its own attributes
// It is also abstract and would have many subclasses where you only need to specify about 2-3 methods if this project were to continue
public abstract class Enemy extends Character {
    protected String description;
    protected boolean spareable = false;
    protected int willingness;
    protected int expGain;
    protected int goldGain;
    protected String visibleName = "the monster";
    protected String[] choices;
    protected int[] correctChoices;

    //Constructors
    // Default constructor
    public Enemy() {

    }

    public Enemy(String name, int attack, int defense, int maxHp, int expGain, int goldGain, int willingness, String description) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.expGain = expGain;
        this.goldGain = goldGain;
        this.willingness = willingness;
        this.description = description;
    }

    // Another constructor using another Enemy, essentially making a copy
    public Enemy(Enemy enemy) {
        this.name = enemy.getName();
        this.attack = enemy.getAttack();
        this.defense = enemy.getDefense();
        this.maxHp = enemy.getMaxHp();
        this.hp = enemy.getHp();
        this.expGain = enemy.getExpGain();
        this.goldGain = enemy.getGoldGain();
        this.willingness = enemy.getWillingness();
        this.description = enemy.getDescription();
    }

    // Methods
    // This method is on the enemies turn during battle() in Mount_Ebott, randomizes an attack unique between different monsters
    // but only including six different ways for player/user to counter/negate the incoming damage and then looking at the result with attackResult()
    public void attacks(Character player){
        System.out.println(this.getVisibleName() + " tries to attack " + player.getName());
        System.out.println();
        Random generator = new Random();
        Scanner read = new Scanner(System.in);
        int attackVariation = generator.nextInt(1,4);
        int choice;

        //
        int correctChoice = determineCorrectChoice(attackVariation);;

        while (true) {
            System.out.println("How do you want to avoid damage?");
            System.out.println("""
                    1: Block the attack
                    2: Duck
                    3: Jump
                    4: Parry
                    5: Step back
                    6: Sidestep""");
            try {
                choice = Integer.parseInt(read.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("You must write a number between 1 and 6 corresponding to what you want to do!");
            }
        }
        attackResult(choice, correctChoice, player);
    }

    public int determineCorrectChoice(int attackVariation) {
        int correctChoice = 0;
        switch (attackVariation) {
            case 1 -> {
                System.out.println(this.getVisibleName() + this.getChoices()[0]);
                correctChoice = this.getCorrectChoices()[0];
            }
            case 2 -> {
                System.out.println(this.getVisibleName() + this.getChoices()[1]);
                correctChoice = this.getCorrectChoices()[1];
            }
            case 3 -> {
                System.out.println(this.getVisibleName() + this.getChoices()[2]);
                correctChoice = this.getCorrectChoices()[2];
            }
        }
        return correctChoice;
    }

    // A method that sends back an int correctChoice in the attack method
    // The method is implemented in subclasses and doesn't need to use @Override as the method is empty in this class

    //public abstract int determineCorrectChoice(int attackVariation);

    public abstract void actOptions();


    // Checks the users input and what the correct choice is from the randomized attack and prints different lines depending on
    // what the user picked and if that choice is wrong or correct, either doing nothing or making the player take damage
    public void attackResult(int choice, int correctChoice, Character player){
        if (choice == correctChoice){
            switch (choice) {
                case 1 -> System.out.println("You blocked the attack with your weapon!");
                case 2 -> System.out.println("You ducked under the attack!");
                case 3 -> System.out.println("You jumped over the attack!");
                case 4 -> System.out.println("You parry the attack with your weapon, the enemy looks shocked!");
                case 5 -> System.out.println("You take a step back, the attack grazes your nose!");
                case 6 -> System.out.println("You swerve past the attack!");
            }
        } else {
            switch (choice) {
                case 1 -> System.out.println("How would you block that?");
                case 2 -> System.out.println("You are quack at ducking.");
                case 3 -> System.out.println("Your tiny legs didn't jump high enough!");
                case 4 -> System.out.println("Your timing was completely off.");
                case 5 -> System.out.println("You take a step back, but it seems you misjudged the distance!");
                case 6 -> System.out.println("You try to swerve past the attack, but trip instead!");
            }
            System.out.println("You took damage!");
            player.gettingHit(this.getAttack());
        }
    }

    // Whenever the act method for each enemy is used if the correct option is picked the enemies willingness goes down until it
    // doesn't want to battle anymore and you can spare it
    public void willingnessChange(int difference) {
        this.willingness -= difference;
        if (this.willingness <= 0) {
            this.spareable = true;
            System.out.println("It looks like " + this.visibleName + " doesn't want to fight anymore!");
            this.willingness = 0;
        }
    }

    // The method for the enemy taking damage from player during battle, which can also make the enemy not want
    // to fight anymore because it is too low and could die
    public void gettingHit(int damage){
        this.hp -= damage;
        if (this.hp <= (this.maxHp*0.3) && this.hp>0) {
            this.spareable = true;
            System.out.println("It looks like " + this.visibleName + " doesn't want to fight anymore!");
        }
    }

    // getters and setters
    public String getDescription() {
        this.visibleName = this.name;
        return description;
    }

    public boolean getSparable() {
        return spareable;
    }

    public int getWillingness() {
        return willingness;
    }

    public int getExpGain() {
        return expGain;
    }

    public int getGoldGain(){
        return goldGain;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public String[] getChoices() {
        return this.choices;
    }

    public int[] getCorrectChoices() {
        return this.correctChoices;
    }

    public void setSpareable(boolean spareable) {
        this.spareable = spareable;
    }

    public void setVisibleName(String visibleName) {
        this.visibleName = visibleName;
    }
}
