/*
 * Project       : Human Cannonball Run
 *
 * Program Description:
    This Java program calculates the shortest distance between two points in a 2D space using the
    Floyd-Warshall algorithm. The input consists of coordinates for the start point, end point, and
    a variable number of intermediate points. The distances between points are adjusted based on
    their positions in the sequence.

   Input:
    - The program reads input from the console:
    - Two lines containing x and y coordinates for the start point.
    - Two lines containing x and y coordinates for the end point.
    - One line containing an integer 'numberOfIntermediatePoints'.
    - 'numberOfIntermediatePoints' lines containing x and y coordinates for each intermediate point.

   Output:
    - The program outputs a single line to the console:
    - The shortest distance between the start and end points, formatted with 6 decimal places.

 * Pseudocode:
    1. Initialize an empty list 'coordinatePairs' to store CoordinatePair objects.
    2. Read and store the start coordinates into 'coordinatePairs'.
    3. Read and store the end coordinates into 'coordinatePairs'.
    4. Read the number of intermediate points 'numberOfIntermediatePoints'.
    5. Loop 'i' from 0 to 'numberOfIntermediatePoints - 1':
       a. Read and store intermediate coordinates into 'coordinatePairs'.
    6. Calculate the size of the adjacency matrix ('size') as 'numberOfIntermediatePoints + 2'.
    7. Initialize a 2D array 'adjacencyMatrix' with dimensions 'size x size'.
    8. Loop 'x' from 0 to 'size - 1':
       a. Loop 'y' from 'x + 1' to 'size - 1':
          i. Calculate the Euclidean distance between coordinatePairs[x] and coordinatePairs[y].
          ii. If 'x' or 'y' is 0, set adjacencyMatrix[x][y] to 'distance / 5', and make it symmetric.
          iii. Otherwise, set adjacencyMatrix[x][y] to '((|distance - 50|) / 5) + 2', and make it symmetric.
    9. Apply the Floyd-Warshall algorithm to find shortest distances between all pairs of points:
       a. Initialize 'matrixSize' as the length of adjacencyMatrix.
       b. Initialize a 2D array 'shortestDistances' and copy the values from 'adjacencyMatrix'.
       c. Loop 'k' from 0 to 'matrixSize - 1':
          i. Loop 'i' from 0 to 'matrixSize - 1':
             - Loop 'j' from 0 to 'matrixSize - 1':
                * Update shortestDistances[i][j] as 'min(shortestDistances[i][j], shortestDistances[i][k] + shortestDistances[k][j])'.
    10. Format the result as a string with 6 decimal places ('formattedResult').
    11. Write 'formattedResult' to the output.
    12. Handle IOException by printing the stack trace if an exception occurs during input/output.
    13. Define a CoordinatePair class:
        a. Store x and y coordinates.
        b. Provide a constructor to initialize the coordinates.
        c. Provide getter methods for x and y.
    14. End of the program.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// Main class that contains the program logic
public class HumanCannonballRun {
    // Main method where the program execution starts
    public static void main(String[] args) {
        // Use try-with-resources to automatically close resources (buffers) after use
        try (
                // Set up buffered reader to read input from the console
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                // Set up buffered writer to write output to the console
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            // Create an ArrayList to store CoordinatePair objects
            ArrayList<CoordinatePair> coordinatePairs = new ArrayList<>();

            // Read and store start and end coordinates
            coordinatePairs.add(readCoordinates(reader));
            coordinatePairs.add(readCoordinates(reader));

            // Read the number of intermediate points
            int numberOfIntermediatePoints = Integer.parseInt(reader.readLine());

            // Read and store intermediate coordinates
            int i = 0;
            while (i < numberOfIntermediatePoints) {
                coordinatePairs.add(readCoordinates(reader));
                i++;
            }

            // Calculate the size of the adjacency matrix
            int size = numberOfIntermediatePoints + 2;
            // Create a 2D array to represent the adjacency matrix
            double[][] adjacencyMatrix = new double[size][size];

            // Populate the adjacency matrix based on distance calculations
            for (int x = 0; x < size; x++) {
                for (int y = x + 1; y < size; y++) {
                    double distance = calculateDistance(coordinatePairs.get(x), coordinatePairs.get(y));

                    // Adjust distances based on whether the points are start/end or intermediate
                    if (x == 0 || y == 0) {
                        adjacencyMatrix[x][y] = distance / 5;
                        adjacencyMatrix[y][x] = adjacencyMatrix[x][y];
                    } else {
                        adjacencyMatrix[x][y] = (Math.abs(distance - 50) / 5) + 2;
                        adjacencyMatrix[y][x] = adjacencyMatrix[x][y];
                    }
                }
            }

            // Apply the Floyd-Warshall algorithm to find shortest distances between all pairs of points
            double[][] shortestDistances = applyFloydWarshall(adjacencyMatrix);
            // Format and write the result to the console
            String formattedResult = String.format("%.6f", shortestDistances[0][1]);
            writer.write(formattedResult);

        } catch (IOException e) {
            // Print the exception trace if an IOException occurs
            e.printStackTrace();
        }
    }

    // Method to read and parse coordinates from the console
    static CoordinatePair readCoordinates(BufferedReader reader) throws IOException {
        // Read a line of input and split it into x and y coordinates
        String[] coordinates = reader.readLine().split(" ");
        // Parse the coordinates to doubles and create a CoordinatePair object
        double x = Double.parseDouble(coordinates[0]);
        double y = Double.parseDouble(coordinates[1]);
        return new CoordinatePair(x, y);
    }

    // Method to calculate the Euclidean distance between two CoordinatePair objects
    static double calculateDistance(CoordinatePair p1, CoordinatePair p2) {
        return Math.hypot(p1.x() - p2.x(), p1.y() - p2.y());
    }

    // Method to apply the Floyd-Warshall algorithm on an adjacency matrix
    static double[][] applyFloydWarshall(double[][] adjacencyMatrix) {
        int matrixSize = adjacencyMatrix.length;
        double[][] shortestDistances = new double[matrixSize][matrixSize];

        // Copy the adjacency matrix to initialize the shortest distances matrix
        for (int i = 0; i < matrixSize; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, shortestDistances[i], 0, matrixSize);
        }

        int k = 0;
        // Perform iterations of the Floyd-Warshall algorithm
        do {
            int i = 0;
            do {
                int j = 0;
                do {
                    // Update the shortest distance matrix
                    shortestDistances[i][j] = Math.min(shortestDistances[i][j], shortestDistances[i][k] + shortestDistances[k][j]);
                    j++;
                } while (j < matrixSize);
                i++;
            } while (i < matrixSize);
            k++;
        } while (k < matrixSize);

        return shortestDistances;
    }
}

// Class to represent a pair of coordinates
class CoordinatePair {
    private final double x;
    private final double y;

    // Constructor to initialize the coordinates
    public CoordinatePair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getter method for the x coordinate
    public double x() {
        return x;
    }

    // Getter method for the y coordinate
    public double y() {
        return y;
    }
}