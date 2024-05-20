import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mount_Ebott {
    // Attributes for Mount_Ebott, the main class handling the program itself, mostly just objects I want to use anywhere in the code like scanner
    // Arraylists for Item subclasses and enemies, so I can make instances of them anywhere without affecting the original
    Scanner read = new Scanner(System.in);
    Random generator = new Random();
    ArrayList<Healing> healItems = new ArrayList<>();
    ArrayList<Weapon> weapons = new ArrayList<>();
    ArrayList<Armor> armors = new ArrayList<>();
    //ArrayList<Item> itemsList = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    Player playerCharacter;
    Player savePlayer;

    // Constructor, called from main, starts the program
    public Mount_Ebott(){
        start();
    }

    // The beginning of the game, includes instructions and a summary of the game itself, asks user for input and creates an instance of Character
    // And creates instances of several items/subclasses of items and adds them
    // to the Characters inventory and attributes by calling Character method setInventory and then calls the manageRuins method, moving forward

    public void start(){
        System.out.println("Undertale fangame");

        System.out.println("""

                Undertale is a turn based fighting rpg where you can choose to fight enemies or spare them.
                Because 2D attacks where you need to dodge is hard to code, you will instead get a prompt on how you want to dodge the attack.
                For example, if the prompt states that the enemy does a sweeping attack,
                you will get choices on how to dodge the incoming attack, like jumping over the attack, or some other way to stop the damage.
                Knowing how to dodge the attacks and winning the battle without fighting can either be deduced or found through trial and error.
                You will also have an inventory where you can use and equip new items and equipment.
                Last of all, you will have a mercy option where you spare enemies who no longer want to fight or you can flee if the situation looks bad.
                Keep in mind that fleeing won't always be successful, and powerful enemies might keep you from running.
                Good luck and have fun!""");

        System.out.println("\nWhat will name your character: ");
        String name = read.nextLine();
        healItems.add(new Healing(2, 1, "Candy", "Candy\nHealing: 2\nA piece of cinnamon caramel candy, tasty!"));
        weapons.add(new Weapon(2, "stick", "Stick\nAttack: 2\nA stick you randomly found on the ground, could help in sticky situations."));
        armors.add(new Armor(1, "bandage", "Band-aid\nDefense: 1\nA band-aid you had on you before falling down, it has cute puppies on it!"));

        playerCharacter = new Player(name, new Armor(armors.get(0)), new Weapon(weapons.get(0)));

        playerCharacter.setInventory(new Healing(healItems.get(0)));
        playerCharacter.setInventory(new Healing(healItems.get(0)));

        manageRuins();
    }

    // Creates an instance of Froggit and writes another wall of text and calls the menu method and lets the user choose what to do with a
    // switch statement, either calling method checkInventory, checkStatsEquipment or continuing forward which calls method battle
    // The program is supposed to continue from here if more content gets added in the future
    public void manageRuins(){
        Enemy froggit = new Froggit("Froggit", 4, 4, 20, 10, 6,
                2, "Froggit - Atk 4 Def 4" + "\nA weird frog creature... it talks?!");
        enemies.add(froggit);
        String choice;

        System.out.println("""
        You slowly open your eyes as you awake.
        It seems you have fallen down into a cave while traveling on Mount Ebott, the mountain rumored to be home to the monsters.
        You look around but don't see much except golden flowers and a large purple door.
        \nWhat do you want to do?""");
        String[] choices = new String[] {"View inventory (equipped items won't show up here)", "Check stats and equipment", "Go out the large purple door"};
        boolean loop = true;
        while (loop) {
            choice = openMenu(choices);

            switch (choice) {
                case "1" -> checkInventory(false);
                case "2" -> checkStatsEquipment();
                case "3" -> loop = false;
                default -> System.out.println("You need to write a number corresponding to the choices!");
            }
        }

        System.out.println("You walk out the large purple door and see a dark room with a faint light in the distance."+
                "\nAs you try to walk your way to the light, something emerges from the darkness!");
        battle(enemies.get(0));
    }

    /*
    A bunch of methods for more areas in case I continue on this game later

    public void manageSnowdin(){

    }

    public void manageWaterfall(){

    }

    public void manageHotland(){

    }

    public void manageNewHome(){

    }

    public void end(){

    }
     */

    // Takes in an ArrayList of strings and write out a numbered list with a for loop to simulate a menu of choices
    // and then asks user for input which it then returns to a String choice which is used to compute users input
    public String openMenu(String[] menu) {
        for (int i = -1; i < menu.length; i++) {
            if (i==-1) {
                System.out.println("Input the number corresponding to what you want to do: ");
            }
            else{
                System.out.println((i+1) + ". " + menu[i]);
            }
        }

        return read.nextLine();
    }

    // This method uses loops and if-else statements to let the user see the arraylist of items
    // the playerCharacter object has stored and choose an item to either equip or use depending on
    // if the item is an instance of Weapon/Armor or Healing or lets the user throw away the item
    // Now divided up in a few methods
    public void checkInventory(boolean inBattle) {
        boolean loop = true;
        int answer;

        while(loop) {

            if (playerCharacter.getInventory().isEmpty()) {
                System.out.println("You have no items");
                break;
            } else {
                printInventory();
            }

            System.out.println("\nDo you want check one of the items? y/n");
            answer = yesOrNoQuestion();

            loop = chooseItem(answer, inBattle);
        }
    }

    // Prints out the players gold and inventory
    public void printInventory() {
        System.out.println();
        System.out.println("Gold: " + playerCharacter.getGold());

        for (int i = 0; i < playerCharacter.getInventory().size(); i++) {
            System.out.print(playerCharacter.getInventory().get(i).getName()+"  ");
            if (i == 3) {
                System.out.println();
            }
        }
    }

    public boolean chooseItem(int answer, boolean inBattle) {
        String choice;
        boolean itemFound = false;
        boolean loop = true;

        if (answer == 1) {
            System.out.println("Input the name of the item you want to check: ");
            choice = read.nextLine();
            for (Item item : playerCharacter.getInventory()) {
                if (choice.equalsIgnoreCase(item.getName())) {
                    itemFound = true;
                    System.out.println(item.getDescription());
                    if (item instanceof Weapon || item instanceof Armor) {
                        System.out.println("Do you want to equip it? y/n");
                    } else {
                        System.out.println("Do you want to use it? y/n");
                    }
                    answer = yesOrNoQuestion();
                    if (answer == 1) {
                        if (inBattle) {
                            loop = false;
                        }

                        if (item instanceof Weapon || item instanceof Armor) {
                            playerCharacter.equipItem(item);
                            break;
                        } else if (item instanceof Healing healingItem) {
                            playerCharacter.useHealing(healingItem);
                            break;
                        }
                    }
                    else {
                        System.out.println("Do you want to throw it away? y/n");
                        answer = yesOrNoQuestion();
                        if (answer == 1) {
                            playerCharacter.throwAway(item);
                        }
                    }
                    break;
                }
            }
            if (!itemFound) {
                System.out.println("Item was not found in inventory.\nExit inventory? y/n");
                answer = yesOrNoQuestion();
                if (answer == 1) {
                    loop = false;
                }
            }
        } else if (answer == 2) {
            loop = false;
        }
        return loop;
    }

    // Prints out a majority of the characters saved attributes to show the user the characters "stats" and equipment
    public void checkStatsEquipment(){
        System.out.println();
        System.out.println("Name: " + playerCharacter.getName() +
                "\n\nHP: " + playerCharacter.getHp() + "/" + playerCharacter.getMaxHp()+
                "\nDefense: " + playerCharacter.getDefense()+
                "\nAttack: " + playerCharacter.getAttack()+
                "\nExp: " + playerCharacter.getExp()+
                "\nLv: " + playerCharacter.getLv()+
                "\nArmor: " + playerCharacter.getMyArmor().getName()+
                "\nWeapon: " + playerCharacter.getMyWeapon().getName());
        System.out.println("Do you want to check equipment? y/n");
        String choice = read.nextLine();
        if (choice.equalsIgnoreCase("y")){
            System.out.println("Armor: " + playerCharacter.getMyArmor().getDescription()+
                    "\n\nWeapon: " + playerCharacter.getMyWeapon().getDescription());
        }
    }

    // A simple utility method that returns an integer for yes or no questions because they are asked so often
    // It was chosen as an integer instead of boolean because it needed to be able to become a third value in case the user
    // input something other than yes or no and tells the user what to do
    public int yesOrNoQuestion() {
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

    /* A general method for battling an enemy, so the parameter can take in any Enemy from
       the arraylist and make an instance of the subclass of enemy and then proceed with the battle
       It creates a multitude of variables in the start, so they are not continuously generated in the loop and can also be saved for
       certain checks which get updated every iteration of the loop like enemyHP and items in case the user uses an item
       There is a switch statement that acts as the users turn and calls three other methods and also just reduces the enemies health
       After the users turn it checks if the enemy is dead and then the enemy attacks the playerCharacter, and it checks if the playerCharacter is dead afterward
       Currently the game ends after the battle since there is nothing left after it is called in manageRuins but in the future it will continue*/
    public void battle(Enemy opponentTemplate) {
        boolean loop;
        savePlayer = new Player(playerCharacter);
        Enemy opponent = null;
        if (opponentTemplate instanceof Froggit) {
            opponent = new Froggit(opponentTemplate);
        }

        String[] choices = new String[]{"Fight", "Act", "View inventory", "Mercy"};
        String choice;
        int missChance;
        int items;
        Weapon currentWeapon;
        Armor currentArmor;
        double enemyHPPercent;
        double enemyHP;
        double enemyMaxHP = opponent.getMaxHp();

        int willingness = opponent.getWillingness();

        label:
        while (true) {
            items = playerCharacter.getInventory().size();
            currentWeapon = playerCharacter.getMyWeapon();
            currentArmor = playerCharacter.getMyArmor();
            enemyHP = opponent.getHp();


            enemyHPPercent = enemyHP/enemyMaxHP*100;


            loop = true;
            while (loop) {
                System.out.println("Current HP: "+playerCharacter.getHp()+"/"+playerCharacter.getMaxHp()+"\nEnemy HP: " + enemyHPPercent + "%");
                System.out.println("What do you want to do?");
                choice = openMenu(choices);

                switch (choice) {
                    case "1":
                        missChance = generator.nextInt(1,11);
                        if (missChance==1){
                            System.out.println("You missed!");
                        } else {
                            System.out.println("You attacked " + opponent.getVisibleName());
                            opponent.gettingHit(playerCharacter.getAttack());
                        }
                        loop = false;
                        break;
                    case "2":
                        System.out.println("What do you want to do?");
                        opponent.actOptions();
                        loop = false;
                        break;
                    case "3":
                        checkInventory(true);
                        if (playerCharacter.getInventory().size() != items || !currentWeapon.equals(playerCharacter.getMyWeapon()) || !currentArmor.equals(playerCharacter.getMyArmor())) {
                            loop = false;
                        }
                        break;
                    case "4":
                        if (mercyAction(opponent)) {
                            break label;
                        }
                        loop = false;
                        break;
                    default:
                        System.out.println("You need to input a number corresponding to one of the actions!");
                        break;
                }
            }
            if (opponent.isCharacterDead()) {
                wonBattle(opponent);
                break;
            }

            opponent.attacks(playerCharacter);
            if (playerCharacter.isCharacterDead()) {
                gameOver();
                opponent.setHP(opponent.getMaxHp());
                opponent.setSpareable(false);
                opponent.willingnessChange(-willingness);
                opponent.setVisibleName("the monster");
            }
        }
    }

    // One of the options during the player turn, lets them exit the battle either by chance based fleeing or when the enemy is sparable
    // which is when the enemy is either at low enough health or after the unique ACTions have been chosen correctly
    // If the player runs away they don't get any rewards but if they spare then they get 60% of the gold from the battle by calling wonBattle method (100% is from killing)
    public boolean mercyAction(Enemy opponent) {
        System.out.println("What do you want do?");
        System.out.println("1. Spare " + opponent.getVisibleName() +"\n2. Flee from the battle");
        String choice = read.nextLine();
        boolean over = false;

        switch (choice){
            case "1":
                if (opponent.getSparable()) {
                    wonBattle(opponent);
                    over = true;
                } else {
                System.out.println(opponent.getVisibleName() + " isn't spareable right now!");
                }
                break;
            case "2":
                int flee = generator.nextInt(1,11);
                if (flee <=4) {
                    System.out.println("You failed to escape!");
                } else {
                    System.out.println("You escaped successfully!");
                    over = true;
                }
        }
        return over;
    }

    // When the enemy in battle() dies or is spared in mercyAction() this is called and gives exp (execution points) and full amount
    // of gold when killing, or 60% of gold when sparing (they also need money you know?)
    public void wonBattle(Enemy opponent) {
        System.out.println("You won!");
        if (opponent.isCharacterDead()) {
            playerCharacter.expGain(opponent.getExpGain());
            playerCharacter.setGold(opponent.getGoldGain());
            System.out.println("You gained "+opponent.getExpGain()+" EXP and "+opponent.getGoldGain()+" gold.");
        } else {
            playerCharacter.setGold((opponent.getGoldGain()/10)*6);
            System.out.println("You gained "+opponent.getGoldGain()+" gold.");
        }
    }

    // Called when the playerCharacter reaches zero health in battle() and exits the program if you give up or continues the battle from the beginning
    // if you choose to continue which is achieved by making a resetting the enemy and using a saved copy of playerCharacter to
    // make a new playerCharacter and overwrite the current one, so that all data will be the same as when playerCharacter entered battle
    public void gameOver(){
        System.out.println("""
                ⠀⢀⣴⣾⣿⣿⣿⣷⣦⡄⠀⣴⣾⣿⣿⣿⣿⣶⣄⠀⠀
                ⣰⣿⣿⣿⣿⣿⣿⣿⠋⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣌⠛⣿⣿⣿⣿⣿⣿⣿⣿⡆
                ⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢁⣼⣿⣿⣿⣿⣿⣿⣿⣿⠁
                ⠸⣿⣿⣿⣿⣿⣿⣿⡟⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀
                ⠀⠙⣿⣿⣿⣿⣿⣿⣄⠻⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀
                ⠀⠀⠈⠻⣿⣿⣿⣿⣿⣧⡈⢿⣿⣿⣿⣿⡟⠁⠀⠀⠀
                ⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⡇⢸⣿⣿⠟⠉⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠈⠙⢿⡿⠀⡿⠛⠁⠀⠀⠀⠀⠀⠀⠀""");
        while(true) {
            System.out.println("Oh no, you died! Do not give up,"+playerCharacter.getName()+", I believe in you!"+
                    "\nDo you want to continue or give up?");
            System.out.println("1. Continue"+"\n2. Give up");
            String choice = read.nextLine();
            if(choice.equals("1")) {
                playerCharacter = new Player(savePlayer);
                System.out.println("You have been brought back to the beginning of the battle.");
                break;
            } else if(choice.equals("2")) {
                System.out.println("Are you absolutely sure you want to give up? This will exit the game. y/n");
                int answer = yesOrNoQuestion();
                if (answer == 1) {
                    System.exit(0);
                }
            } else {
                System.out.println("You must answer with by putting in 1 or 2 for the first or second option!");
            }
        }
    }

    //TODO: add a mimic battle, copies playerCharacters stats and name with three question marks

    /*
    Will be added in the future to let the user spend gold earned from battles to add items to the playerCharacters inventory arraylist
    public void manageShop() {

    }
     */
}
