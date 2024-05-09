// Project: PeaSoup and Pancake

import java.util.Scanner;

public class PeaSoupandPancake {
    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner input = new Scanner(System.in);
        // Read the number of restaurants
        int restaurantTotal = input.nextInt();
        // Consume the newline character left after reading an integer
        input.nextLine();

        // Initialize a variable to store the restaurant name
        String restaurantName = "";
        // Initialize a flag to track whether a suitable restaurant is found
        boolean foundRestaurant = false;

        // Loop through each restaurant
        for (int i = 0; i < restaurantTotal; i++) {
            // Read the number of menu items for the current restaurant
            int menuItemsTotal = input.nextInt();
            // Consume the newline character left after reading an integer
            input.nextLine();

            // Initialize a flag for pea soup presence
            boolean containPeaSoup = false;
            // Initialize a flag for pancakes presence
            boolean containPancakes = false;

            // Loop through each menu item including the restaurant name
            for (int j = 0; j < menuItemsTotal + 1; j++) {
                // Read the current menu item
                String itemMenu = input.nextLine();

                // If it's the first item, store it as the restaurant name
                if (j == 0) {
                    restaurantName = itemMenu;
                    // Check if the current item is "pea soup"
                } else if (itemMenu.equals("pea soup")) {
                    containPeaSoup = true;
                    // Check if the current item is "pancakes"
                } else if (itemMenu.equals("pancakes")) {
                    containPancakes = true;
                }
            }

            // Check if both "pea soup" and "pancakes" are present
            if (containPeaSoup && containPancakes) {
                // Set the foundRestaurant flag to true
                foundRestaurant = true;
                // Exit the loop since a suitable restaurant is found
                break;
            }
        }

        // Close the Scanner
        input.close();

        // If a suitable restaurant is found
        if (foundRestaurant) {
            // Output the name of the restaurant
            System.out.println(restaurantName);
        } else {
            // Output the default message
            System.out.println("Anywhere is fine I guess");
        }
    }
}