/**
 * Project       : Millionaire Madness

 * Program Description:
   This program calculates the minimum ladder length required to reach the destination
   on a terrain map with varying heights. It uses a binary search algorithm to find
   the minimum ladder length, considering that the ladder can be placed on cells to
   bridge height differences.

 * Input:
   The input consists of the following:
   - The first line contains two space-separated integers, N and M, where N is the number
     of rows and M is the number of columns in the terrain map.
   - N lines follow, each containing M space-separated integers, representing the heights
     of the cells in the terrain map.

 * Output:
   The program outputs a single integer, which is the minimum ladder length required to
   reach the destination. If it's not possible to reach the destination, the output is 0.

 * Program Implementation:
   1. Read the dimensions of the terrain map and the heights of the cells.
   2. Perform a binary search to find the minimum ladder length needed to reach the destination.
   3. Implement a breadth-first search (BFS) algorithm to check if the destination is reachable
      with the given ladder length, considering height differences.
   4. Output the result, which is the minimum ladder length.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;

public class MillionaireMadness {
    public static void main(String[] args)
            throws IOException {
        // Create a reader to read input from the standard input (keyboard)
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        // Create a writer to write output to the standard output (console)
        PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Read the dimensions of the terrain map (number of rows and columns)
        String[] dimensions = inputReader.readLine().split(" ");
        int numCols = Integer.parseInt(dimensions[1]);
        int numRows = Integer.parseInt(dimensions[0]);

        // Create a 2D array to represent the terrain map with heights
        int[][] terrainMap = new int[numRows][numCols];
        int rowIdx = 0;

        // Read the height values for each cell in the terrain map
        while (rowIdx < numRows) {
            String[] rowValues = inputReader.readLine().split(" ");
            int colIdx = 0;
            while (colIdx < numCols) {
                int height = Integer.parseInt(rowValues[colIdx]);
                terrainMap[rowIdx][colIdx] = height;
                colIdx++;
            }
            rowIdx++;
        }

        // Initialize variables for binary search
        int minLadderLength = 0;
        int maxLadderLength = Integer.MAX_VALUE;
        int resultLadderLength = 0;

        // Binary search for the minimum ladder length needed to reach the destination
        while (minLadderLength <= maxLadderLength) {
            int midLadderLength = minLadderLength + (maxLadderLength - minLadderLength) / 2;
            if (canReachDestination(terrainMap, midLadderLength)) {
                resultLadderLength = midLadderLength;
                maxLadderLength = midLadderLength - 1;
            } else {
                minLadderLength = midLadderLength + 1;
            }
        }

        // Write the result (minimum ladder length) to the output
        outputWriter.write(Integer.toString(resultLadderLength));
        outputWriter.close();
    }

    // Helper function to check if it's possible to reach the destination using a given ladder length
    private static boolean canReachDestination(int[][] terrainMap, int maxLadderLength) {
        int numRows = terrainMap.length;
        int numCols = terrainMap[0].length;
        boolean[][] visited = new boolean[numRows][numCols];

        // Create a queue for breadth-first search
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        // Define possible movement directions: up, down, left, and right
        int[][] moveDirections = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        // Perform a breadth-first search to explore the terrain
        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int currentRow = currentPosition[0];
            int currentCol = currentPosition[1];

            // If the destination is reached, return true
            if (currentRow == numRows - 1 && currentCol == numCols - 1) {
                return true;
            }

            // Check adjacent cells for possible movement
            for (int[] direction : moveDirections) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];

                // Check if the new position is within bounds and hasn't been visited
                if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols && !visited[newRow][newCol]) {
                    // Calculate the height difference between the current cell and the new cell
                    int heightDifference = terrainMap[newRow][newCol] - terrainMap[currentRow][currentCol];
                    // If the height difference is within the ladder length limit, add the new position to the queue
                    if (heightDifference <= maxLadderLength) {
                        queue.add(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }

        // If the destination cannot be reached, return false
        return false;
    }
}