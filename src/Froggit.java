import java.util.Scanner;

// A subclass of the abstract class Enemy, so that the Enemy class and main code
// doesn't get cluttered with methods for each and every enemy type
// Makes it possible for certain enemy types to drop specific items if a few changes are added
public class Froggit extends Enemy{

    // Constructor
    public Froggit(String name, int attack, int defense, int maxHp, int expGain, int goldGain, int willingness, String description) {
        super(name, attack, defense, maxHp, expGain, goldGain, willingness, description);
        this.choices = new String[]{" sends out a bunch of big flies at you!", " jumps at you!", " shoots out his tongue!"};
        this.correctChoices = new int[]{6,2,4};
        System.out.println(this.choices[0] + this.choices[1] + this.choices[2]);
    }

    // Another constructor to make copy of Froggit
    public Froggit(Enemy enemy) {
        super(enemy);
        this.choices = new String[]{" sends out a bunch of big flies at you!", " jumps at you!", " shoots out his tongue!"};
        this.correctChoices = new int[]{6,2,4};
    }

    // Methods

    // This method lets you choose an action when choosing act against the enemy froggit in the Battle method,
    // which either lets you see froggits stats and description
    // or call a class method to change its willingness which affects its spareability
    public void actOptions() {
        String choice;
        Scanner read = new Scanner(System.in);
        System.out.println("\n1: Check " + this.getVisibleName() + "\n2: Compliment " +
                this.getVisibleName() + "\n3: Threaten " + this.getVisibleName());

        label:
        while(true) {
            choice = read.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(this.getDescription());
                    break label;
                case "2":
                    System.out.println(this.getVisibleName() + " didn't understand you, but was flattered anyway");
                    this.willingnessChange(1);
                    break label;
                case "3":
                    System.out.println(this.getVisibleName() + " didn't understand you, but was scared anyway");
                    this.willingnessChange(1);
                    break label;
                default:
                    System.out.println("You need to write a number corresponding to the choices!");
                    break;
            }
        }
    }
}
