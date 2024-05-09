/*
 * Project       : Lost Map
 *
 * Program Description:
    This program solves the task of distributing a table and map to villages in a mountainous region.
    The goal is to reconstruct the map based on the distances between villages, given in a table.
    The program uses Prim's algorithm to find the Minimum Spanning Tree, which represents the roads
    connecting villages with the minimum total distance. The output consists of the road connections
    that should be built to efficiently distribute the table and map.

     Input:
     - The first line contains the integer n (2≤n≤2500), the number of villages in the region.
     - next n lines contain n integers each, representing the distance from one village to another.

     Output:
     - For each test case, output n−1 lines with two integers u and v on each line, indicating that
       there is a road connecting villages u and v in this region. Villages are numbered from 1 to n.
     - Any solution that outputs the original set of roads will be accepted.

 * Pseudocode:
    numberOfVillages = readIntegerFromInput()

    distanceMatrix = initializeMatrix(numberOfVillages)

    for i from 0 to numberOfVillages - 1:
        distanceValues = readArrayFromInput()
        for j from 0 to numberOfVillages - 1:
            distanceMatrix[i][j] = distanceValues[j]

    isVisited = initializeArray(numberOfVillages, false)
    minimumDistances = initializeArray(numberOfVillages, INFINITY)
    parentVertices = initializeArray(numberOfVillages, -1)

    minimumDistances[0] = 0

    for k from 0 to numberOfVillages - 2:
        currentVertex = findMinimumDistanceVertex(isVisited, minimumDistances)
        isVisited[currentVertex] = true

        for each unvisited adjacentVertex of currentVertex:
            if distanceMatrix[currentVertex][adjacentVertex] < minimumDistances[adjacentVertex]:
                minimumDistances[adjacentVertex] = distanceMatrix[currentVertex][adjacentVertex]
                parentVertices[adjacentVertex] = currentVertex

    for l from 1 to numberOfVillages - 1:
        parent = parentVertices[l]
        if parent < l:
            writeOutput(parent + 1, l + 1)
        else:
            writeOutput(l + 1, parent + 1)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

// Main class that contains the program logic
public class LostMap {
    // Main method where the execution of the program begins
    public static void main(String[] args)
            throws IOException {
        // Set up input and output streams for reading and writing data
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Read the number of villages from the input
        int numberOfVillages = Integer.parseInt(reader.readLine());

        // Initialize a matrix to store distances between villages
        int[][] distanceMatrix = new int[numberOfVillages][numberOfVillages];

        // Initialize a variable for loop control
        int i = 0;

        // Populate the distance matrix by reading input values
        while (i < numberOfVillages) {
            String[] distanceValues = reader.readLine().split(" ");
            for (int j = 0; j < numberOfVillages; j++) {
                distanceMatrix[i][j] = Integer.parseInt(distanceValues[j]);
            }
            i++;
        }

        // Arrays to keep track of visited vertices, minimum distances, and parent vertices
        boolean[] isVisited = new boolean[numberOfVillages];
        int[] minimumDistances = new int[numberOfVillages];
        int[] parentVertices = new int[numberOfVillages];

        // Initialize minimum distances and set the distance to the starting vertex to 0
        Arrays.fill(minimumDistances, Integer.MAX_VALUE);
        minimumDistances[0] = 0;

        // Variable for loop control
        int k = 0;

        // Perform Prim's algorithm to find minimum spanning tree
        while (k < numberOfVillages - 1) {
            // Find the vertex with the minimum distance value
            int currentVertex = findMinimumDistanceVertex(isVisited, minimumDistances);
            isVisited[currentVertex] = true;

            // Update the minimum distances and parent vertices for adjacent vertices
            int adjacentVertex = 0;
            while (adjacentVertex < numberOfVillages) {
                if (!isVisited[adjacentVertex] && distanceMatrix[currentVertex][adjacentVertex] < minimumDistances[adjacentVertex]) {
                    minimumDistances[adjacentVertex] = distanceMatrix[currentVertex][adjacentVertex];
                    parentVertices[adjacentVertex] = currentVertex;
                }
                adjacentVertex++;
            }
            k++;
        }

        // Variable for loop control
        int l = 1;

        // Output the edges of the minimum spanning tree
        while (l < numberOfVillages) {
            int parent = parentVertices[l];
            // Ensure the output is in ascending order of vertices
            if (parent < l) {
                writer.write(String.format("%d %d\n", parent + 1, l + 1));
            } else {
                writer.write(String.format("%d %d\n", l + 1, parent + 1));
            }
            l++;
        }

        // Close the PrintWriter to flush and release system resources
        writer.close();
    }

    // Method to find the vertex with the minimum distance value
    private static int findMinimumDistanceVertex(boolean[] isVisited, int[] minimumDistances) {
        int minimum = Integer.MAX_VALUE;
        int minimumVertex = -1;

        // Iterate through vertices to find the minimum distance
        for (int i = 0; i < minimumDistances.length; i++) {
            if (!isVisited[i] && minimumDistances[i] < minimum) {
                minimum = minimumDistances[i];
                minimumVertex = i;
            }
        }

        // Return the vertex with the minimum distance
        return minimumVertex;
    }
}