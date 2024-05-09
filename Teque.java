/**
 * Project       : Teque
 *
 * Program Description:
 * This Java program simulates a data structure that combines two queues, a front queue
 * and a back queue. It processes a series of commands provided through standard input
 * and performs the following operations:
 * 1. "get x": Retrieve and output the element at position x in the combined queues.
 * 2. "push_back x": Add the element x to the back queue.
 * 3. "push_front x": Add the element x to the front queue.
 *
 * The program maintains two HashMaps to represent the front and back queues, and it
 * dynamically balances the queues to optimize performance. It reads commands and values
 * from standard input, processes them according to the specified operations, and prints
 * the results to standard output.
 *
 * Input:
 * - The first line contains an integer N, the number of queries to be processed.
 * - N lines follow, each containing a command and a value separated by a space.
 *   - "get x": Retrieve the x-th element from the combined queues.
 *   - "push_back x": Add x to the back queue.
 *   - "push_front x": Add x to the front queue.
 *
 * Output:
 * - For each "get" command, the program outputs the value at the specified position.
 * - The program uses BufferedOutputStream for efficient output.
 *
 * This program efficiently manages front and back queues, ensuring balanced processing
 * and optimized retrieval of elements based on the "get" command positions.
 */

import java.util.StringTokenizer;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;

public class Teque {
    public static void main(String[] args)
            throws Exception {
        // Create a BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Create a BufferedOutputStream to write output to the console
        BufferedOutputStream writer = new BufferedOutputStream(System.out);

        // Read the number of queries from the input
        int numQueries = Integer.parseInt(reader.readLine());

        // Create two HashMaps to represent front and back queues
        HashMap<Integer, Integer> frontQueue = new HashMap<>();
        HashMap<Integer, Integer> backQueue = new HashMap<>();

        // Initialize heads and tails for the front and back queues
        int frontQueueHead = -1;
        int backQueueHead = -1;
        int frontQueueTail = 0;
        int backQueueTail = 0;

        // Create a StringBuilder to store the output
        StringBuilder output = new StringBuilder();

        // Initialize a counter for the queries
        int queryCounter = 0;

        // Process each query
        while (queryCounter < numQueries) {
            // Read the query as a string and tokenize it
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            // Extract the command and value from the query
            String command = tokenizer.nextToken();
            int value = Integer.parseInt(tokenizer.nextToken());

            // Handle different commands using a switch statement
            switch (command) {
                case "get":
                    // Handle the "get" command
                    if (value < frontQueue.size()) {
                        output.append(frontQueue.get(value + frontQueueHead + 1));
                    } else {
                        output.append(backQueue.get(value - frontQueue.size() + backQueueHead + 1));
                    }
                    output.append("\n");
                    break;
                case "push_back":
                    // Add an element to the back queue
                    backQueue.put(backQueueTail++, value);
                    break;
                case "push_front":
                    // Add an element to the front queue
                    frontQueue.put(frontQueueHead--, value);
                    break;
                default:
                    // Add an element to the front queue (default behavior)
                    frontQueue.put(frontQueueTail++, value);
            }

            // Balance the queues if necessary
            if (frontQueue.size() < backQueue.size()) {
                frontQueue.put(frontQueueTail++, backQueue.get(backQueueHead + 1));
                backQueue.remove(++backQueueHead);
            } else if (frontQueue.size() - 1 > backQueue.size()) {
                backQueue.put(backQueueHead--, frontQueue.get(frontQueueTail - 1));
                frontQueue.remove(--frontQueueTail);
            }

            // Increment the query counter
            queryCounter++;
        }

        // Write the output to the console
        writer.write(output.toString().getBytes());

        // Close the input reader and output writer
        reader.close();
        writer.close();
    }
}