/**
 * Project       : Nicknames
 *
 * Program Description:
 * This Java program implements an AVL Tree for efficient storage and querying of a list of names.
   The program reads input data from standard input (stdin) and performs queries on a collection of names
   stored in an AVL (Adelson-Velsky and Landis) Tree data structure. The AVL Tree maintains a balanced
   binary search tree, ensuring efficient insertion and retrieval operations.

 * Input:
    - The program expects the following input from stdin:
    - An integer 'numNames' representing the number of names to be inserted into the AVL Tree.
    - 'numNames' lines, each containing a name (a string) to be inserted into the AVL Tree.
    - An integer 'numQueries' representing the number of queries to be performed.
    - 'numQueries' lines, each containing a query string (a string to search for in the AVL Tree).

 * Output:
   - The program produces the following output to stdout:
   - For each query, it outputs the number of names in the AVL Tree that match the query string,
   - followed by a newline character.

 * Implementation Details:
   - The program uses an AVL Tree to store the names. AVL Trees are self-balancing binary search trees
     that maintain a balance factor for each node, ensuring that the tree remains relatively balanced.
     This property leads to efficient search, insertion, and deletion operations.
   - The AVL Tree is constructed as a class 'AVLTree' with methods to insert names ('insert') and validate
     queries ('validateQuery').
   - The 'AVLNode' class represents the nodes in the AVL Tree and is responsible for updating node height,
     size, and performing rotations.
   - Queries are performed efficiently by finding the highest valid node for the query and then scanning
     the left and right subtrees for additional matches.
 */


import java.io.BufferedReader; // Import for reading input
import java.io.BufferedWriter; // Import for writing output
import java.io.IOException; // Import for handling exceptions
import java.io.InputStreamReader; // Import for reading input from standard input
import java.io.OutputStreamWriter; // Import for writing output to standard output

public class Nicknames {
    public static void main(String[] args) throws IOException {
        // Initialize input and output readers/writers
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); // Create a reader for input
        BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(System.out)); // Create a writer for output

        // Create an instance of the AVLTree
        AVLTree customTree = new AVLTree();

        // Read the number of names to insert
        int numNames = Integer.parseInt(inputReader.readLine());

        // Insert names into the AVL tree
        for (int i = 0; i < numNames; i++) {
            String newName = inputReader.readLine(); // Read a name
            customTree.insert(newName); // Insert the name into the AVL tree
        }

        // Read the number of queries
        int numQueries = Integer.parseInt(inputReader.readLine());

        // Create a string builder to store the output
        StringBuilder output = new StringBuilder();

        // Process each query and append results to the output
        for (int j = 0; j < numQueries; j++) {
            String query = inputReader.readLine(); // Read a query
            int matches = customTree.validateQuery(query); // Validate the query and count the matches
            output.append(matches).append("\n"); // Append the result to the output
        }

        // Write the output to the output writer
        outputWriter.write(output.toString());
        outputWriter.close(); // Close the output writer
    }
}

class AVLTree {
    private AVLNode root; // Root of the AVL tree

    public void insert(String key) {
        // Insert a key into the AVL tree
        this.root = insertNode(this.root, key);
    }

    public int validateQuery(String query) {
        // Find the highest valid node for the query
        AVLNode highestValid = findHighestValidNode(this.root, query);

        if (highestValid == null) {
            return 0; // If no valid node found, return 0
        }

        return 1 + scanLeft(highestValid.left, query) + scanRight(highestValid.right, query); // Count matches
    }

    private AVLNode insertNode(AVLNode node, String key) {
        // Insert a key into a subtree and maintain AVL balance

        if (node == null) {
            return new AVLNode(key); // If the node is null, create a new node
        }

        int compare = key.compareTo(node.key); // Compare the key with the current node's key

        if (compare < 0) {
            node.left = insertNode(node.left, key); // If smaller, insert on the left
        } else if (compare > 0) {
            node.right = insertNode(node.right, key); // If larger, insert on the right
        }

        node.updateSizeAndHeight(); // Update size and height
        return performRotation(node); // Perform AVL rotations
    }

