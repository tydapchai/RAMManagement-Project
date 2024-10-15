package model;

import filehandle.FileHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import tool.Tools;
import tool.Inputter;

public class RAMManagementSystem {

    private HashMap<String, RAM> ramItems;
    private FileHandler fileHandler;
    private String k = String.format("%-15s %-15s %-15s %-15s %-12s %-10s %-10s",
            "RAM Code", "Type", "Bus", "Brand", "Quantity", "Date", "Active");

    public RAMManagementSystem(String fileName) {

        this.fileHandler = new FileHandler(fileName);
        this.ramItems = new HashMap<>();
        List<RAM> loadedRAM = fileHandler.loadFromFile();
        if (loadedRAM != null) {
            for (RAM ram : loadedRAM) {
                this.ramItems.put(ram.getCode(), ram);
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void saveData() {
        List<RAM> ramList = new ArrayList<>(ramItems.values());
        fileHandler.saveToFile(ramList);
    }

    /**
     * add item
     */
    public void addItem() {
        do {
            RAM ram = RAMFactory.createRAM(
                    Inputter.inputType("Enter type of RAM: "),
                    Inputter.inputBus("Enter bus speed of RAM: "),
                    Tools.inputString("Enter brand of RAM: "),
                    Tools.inputInt("Enter quantity of RAM: "),
                    inputMonthYear());
            ram.setCode(generateCode(ram.getType()));
            this.ramItems.put(ram.getCode(), ram);
            System.out.println("RAM item added successfully.");
        } while (Tools.inputInt(
                "---------Sub Menu---------\n"
                + "1. Add another RAM \n"
                + "2. Return to main menu \n"
                + "------------------------\n"
                + "Enter your choice: ") == 1);
    }

    public List<RAM> toList() {
        List<RAM> RAMList = new ArrayList<>(ramItems.values());

        Collections.sort(RAMList, (RAM r1, RAM r2) -> {
            int typeCompare = r1.getType().compareTo(r2.getType());

            if (typeCompare == 0) {
                int bus1 = Integer.parseInt(r1.getBus().replaceAll("[^0-9]", ""));
                int bus2 = Integer.parseInt(r2.getBus().replaceAll("[^0-9]", ""));

                int busCompare = Integer.compare(bus2, bus1);

                if (busCompare == 0) {
                    return r1.getBrand().compareTo(r2.getBrand());
                }
                return busCompare;
            }
            return typeCompare;
        });

        return RAMList;
    }

    public String generateCode(String type) {
        int maxOrder = 0;

        for (RAM ram : ramItems.values()) {
            if (ram.getType().equalsIgnoreCase(type)) {
                String[] parts = ram.getCode().split("_");
                if (parts.length == 2) {
                    int order = Integer.parseInt(parts[1]);
                    if (order > maxOrder) {
                        maxOrder = order;
                    }
                }
            }
        }
        String code = "RAM" + type + "_" + (maxOrder + 1);
        return code;
    }

    public String inputMonthYear() {
        String monthYear;
        boolean validInput = false;

        do {
            monthYear = Tools.inputString("Enter date of production (MM/YYYY): ");

            if (Inputter.isValidMonthYear(monthYear)) {
                validInput = true;
            } else {
                System.out.println("Invalid format. Enter production date the right format MM/YYYY.");
            }
        } while (!validInput);

        return monthYear;
    }

    /**
     * update item if leave blank keep the current info
     */
    public void updateItem() {
        String code = Tools.inputString("Enter RAM code to update: ");
        RAM ram = ramItems.get(code);

        if (ram == null) {
            System.out.println("No RAM found with the given code.");
            return;
        }

        System.out.println("Current RAM details:");
        System.out.println(ram);

        ram.setType(Inputter.updateInput("Enter new type of RAM : ", ram.getType()));
        ram.setBus(Inputter.updateInput("Enter new bus speed of RAM : ", ram.getBus()));
        ram.setBrand(Inputter.updateInput("Enter new brand of RAM : ", ram.getBrand()));
        ram.setQuantity(Inputter.updateIntInput("Enter new quantity of RAM : ", ram.getQuantity()));
        ram.setProduction_month_year(Inputter.updateProductionDate("Enter new production date (MM/YYYY) : ", ram.getProduction_month_year()));

        ramItems.put(code, ram);
        System.out.println("RAM updated successfully.");
    }

    /**
     * delete item base on status active
     */
    public void deleteItem() {
        String check = Tools.inputString("Enter RAM code to delete: ");
        RAM ram = ramItems.get(check);

        if (ram == null) {
            System.out.println("No such RAM found with the given code.");
            return;
        }

        if (Tools.continueFunction("Do you wish to delete this ? y/n: ")) {
            ram.setFlag(false);
            System.out.println("RAM item deleted successfully.");
        }
    }

    /**
     * show all item
     */
    public void showAllItems() {
        List<RAM> sortedRAMList = this.toList();

        if (sortedRAMList.isEmpty()) {
            System.out.println("No RAM items available.");
            return;
        }

        System.out.println(k);

        sortedRAMList.stream().filter((item) -> (item.getFlag())).forEach((item) -> {
            System.out.println(item);
        });
    }

    /**
     * search ram with sub menu
     */
    public void search() {
        int choice;
        do {
            choice = Tools.inputInt(
                    "------------Search sub-Menu------------\n"
                    + "         1.Search by Type \n"
                    + "         2.Search by Bus \n"
                    + "         3.Search by Brand \n"
                    + "         0.Exit to main menu \n"
                    + "---------------------------------------\n"
                    + "Enter your choice: ");
            switch (choice) {
                case 1:
                    String tempType = Tools.inputString("Enter Type to search: ");
                    boolean foundType = false;
                    for (RAM ram : this.toList()) {
                        if (ram.getType().equalsIgnoreCase(tempType)) {
                            System.out.println(ram);
                            foundType = true;
                        }
                    }
                    if (!foundType) {
                        System.out.println("No such Type info.");
                    }
                    break;
                case 2:
                    String tempBus = Tools.inputString("Enter BUS to search: ");
                    boolean foundBus = false;
                    for (RAM ram : this.toList()) {
                        if (ram.getBus().equalsIgnoreCase(tempBus)) {
                            System.out.println(k);
                            System.out.println(ram);
                            foundBus = true;
                        }
                    }
                    if (!foundBus) {
                        System.out.println("No such BUS info.");
                    }
                    break;
                case 3:
                    String tempBrand = Tools.inputString("Enter Brand to search: ");
                    boolean foundBrand = false;
                    for (RAM ram : this.toList()) {
                        if (ram.getBrand().equalsIgnoreCase(tempBrand)) {
                            System.out.println(k);
                            System.out.println(ram);
                            foundBrand = true;
                        }
                    }
                    if (!foundBrand) {
                        System.out.println("No such Brand info.");
                    }
                    break;
                case 0:
                    System.out.println("Exit to main menu....");
                    break;
                default:
                    System.out.println("Invalid input, please enter between 0 - 3.");
                    break;
            }
        } while (choice != 0);
    }

    public void showByBusQuantity() {

        List<RAM> sortedRAMList = this.toList();

        if (sortedRAMList.isEmpty()) {
            System.out.println("No RAM items available.");
            return;
        }

        System.out.println(k);
        String check = Tools.inputString("Enter bus to display:");
        int under = Tools.inputInt("Enter the lower quantity:");
        int upper = Tools.inputInt("Enter the upper quantity:");

        for (RAM ram : this.toList()) {
            if (ram.getBus().startsWith(check) && ram.getQuantity() >= under && ram.getQuantity() <= upper) {
                System.out.println(ram);
            }
        }

    }

}
