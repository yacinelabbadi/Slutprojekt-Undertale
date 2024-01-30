import java.util.Random;
import java.util.Scanner;

public class Enemy extends Character {
    private String description;
    private boolean spareable = false;
    private int willingness;
    private boolean checked;
    private int expGain;
    private int goldGain;
    private String visibleName = "the monster";

    //Constructor
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

    // Methods
    public void attacks(Character player){
        // System.out.println(this.name + " tries to attack " + player.getName());
        System.out.println();
        Random generator = new Random();
        Scanner read = new Scanner(System.in);
        int correctChoice = 0;
        int choice = 0;
        int attackVariation = generator.nextInt(1,4);

        if (this.name.equals("Froggit")){
            correctChoice = froggit(attackVariation);
        }

        while (true) {
            System.out.println("How do you want to avoid damage?");
            System.out.println("1: Block the attack" + "\n2: Duck" + "\n3: Jump" + "\n4: Parry" + "\n5: Step back" + "\n6: Sidestep");
            try {
                choice = Integer.parseInt(read.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("You must write a number between 1 and 6 corresponding to what you want to do!");
            }
        }
        attackResult(choice, correctChoice, player);
    }

    public int froggit(int attackVariation) {
        int correctChoice = 0;
        switch (attackVariation) {
            case 1 -> {
                System.out.println(this.visibleName + " sends out a bunch of big flies at you!");
                correctChoice = 6;
            }
            case 2 -> {
                System.out.println(this.visibleName + " jumps at you!");
                correctChoice = 2;
            }
            case 3 -> {
                System.out.println(this.visibleName + " shoots out his tongue!");
                correctChoice = 4;
            }
        }
        return correctChoice;
    }

    public void attackResult(int choice, int correctChoice, Character player){
        if (choice == correctChoice){
            switch (choice) {
                case 1 -> System.out.println("You blocked the attack with your weapon!");
                case 2 -> System.out.println("You ducked under the attack!");
                case 3 -> System.out.println("You jumped over the attack!");
                case 4 -> System.out.println("You parry the attack with your weapon, the enemy looks schocked!");
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
            player.gettingHit(this.attack);
        }
    }

    public void willingnessChange(int difference) {
        this.willingness -= difference;
        if (this.willingness == 0) {
            this.spareable = true;
            System.out.println("It looks like " + this.visibleName + " doesn't want to fight anymore!");
        }
    }

    // getters and setters
    public String getDescription() {
        checked = true;
        this.visibleName = this.name;
        return description;
    }

    public boolean getSparable() {
        return spareable;
    }

    public int getWillingness() {
        return willingness;
    }

    public boolean getChecked() {
        return checked;
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

    public void setSpareable(boolean spareable) {
        this.spareable = spareable;
    }
}
