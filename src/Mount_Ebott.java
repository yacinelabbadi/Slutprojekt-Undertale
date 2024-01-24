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
        Weapon stick = new Weapon(2, "stick", "Stick\nAttack: 2\nA stick you randomly found on the ground, could help in sticky situations.");
        Armor bandAid = new Armor(1, "bandage", "Band-aid\nDefense: 1\nA band-aid you had on you before falling down, it has cute puppies on it!");

        player.equipItem(stick);
        player.equipItem(bandAid);

        ruins(player);
    }

    public void ruins(Character player){
        System.out.println("You slowly open your eyes as you awake."+
        "\nIt seems you have fallen down into a cave while traveling on Mount Ebott, the mountain rumored to be home to the monsters."+
        "\nYou look around but don't see much except golden flowers and a large purple door."+
        "\nWhat do you want to do?");
        String[] choices = new String[] {"View inventory (equipped items won't show up here)", "Check stats and equipment", "Go out the large purple door"};
        boolean loop = true;
        while (loop) {
            menu(choices);
            String choice = read.nextLine();

            switch (choice) {
                case "1":
                    checkInventory(player);
                    break;
                case "2":
                    checkStatsEquipment(player);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("You need to write a number corresponding to the choices!");
                    break;
            }
        }
    }

    public void snowdin(){

    }

    public void waterfall(){

    }

    public void hotland(){

    }

    public void newHome(){

    }

    public void end(){

    }

    public void menu(String[] menu) {
        for (int i = -1; i < menu.length; i++) {
            if (i==-1) {
                System.out.println("Input the number corresponding to what you want to do: ");
            }
            else{
                System.out.println((i+1) + ". " + menu[i]);
            }
        }
    }

    //Add using items and equipping gear
    public void checkInventory(Character player) {
        boolean loop = true;
        boolean itemFound = false;
        int answer;

        String choice;
        while(loop) {
            System.out.println();
            System.out.println("Gold: " + player.getGold());
            for (int i = 0; i < player.getInventory().size(); i++) {
                System.out.print(player.getInventory().get(i).getName());
                if (i == 3) {
                    System.out.println();
                }
            }
            System.out.println("Do you want check one of the items? y/n");
            answer = yesOrNo();
            if (answer == 1) {
                System.out.println("Input the name of the item you want to check: ");
                choice = read.nextLine();
                for (Item item : player.getInventory()) {
                    if (choice.equalsIgnoreCase(item.getName())) {
                        itemFound = true;
                        System.out.println(item.getDescription());
                        if (item instanceof Weapon || item instanceof Armor) {
                            System.out.println("Do you want to equip it? y/n");
                        } else {
                            System.out.println("Do you want to use it? y/n");
                        }
                        answer = yesOrNo();
                        if (answer == 1 && item instanceof Weapon || answer ==- 1 && item instanceof Armor) {
                            player.equipItem(item);
                        } else if (answer == 1) {
                            Healing heal = (Healing) item;
                            heal.healCharacter(player);
                        }
                        else {
                            System.out.println("Do you want to throw it away? y/n");
                            answer = yesOrNo();
                            if (answer == 1) {
                                player.throwAway(item);
                            }
                        }
                        break;
                    }
                }
                if (!itemFound) {
                    System.out.println("Item was not found in inventory.\nExit inventory? y/n");
                    answer = yesOrNo();
                    if (answer == 1) {
                        loop = false;
                    }
                }
            } else if (answer == 2) {
                loop = false;
            }
        }
    }

    public void checkStatsEquipment(Character player){
        System.out.println();
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp()+
                "\nDefense: " + player.getDefense()+
                "\nAttack: " + player.getAttack()+
                "\nExp: " + player.getExp()+
                "\nLv: " + player.getLv()+
                "\nArmor: " + player.getMyArmor().getName()+
                "\nWeapon: " + player.getMyWeapon().getName());
        System.out.println("Do you want to check equipment? y/n");
        String choice = read.nextLine();
        if (choice.equalsIgnoreCase("y")){
            System.out.println("Armor: " + player.getMyArmor().getDescription()+
                    "\nWeapon: " + player.getMyWeapon().getDescription());
        }
    }

    public int yesOrNo() {
        String choice = read.nextLine();
        int answer = 0;

        if (choice.equalsIgnoreCase("y")){
            answer = 1;
        }
        else if (choice.equalsIgnoreCase("n")) {
            answer = 2;
        }
        else {
            System.out.println("You need to answer with 'y' for yes or 'n' for no");
        }
        return answer;
    }

    public void battle() {

    }

    public void shop() {

    }
}
