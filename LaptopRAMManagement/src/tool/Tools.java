package tool;

import java.util.Scanner;

public class Tools {
private static final Scanner sc = new Scanner(System.in);
    /**
     * Prompts the user with a message and reads a trimmed input string. The
     * method removes leading and trailing spaces from the user's input.
     * Additionally, it replaces multiple consecutive spaces between words with
     * a single space.
     *
     * @param mess The message to display prompting the user for input.
     * @return A string with leading/trailing spaces removed and multiple spaces
     * between words replaced by a single space.
     */
    public static String inputString(String mess) {
        System.out.print(mess);
        String result = sc.nextLine().trim();
        result = result.replaceAll("\\s+", " ");
        return result;
    }

    /**
     * Prompt the user with a message and check if input a valid integer or not
     * It continues to prompt the user until the input is valid
     *
     * @param mess The message display to prompt for user to input
     * @return the valid integer input by user
     */
    public static int inputInt(String mess) {
        int result = 0;
        boolean valid = false;
        do {
            System.out.print(mess);
            try {
                String input = sc.nextLine().replaceAll("\\s", "");
                    result = Integer.parseInt(input);
                    valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter an integer number ");
            }
        } while (!valid);

        return result;
    }

    /**
     * Prompt the user with a message and check if input a valid float or not It
     * continues to prompt the user until the input is valid
     *
     * @param mess the message to prompt the user
     * @return a valid float input by the user
     */
    public static float inputFloat(String mess) {
        float result = 0;
        boolean valid = false;
        do {
            System.out.print(mess);
            try {
                String input = sc.nextLine().replaceAll("\\s", "");
                result = Float.parseFloat(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a float number.");
            }
        } while (!valid);

        return result;
    }

    /**
     * Prompt the user with a message and check if input is y or n It continue
     * to prompt the user until the user input y or n
     *
     * @param prompt show a message to user for input
     * @return s user choice
     */
    public static boolean continueFunction(String prompt) {
        String input;
        do {
            input = Tools.inputString(prompt).trim().toLowerCase();
            switch (input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    break;
            }
        } while (!input.equals("y") && !input.equals("n"));
        return false; 
    }

}
