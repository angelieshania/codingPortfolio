/*
 * Project       : Best Relay Team
 *
 * This Java program reads data about a group of runners, including their names, starting times,
 * and last leg times, and determines the optimal team of four runners for a relay race. The team
 * is selected to minimize the total time required to complete the race based on the last leg times
 * of the runners. The program sorts the runners by their last leg times, evaluates various teams
 * by choosing different starting runners, and outputs the optimal team's total time and member names.
 *
 * Input: Number of runners and data for each runner (name, starting time, last leg time).
 * Output: Minimum total time and the names of the selected team members.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// The main class containing the program's entry point.
class BestRelayTeam {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the standard input (keyboard).
        Scanner input = new Scanner(System.in);

        // Read the number of runners from the input.
        int numberOfRunners = input.nextInt();

        // Create an array to store runner objects.
        Runner[] runners = new Runner[numberOfRunners];

        // Loop to read information about each runner and store it in the array.
        for (int i = 0; i < numberOfRunners; i++) {
            // Read the name, starting time, and last leg time of the runner.
            String name = input.next();
            double startingTime = input.nextDouble();
            double lastLegTime = input.nextDouble();

            // Create a Runner object and store it in the array.
            runners[i] = new Runner(name, startingTime, lastLegTime);
        }

        // Sort the array of runners based on their last leg time.
        Arrays.sort(runners);

        // Initialize variables to track the minimum total time and the best team's indices.
        double minimumTotalTime = Double.MAX_VALUE;
        ArrayList<Integer> bestTeamIndices = new ArrayList<>();

        // Loop through each runner as the first leg runner.
        for (int i = 0; i < numberOfRunners; i++) {
            // Initialize the total time with the starting time of the current runner.
            double currentTotalTime = runners[i].startingTime;

            // Create a list to represent the current team's indices and add the current runner to it.
            ArrayList<Integer> currentTeamIndices = new ArrayList<>();
            currentTeamIndices.add(i);

            // Add additional runners to the current team until it has 4 members.
            for (int j = 0; currentTeamIndices.size() < 4; j++) {
                // Skip adding the same runner to the team.
                if (j == i) {
                    continue;
                }

                // Add the next runner's index to the current team and update the total time.
                currentTeamIndices.add(j);
                currentTotalTime += runners[j].lastLegTime;
            }

            // If the current team's total time is better (smaller) than the minimum time,
            // update the minimum time and store the current team's indices as the best team's indices.
            if (currentTotalTime < minimumTotalTime - 1e-9) {
                minimumTotalTime = currentTotalTime;
                bestTeamIndices = new ArrayList<>(currentTeamIndices);
            }
        }

        // Output the minimum total time with 2 decimal places.
        System.out.printf("%.2f\n", minimumTotalTime);

        // Output the names of the runners in the best team based on their indices.
        for (int i = 0; i < 4; i++) {
            System.out.println(runners[bestTeamIndices.get(i)].name);
        }
    }
}

// A class representing a runner and implementing Comparable for sorting.
class Runner implements Comparable<Runner> {
    // Runner's name, starting time, and last leg time.
    public String name;
    public double startingTime;
    public double lastLegTime;

    // Constructor to initialize a Runner object.
    public Runner(String name, double startingTime, double lastLegTime) {
        this.name = name;
        this.startingTime = startingTime;
        this.lastLegTime = lastLegTime;
    }

    // Implementation of the compareTo method to compare runners based on their last leg time.
    public int compareTo(Runner other) {
        // Compare the last leg time with a small epsilon tolerance for precision.
        if (this.lastLegTime > other.lastLegTime + 1e-9) {
            return 1; // This runner is slower.
        }
        if (this.lastLegTime < other.lastLegTime - 1e-9) {
            return -1; // This runner is faster.
        }
        return 0; // Both runners have nearly equal last leg times.
    }
}