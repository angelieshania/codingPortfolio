/**
 * Project       : Join Strings
 *
 * Program Description:
 * This Java program reads information about nodes and edges from the standard input and
 * constructs a linked list data structure. It then traverses the linked list to create a
 * result string by concatenating node strings in the order specified by the linked list.
 * The result string is written to the standard output.
 *
 * The program performs the following tasks:
 * 1. Reads the number of nodes.
 * 2. Reads node strings and stores them in an array.
 * 3. Processes edges between nodes to construct a linked list.
 * 4. Traverses the linked list to create the result string.
 * 5. Writes the result string to the standard output.
 *
 *  * Input:
 *  * - The number of nodes (N) representing the number of words to process.
 *  * - N lines of data for each word, including its content.
 *  * - N-1 lines containing pairs of indices (startNode, endNode) representing operations that rearrange the word sequence.
 *  *
 *  * Output:
 *  * - The resulting concatenated string after applying the specified operations.
 *
 * The program uses arrays to efficiently manage node information and linked nodes. It also
 * uses a StringBuilder to efficiently build the result string. This implementation ensures
 * that the time complexity of the program is linear in the number of nodes.
 */

import java.io.*;
import java.util.*;

public class JoinStrings {
    public static void main(String[] args)
            throws IOException {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Create a BufferedOutputStream to write output to the console
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        // Read the number of nodes from the input
        int numNodes = Integer.parseInt(reader.readLine());

        // Create arrays to store node information
        int[] nodeCache = new int[numNodes + 1];
        int[] linkedNodes = new int[numNodes + 1];
        String[] nodeStrings = new String[numNodes + 1];

        // Read node strings and store them in the array
        int i = 1; // Initialize i outside the loop
        while (i < numNodes + 1) {
            nodeStrings[i] = reader.readLine();
            i++;
        }

        // Initialize variables for processing edges
        int startNode = 1;
        int endNode;
        int currentNode;

        // Process edges and build linked lists
        for (int j = 0; j < numNodes - 1; j++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            startNode = Integer.parseInt(tokenizer.nextToken());
            endNode = Integer.parseInt(tokenizer.nextToken());

            // If the linked node is not set, initialize it
            if (linkedNodes[startNode] == 0) {
                linkedNodes[startNode] = endNode;
                nodeCache[startNode] = endNode;
                continue;
            }

            // Find the end of the linked list for the current node
            currentNode = startNode;
            for (; nodeCache[currentNode] != 0; currentNode = nodeCache[currentNode]) {
            }

            // Update the linked list and cache
            nodeCache[startNode] = endNode;
            linkedNodes[currentNode] = endNode;
        }

        // Build the result string by traversing the linked list
        StringBuilder result = new StringBuilder();
        for (; linkedNodes[startNode] != 0; startNode = linkedNodes[startNode]) {
            result.append(nodeStrings[startNode]);
        }
        result.append(nodeStrings[startNode]);

        // Write the result to the output stream
        writer.write(result.toString().getBytes());

        // Close the input and output streams
        reader.close();
        writer.close();
    }
}






