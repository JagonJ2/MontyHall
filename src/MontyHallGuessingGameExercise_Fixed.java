
import java.util.Random;
import java.util.Scanner;

/**
 * Monty Hall Guessing Game.
 * The user chooses which door to open.
 * Monty opens one of the remaining doors with a goat behind.
 * The user decides whether to switch door or not.
 * Since Monty knows what is hidden behind the doors, there is a greater chance to win if the user switches door.
 */
public class MontyHallGuessingGameExercise_Fixed {
    /**
     * Runs the guessing game
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] door = {0, 0, 0};
        randomlyPlaceAPrize(door);

        System.out.println("Pick a door (0, 1, or 2): "); // todo byta till 1 2 3
        int playerChoice = scanner.nextInt(); // todo fixa säker hets fel
        System.out.println("You picked door " + playerChoice + "!");

        int montysChoice = montySelectsAGoat(door, playerChoice);
        openDoor(montysChoice, door);

        System.out.println("Monty asks: Do you want to switch door? (yes/no)");
        scanner.nextLine(); //Flush the newline character in the scanner
        boolean switchDoor = playerWantsToSwitchDoor(scanner);

        if (switchDoor) {
            playerChoice = switchDoor(playerChoice, montysChoice);
        }

        determineWinner(door, playerChoice);
        scanner.close();
    }


    /***
     * This method will randomly place the prize in one of the doors, that will be symbolised by a 1.
     * @param doors Gets the refrense from the door array so the prize can be put in the array directly.
     */
    public static void randomlyPlaceAPrize(int[] doors) {
        Random random = new Random();
        int randomNumber = random.nextInt(doors.length - 1); // stog +1.
        doors[randomNumber] = 1;
    }


    /***
     * This picks a random door and check so the randomly picked door is not the door with the prize and not the door the player picked.
     * If this is true it will return the index of the picked door. Otherwise it will pick a new door and try agein.
     * @param doors Gets the door array with the goat and prize.
     * @param playerChoice The door the player chose.
     * @return Returns the door that will be opend.
     */
    private static int montySelectsAGoat(int[] doors, int playerChoice) {
        Random random = new Random();
        int choice;

        while (true) {
            choice = random.nextInt(3);
            if (choice != playerChoice && doors[choice] == 0) {
                return choice;
            }
        }
    }


    /***
     * Will check if the door to be oppen has a goat or prize and then writes out wich door was opened and what it containd.
     * @param doorToBeOpen The door that should be opened.
     * @param doors The door array with the goat and prize.
     */
    public static void openDoor(int doorToBeOpen, int[] doors) {
        String contains = "";
        if (doors[doorToBeOpen] == 0) {
            contains = "goat";
        } else {
            contains = "prize";
        }
        System.out.println("Monty opens door " + doorToBeOpen + " and shows you a " + contains);
    }


    /***
     * Lets the player answer the qustion if the player want to switch door.
     * @param scan Import the scanner object so the player can input the answer.
     * @return If the player input was yes the method will return true, else false.
     */
    private static boolean playerWantsToSwitchDoor(Scanner scan) {
        String choice = scan.nextLine().trim();
        if (choice.equals("yes")) {
            return true;
        }
        return false;
    }


    /***
     * Will change the players chois to the index not chosen by the player or monty/the door that was opened.
     * @param playerChoice The door the player chosed.
     * @param montysChoice The door that was opened.
     * @return Returns the door the player will change to.
     */
    private static int switchDoor(int playerChoice, int montysChoice) {
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && i != montysChoice) { // det stog ||
                return i;
            }
        }
        return playerChoice;
    }


    /***
     * Writes out if you won or lost. By checking if the door place index the player have chosen containds a 1 (the prize).
     * @param doors Will get the array for the door to check.
     * @param playerChoice The door the player have chosen.
     */
    private static void determineWinner(int[] doors, int playerChoice) {
        if (doors[playerChoice] == 1) {
            System.out.println("You won!");
        } else {
            System.out.println("You got the goat! :(");
        }
    }
}


