package model;

public class RAMFactory {

    public static RAM createRAM(String type, String bus, String brand, int quantity, String productionMonthYear) {
        return new RAM("", type, bus, brand, quantity, productionMonthYear, true);
    }
}