    private AVLNode findHighestValidNode(AVLNode node, String query) {
        // Find the highest valid node in the tree with respect to a query

        if (node == null) {
            return null; // If the node is null, no valid node is found
        }

        String current = node.key; // Get the key of the current node
        int compare = query.compareTo(current); // Compare the query with the current node's key

        if (compare == 0 || current.startsWith(query)) {
            return node; // If a valid node is found, return it
        }

        if (compare < 0) {
            return findHighestValidNode(node.left, query); // Recursively search the left subtree
        } else {
            return findHighestValidNode(node.right, query); // Recursively search the right subtree
        }
    }

    private int scanLeft(AVLNode node, String query) {
        if (node == null) {
            return 0; // If the node is null, no matches are found
        }

        String current = node.key; // Get the key of the current node
        int compare = query.compareTo(current); // Compare the query with the current node's key

        if (compare == 0 || current.startsWith(query)) {
            return 1 + scanLeft(node.left, query) + node.getCount(node.right); // Count matches
        } else if (compare < 0) {
            return scanLeft(node.left, query); // Recursively search the left subtree
        } else {
            return scanLeft(node.right, query); // Recursively search the right subtree
        }
    }

    private int scanRight(AVLNode node, String query) {
        if (node == null) {
            return 0; // If the node is null, no matches are found
        }

        String current = node.key; // Get the key of the current node
        int compare = query.compareTo(current); // Compare the query with the current node's key

        if (compare == 0 || current.startsWith(query)) {
            return 1 + scanRight(node.right, query) + node.getCount(node.left); // Count matches
        } else if (compare < 0) {
            return scanRight(node.left, query); // Recursively search the left subtree
        } else {
            return scanRight(node.right, query); // Recursively search the right subtree
        }
    }

    private AVLNode performRotation(AVLNode node) {
        int balance = node.getBalance(); // Get the balance factor of the node

        if (balance > 1) {
            if (node.left.getBalance() < 0) {
                node.left = rotateLeft(node.left); // Rotate left
            }

            return rotateRight(node); // Rotate right
        } else if (balance < -1) {
            if (node.right.getBalance() > 0) {
                node.right = rotateRight(node.right); // Rotate right
            }

            return rotateLeft(node); // Rotate left
        }

        return node; // Return the node after rotation
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode rightSubtree = node.right; // Get the right subtree
        AVLNode leftOfRight = rightSubtree.left; // Get the left subtree of the right subtree

        rightSubtree.left = node; // Perform left rotation
        node.right = leftOfRight; // Update right subtree

        node.updateSizeAndHeight(); // Update size and height
        rightSubtree.updateSizeAndHeight(); // Update size and height of the right subtree

        return rightSubtree; // Return the new root after rotation
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode leftSubtree = node.left; // Get the left subtree
        AVLNode rightOfLeft = leftSubtree.right; // Get the right subtree of the left subtree

        leftSubtree.right = node; // Perform right rotation
        node.left = rightOfLeft; // Update left subtree

        node.updateSizeAndHeight(); // Update size and height
        leftSubtree.updateSizeAndHeight(); // Update size and height of the left subtree

        return leftSubtree; // Return the new root after rotation
    }
}

class AVLNode {
    AVLNode left; // Left child of the node
    AVLNode right; // Right child of the node
    String key; // Key (data) of the node
    int height; // Height of the node
    int count; // Number of nodes in the subtree rooted at this node

    AVLNode(String key) {
        // Initialize an AVL node with a key
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1;
        this.count = 1;
    }

    void updateSizeAndHeight() {
        // Update the size and height of the node
        this.height = 1 + Math.max(getHeight(this.left), getHeight(this.right));
        this.count = getCount(this.left) + getCount(this.right) + 1;
    }

    private int getHeight(AVLNode node) {
        // Get the height of a node (or 0 if the node is null)
        return (node != null) ? node.height : 0;
    }

    int getCount(AVLNode node) {
        // Get the count (size) of a node (or 0 if the node is null)
        return (node != null) ? node.count : 0;
    }

    int getBalance() {
        // Get the balance factor of the node
        return getHeight(this.left) - getHeight(this.right);
    }
}