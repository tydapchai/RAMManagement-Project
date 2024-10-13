package main;

import model.RAMManagementSystem;
import tool.Tools;

public class Main {

    public static void main(String[] args) {
        RAMManagementSystem system = new RAMManagementSystem("src/data/RAMModules.dat");

        int choice = 0;
        do {
            choice = Tools.inputInt(
                    "-------------------RAM Management System------------------------\n"
                    + "              1. Add a RAM Item \n"
                    + "              2. Search by Type/Bus/Brand \n"
                    + "              3. Update Item Information \n"
                    + "              4. Delete Item \n"
                    + "              5. Show All Active Items \n"
                    + "              6. Exit \n"
                    + "----------------------------------------------------------------\n"
                    + "Enter your choice: ");

            switch (choice) {
                case 1:
                    system.addItem();
                    break;
                case 2:
                    system.search();
                    break;
                case 3:
                    system.updateItem();
                    break;
                case 4:
                    system.deleteItem();
                    break;
                case 5:
                    system.showAllItems();
                    break;
                case 6:
                   
                    if (Tools.continueFunction("Do you want to save changes before exiting? (y/n): ")) {
                        system.saveData();
                        System.out.println("Data saved successfully. Exiting program...");
                    } else {
                        System.out.println("Exiting without saving...");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }
}