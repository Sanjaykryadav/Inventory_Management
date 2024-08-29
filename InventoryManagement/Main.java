import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();
        boolean running = true;

        while (running) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Item");
            System.out.println("4. List Items");
            System.out.println("5. List Items by Category");
            System.out.println("6. Search Item");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    manager.addItem(new Item(id, name, quantity, price, category));
                    System.out.println("Item added.");
                    break;

                case 2:
                    System.out.print("Enter ID of item to remove: ");
                    id = scanner.nextInt();
                    manager.removeItem(id);
                    System.out.println("Item removed.");
                    break;

                case 3:
                    System.out.print("Enter ID of item to update: ");
                    id = scanner.nextInt();
                    System.out.print("Enter new Quantity: ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter new Price: ");
                    price = scanner.nextDouble();
                    manager.updateItem(id, quantity, price);
                    System.out.println("Item updated.");
                    break;

                case 4:
                    System.out.println("Current Inventory:");
                    manager.listItems();
                    break;

                case 5:
                    System.out.print("Enter category: ");
                    String cat = scanner.nextLine();
                    System.out.println("Items in category " + cat + ":");
                    manager.listItemsByCategory(cat);
                    break;

                case 6:
                    System.out.print("Enter Item ID or Name to search: ");
                    String searchQuery = scanner.nextLine();
                    manager.searchItem(searchQuery);
                    break;

                case 7:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
