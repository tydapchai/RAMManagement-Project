package tool;

public class Inputter {

    /**
     * Prompt user with a message read the user input check if valid or not
     *
     * @param mess the message to display for user to input
     * @return a valid input of type
     */
    public static String inputType(String mess) {
        String type;
        boolean valid = false;
        do {
            type = Tools.inputString(mess);
            if (type.matches(".*\\d")) {
                valid = true;
            } else {
                System.out.println("Invalid type. Must match format DDRX.");
            }
        } while (!valid);
        return type;
    }

    /**
     * Prompt the user with a message and check if a valid input of Bus
     *
     * @param mess the message to display for user to input
     * @return a valid input of bus
     */
    public static String inputBus(String mess) {
        String bus;
        boolean valid = false;
        do {
            bus = Tools.inputString(mess);
            if (bus.matches("\\d+MHz")) {
                valid = true;
            } else {
                System.out.println("Invalid bus. Must be in the format 'number + MHz' (e.g., 2400MHz).");
            }
        } while (!valid);
        return bus;
    }

    /**
     * Prompts the user with a message and checks if the input is blank. If the
     * input is blank, the current value is kept; otherwise, the input is
     * updated.
     *
     * @param mess The message to prompt the user for input.
     * @param currentValue The current value to keep if the input is blank.
     * @return The updated value if input is provided, or the current value if
     * input is blank.
     */
    public static String updateInput(String mess, String currentValue) {
        String input = Tools.inputString(mess);
        return input.trim().isEmpty() ? currentValue : input;
    }

    /**
     * Prompts the user with a message and checks if the input is blank or a
     * valid integer. If the input is blank, the current value is kept;
     * otherwise, the input is updated. If the input is not a valid integer, the
     * current value is kept.
     *
     * @param mess The message to prompt the user for input.
     * @param currentValue The current integer value to keep if the input is
     * blank or invalid.
     * @return The updated integer value if valid input is provided, or the
     * current value if input is blank or invalid.
     */
    public static int updateIntInput(String mess, int currentValue) {
        String input = Tools.inputString(mess);
        try {
            return input.trim().isEmpty() ? currentValue : Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, keeping current value.");
            return currentValue;
        }
    }
    
    /**
     * Prompts the user with a message and check if the input is blank or a valid
     * date. If the input is blank keep the current value. otherwise update date.
     * @param mess The message to prompt the user for input.
     * @param currentValue The current date to keep if the input is blank or invalid.
     * @return a valid date. 
     */
    public static String updateProductionDate(String mess, String currentValue) {
        String input = Tools.inputString(mess);
        if (input.trim().isEmpty()) {
            return currentValue;
        }
        if (isValidMonthYear(input)) {
            return input;
        } else {
            System.out.println("Invalid format, keeping current date.");
            return currentValue;
        }
    }

    /**
     * check if the current value is a valid date or not.
     * @param monthYear the value of the date user want to check.
     * @return true if a valid date. false if not.
     */
    public static boolean isValidMonthYear(String monthYear) {
        if (monthYear.matches("^(0[1-9]|1[0-2])/(\\d{4})$")) {
            String[] parts = monthYear.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]);

            return month >= 1 && month <= 12 && year >= 1800 && year <= 2024;
        }
        return false;
    }
}
