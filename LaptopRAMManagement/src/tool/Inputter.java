package tool;

public class Inputter {

    public static String inputType(String mess) {
        String type;
        boolean valid = false;
        do {
            type = Tools.inputString(mess);
            if (type.matches(".*\\d")) {
                valid = true;
            } else {
                System.out.println("Invalid type. Must contain at least one number.");
            }
        } while (!valid);
        return type;
    }

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

    public static String updateInput(String mess, String currentValue) {
        String input = Tools.inputString(mess);
        return input.trim().isEmpty() ? currentValue : input;
    }

    public static int updateIntInput(String mess, int currentValue) {
        String input = Tools.inputString(mess);
        try {
            return input.trim().isEmpty() ? currentValue : Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format, keeping current value.");
            return currentValue;
        }
    }

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
