package model;

public class RAMFactory {
    public static RAM createRAM(String type, String bus, String brand, int quantity, String productionMonthYear) {
        String code = generateCode(type);
        return new RAM(code, type, bus, brand, quantity, productionMonthYear, true);
    }

    private static String generateCode(String type) {
       
        int maxOrder = 0; 
        return "RAM" + type + "_" + (maxOrder + 1);
    }
}