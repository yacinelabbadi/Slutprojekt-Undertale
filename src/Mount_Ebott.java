import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mount_Ebott {
    Scanner read = new Scanner(System.in);
    Random generator = new Random();


    public Mount_Ebott(){
        start();
    }
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
        Character player = new Character(name);
        Weapon stick = new Weapon(2, "stick", "Stick\nAttack: 2\nA stick you randomly found on the ground, could help in sticky situations.");
        Armor bandAid = new Armor(1, "bandage", "Band-aid\nDefense: 1\nA band-aid you had on you before falling down, it has cute puppies on it!");

        player.equipItem(stick);
        player.equipItem(bandAid);

        // Add healing items to inventory



        ruins(player);
    }

    public void ruins(Character player){
        Enemy froggit = new Enemy("Froggit", 4, 4, 20, 10, 6, 2, "Froggit - Atk 4 Def 4"+
                "\nA weird frog creature... it talks?!");
        String choice;

        System.out.println("""
        You slowly open your eyes as you awake.
        It seems you have fallen down into a cave while traveling on Mount Ebott, the mountain rumored to be home to the monsters.
        You look around but don't see much except golden flowers and a large purple door.
        What do you want to do?""");
        String[] choices = new String[] {"View inventory (equipped items won't show up here)", "Check stats and equipment", "Go out the large purple door"};
        boolean loop = true;
        while (loop) {
            choice = menu(choices);


            switch (choice) {
                case "1" -> checkInventory(player);
                case "2" -> checkStatsEquipment(player);
                case "3" -> loop = false;
                default -> System.out.println("You need to write a number corresponding to the choices!");
            }
        }

        System.out.println("You walk out the large purple door and see a dark room with a faint light in the distance."+
                "\nAs you try to walk your way to the light, something emerges from the darkness!");
        player = battle(player, froggit);
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

    public String menu(String[] menu) {
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
            if (player.getInventory().isEmpty()) {
                System.out.println("You have no items");
                break;
            }
            System.out.println("\nDo you want check one of the items? y/n");
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
                        } else if (answer == 1 && item instanceof Healing) {
                            Healing heal = (Healing) item;
                            player.useHealing(heal);
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

    public Character battle(Character player, Enemy opponent) {
        boolean loop = true;
        Character savePlayer = new Character(player);
        String[] choices = new String[]{"Fight", "Act", "View inventory", "Mercy"};
        String choice;
        int missChance;
        int items;
        Weapon currentWeapon;
        Armor currentArmor;
        double enemyHPProcent;
        double enemyHP;
        double enemyMaxHP;

        label:
        while (true) {
            items = player.getInventory().size();
            currentWeapon = player.getMyWeapon();
            currentArmor = player.getMyArmor();
            enemyHP = opponent.getHp();
            enemyMaxHP = opponent.getMaxHp();

            enemyHPProcent = enemyHP/enemyMaxHP*100;


            loop = true;
            while (loop) {
                System.out.println("Current HP: "+player.getHp()+"/"+player.getMaxHp()+"\nEnemy HP: " + enemyHPProcent + "%");
                System.out.println("What do you want to do?");
                choice = menu(choices);

                switch (choice) {
                    case "1":
                        missChance = generator.nextInt(1,11);
                        if (missChance==1){
                            System.out.println("You missed!");
                        } else {
                            opponent.setHp(-player.getAttack());
                            System.out.println("You attacked " + opponent.getVisibleName());
                        }
                        loop = false;
                        break;
                    case "2":
                        act(player, opponent);
                        break;
                    case "3": checkInventory(player);
                        if (player.getInventory().size() < items || !currentWeapon.equals(player.getMyWeapon()) || !currentArmor.equals(player.getMyArmor())) {
                            loop = false;
                        }
                        break;
                    case "4":
                        if (mercy(player, opponent)) {
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
                wonBattle(player, opponent);
                break;
            }

            // opponent.talk()
            opponent.attacks(player);
            if (player.isCharacterDead()) {
                player = gameOver(player, opponent, savePlayer);
            }
        }

        // If spared, 60% gold, if killed, 100% gold
        return player;
    }

    public void act(Character player, Enemy opponent) {

        System.out.println("What do you want to do?");
        if (opponent.getName().equals("Froggit")) {
            froggit(opponent);
        }
    }

    public boolean mercy(Character player, Enemy opponent) {
        System.out.println("What do you want do?");
        System.out.println("1. Spare the enemy"+"\n2. Flee from the battle");
        String choice = read.nextLine();
        boolean over = false;

        switch (choice){
            case "1":
                if (opponent.getSparable()) {
                    wonBattle(player, opponent);
                    over = true;
                } else {
                System.out.println(opponent.getName() + " isn't spareable right now!");
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

    public void wonBattle(Character player, Enemy opponent) {
        if (opponent.isCharacterDead()) {
            player.expGain(opponent.getExpGain());
            player.setGold(opponent.getGoldGain());
        } else {
            player.setGold((opponent.getGold()/10)*6);
        }
    }

    public void test(Character player){
        player.setGold(10);
    }

    public Character gameOver(Character player, Enemy opponent, Character savePlayer){
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
            System.out.println("Oh no, you died! Do not give up,"+player.getName()+", I believe in you!"+
                    "\nDo you want to continue or give up?");
            System.out.println("1. Continue"+"\n2. Give up");
            String choice = read.nextLine();
            if(choice.equals("1")) {
                player = new Character(savePlayer);
                opponent.setHp(opponent.getMaxHp());
                System.out.println("You have been brought back to the beginning of the battle.");
                break;
            } else if(choice.equals("2")) {
                System.out.println("Are you absolutely sure you want to give up? This will exit the game. y/n");
                int answer = yesOrNo();
                if (answer == 1) {
                    System.exit(0);
                }
            } else {
                System.out.println("You must answer with by putting in 1 or 2 for the first or second option!");
            }
        }
        return player;
    }

    public void froggit(Enemy opponent) {
        String choice;
        String[] choices = new String[] {"Check " + opponent.getVisibleName(), "Compliment " + opponent.getVisibleName(), "Threaten " + opponent.getVisibleName()};

        label:
        while(true) {
            choice = menu(choices);
            switch (choice) {
                case "1":
                    System.out.println(opponent.getDescription());
                    break label;
                case "2":
                    System.out.println(opponent.getVisibleName() + " didn't understand you, but was flattered anyway");
                    opponent.willingnessChange(1);
                    break label;
                case "3":
                    System.out.println(opponent.getVisibleName() + " didn't understand you, but was scared anyway");
                    opponent.willingnessChange(1);
                    break label;
                default:
                    System.out.println("You need to write a number corresponding to the choices!");
                    break;
            }
        }
    }

    public void shop() {

    }
}
