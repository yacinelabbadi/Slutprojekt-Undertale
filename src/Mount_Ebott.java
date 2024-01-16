import java.util.ArrayList;
import java.util.Scanner;

public class Mount_Ebott {
    Scanner read = new Scanner(System.in);


    public Mount_Ebott(){
        start();
    }
    public void start(){
        System.out.println("Undertale fangame");

        System.out.println("\nUndertale is a turn based fighting rpg where you can choose to fight enemies or spare them."
        +"\nBecause 2D attacks where you need to dodge is hard to code, you will instead get a prompt on how you want to dodge the attack."
        +"\nFor example, if the prompt states that the enemy does a sweeping attack,"+"\nyou will get choices on how to dodge the incoming attack, like jumping over the attack, or some other way to stop the damage."
        +"\nKnowing how to dodge the attacks and winning the battle without fighting can either be deduced or found through trial and error."
        +"\nYou will also have an inventory where you can use and equip new items and equipment."
        +"\nLast of all, you will have a mercy option where you spare enemies who no longer want to fight or you can flee if the situation looks bad."
        +"\nKeep in mind that fleeing won't always be successful, and powerful enemies might keep you from running."
        +"\nGood luck and have fun!");

        System.out.println("\nWhat will name your character: ");
        String name = read.nextLine();
        Character player = new Character(name);
        Weapon stick = new Weapon(3, "blunt", "stick");
        Armor bandage = new Armor();
    }


    // Ask Liv if methods should have lower case first letter
    public void Menu(String[] menu) {
        for (int i = 0; i < menu.length; i++) {
            if (i==0) {
                System.out.println(menu[i]);
            }
            else{
                System.out.println(i + ". " + menu[i]);
            }
        }
    }

    public void battle() {

    }

    public void shop() {

    }
}
