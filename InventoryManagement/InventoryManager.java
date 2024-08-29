import java.io.*;
import java.util.*;

public class InventoryManager {
    private Map<Integer, Item> inventory = new HashMap<>();
    private static final String FILE_NAME = "inventory.txt";

    public InventoryManager() {
        loadFromFile();
    }

    // Add item to inventory
    public void addItem(Item item) {
        inventory.put(item.getId(), item);
        saveToFile();
    }

    // Remove item by ID
    public void removeItem(int id) {
        if (inventory.containsKey(id)) {
            inventory.remove(id);
            saveToFile();
        } else {
            System.out.println("Item not found.");
        }
    }

    // Update item quantity and price
    public void updateItem(int id, int quantity, double price) {
        if (inventory.containsKey(id)) {
            Item item = inventory.get(id);
            item.setQuantity(quantity);
            item.setPrice(price);
            saveToFile();
        } else {
            System.out.println("Item not found.");
        }
    }

    // List all items in the inventory
    public void listItems() {
        for (Item item : inventory.values()) {
            System.out.println(item);
        }
    }

    // List items by category
    public void listItemsByCategory(String category) {
        boolean found = false;
        for (Item item : inventory.values()) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in this category.");
        }
    }

    // Search for an item by ID or Name
    public void searchItem(String searchQuery) {
        boolean found = false;
        for (Item item : inventory.values()) {
            if (String.valueOf(item.getId()).equals(searchQuery) ||
                item.getName().equalsIgnoreCase(searchQuery)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // Save inventory to file
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : inventory.values()) {
                writer.println(item.getId() + "," + item.getName() + "," +
                               item.getQuantity() + "," + item.getPrice() + "," +
                               item.getCategory());
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory to file.");
        }
    }

    // Load inventory from file
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                // Validate the number of fields
                if (parts.length != 5) {
                    System.out.println("Invalid data format in file: " + line);
                    continue;  // Skip this line
                }
    
                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    String category = parts[4];
                    inventory.put(id, new Item(id, name, quantity, price, category));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in line: " + line);
                    continue;  // Skip this line
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found. Starting with an empty inventory.");
        } catch (IOException e) {
            System.out.println("Error reading inventory file.");
        }
    }
    
}
