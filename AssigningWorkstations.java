/*
 * Project       : Assigning Workstations
 *
 * Program Description:
     This Java program efficiently schedules workstations to maximize their usage while ensuring that the time gap
     between workstations does not exceed a specified maximum inactive time. It reads input data about the workstations'
     start and end times and processes the scheduling algorithm to determine the number of workstations that can be saved
     from being idle for too long.

     The program utilizes a PriorityQueue and a Queue to manage workstations and their end times efficiently. It also
     defines a custom class Workstation to represent workstations and ensure proper sorting based on start and end times.

     * Input: The program expects input from the standard input (keyboard) in the following format:
       - The first line contains two integers: the number of workstations (N) and the maximum inactive time (M).
       - The next N lines contain two integers each, representing the start time and the duration of each workstation's task.

     * Output: The program outputs a single integer representing the number of workstations that can be saved from excessive
       idleness according to the given scheduling constraints.

 * Pseudocode:
    1. Initialize numWorkstations, maxInactiveTime, and workstationQueue
    2. Read numWorkstations and maxInactiveTime from input
    3. Create a PriorityQueue (workstationQueue) to store workstations
    4. Initialize workstationIndex to 0
    5. While workstationIndex is less than numWorkstations, repeat the following steps:
        a. Read start time and end time from input
        b. Compute the end time as start time + end time
        c. Add a new Workstation to workstationQueue with the start and end times
        d. Increment workstationIndex
    6. Initialize numSaved to 0
    7. Create a Queue (endTimesQueue) to store end times of workstations
    8. Add the end time of the first workstation from workstationQueue to endTimesQueue
    9. While workstationQueue is not empty, repeat the following steps:
        a. Dequeue the currentWorkstation from workstationQueue
        b. Add the end time of the currentWorkstation to endTimesQueue
        c. If the start time of the currentWorkstation is less than the earliest end time in endTimesQueue, continue to the next iteration
        d. Iterate through endTimesQueue, comparing time gaps:
            i. Dequeue an end time from endTimesQueue
            ii. Calculate the time gap as the start time of the currentWorkstation minus the dequeued end time
            iii. If the time gap is greater than maxInactiveTime, continue to the next iteration
            iv. If the time gap is less than 0, enqueue the end time back into endTimesQueue and exit the loop
            v. Otherwise, increment numSaved and exit the loop
    10. Output the value of numSaved
    11. Close inputReader and output streams
 */


import java.io.*;
import java.util.*;

public class AssigningWorkstations {
    public static void main(String[] args)
            throws IOException {
        // Create a BufferedReader to read input from the standard input (keyboard).
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        // Create a BufferedOutputStream to write output to the standard output (console).
        BufferedOutputStream output = new BufferedOutputStream(System.out);

        // Create a StringTokenizer to parse the first line of input.
        StringTokenizer tokenizer = new StringTokenizer(inputReader.readLine());

        // Parse the first token as the number of workstations.
        int numWorkstations = Integer.parseInt(tokenizer.nextToken());

        // Parse the second token as the maximum inactive time.
        final int maxInactiveTime = Integer.parseInt(tokenizer.nextToken());

        // Create a PriorityQueue to store workstations based on their start times.
        PriorityQueue<Workstation> workstationQueue = new PriorityQueue<>();

        // Initialize an index to keep track of the number of workstations added.
        int workstationIndex = 0;

        // Read and parse input lines to populate the workstationQueue.
        while (workstationIndex < numWorkstations) {
            tokenizer = new StringTokenizer(inputReader.readLine());

            // Parse the start time and compute the end time.
            int startTime = Integer.parseInt(tokenizer.nextToken());
            int endTime = startTime + Integer.parseInt(tokenizer.nextToken());

            // Add the workstation to the PriorityQueue.
            workstationQueue.add(new Workstation(startTime, endTime));

            workstationIndex++;
        }

        // Initialize a counter for the number of workstations saved.
        int numSaved = 0;

        // Create a Queue to store end times of workstations.
        Queue<Integer> endTimesQueue = new PriorityQueue<>();

        // Add the end time of the first workstation to the endTimesQueue.
        endTimesQueue.add(workstationQueue.poll().endTime);

        // Process the workstations to find the saved ones.
        while (!workstationQueue.isEmpty()) {
            Workstation currentWorkstation = workstationQueue.poll();

            // Add the end time of the current workstation to the endTimesQueue.
            endTimesQueue.add(currentWorkstation.endTime);

            // Check if the current workstation's start time is less than the earliest end time in the queue.
            if (currentWorkstation.startTime < endTimesQueue.peek()) {
                continue; // If it is, skip this workstation.
            }

            // Iterate through the endTimesQueue and compare time gaps.
            for (workstationIndex = 0; !endTimesQueue.isEmpty(); workstationIndex++) {
                int endTime = endTimesQueue.poll();
                int timeGap = currentWorkstation.startTime - endTime;

                // If the time gap is greater than the maxInactiveTime, continue to the next iteration.
                if (timeGap > maxInactiveTime) {
                    continue;
                } else if (timeGap < 0) {
                    // If the time gap is less than 0, add the end time back to the queue.
                    endTimesQueue.add(endTime);
                    break; // Exit the loop.
                } else {
                    // If none of the above conditions are met, increment the numSaved counter.
                    numSaved++;
                    break; // Exit the loop.
                }
            }
        }

        // Write the number of saved workstations to the output stream.
        output.write(Integer.toString(numSaved).getBytes());

        // Close the input and output streams.
        inputReader.close();
        output.close();
    }
}

// Define a custom class Workstation that implements the Comparable interface.
class Workstation implements Comparable<Workstation> {
    int startTime;
    int endTime;

    Workstation(int start, int end) {
        startTime = start;
        endTime = end;
    }

    @Override
    public int compareTo(Workstation other) {
        if (startTime == other.startTime) {
            return Integer.compare(endTime, other.endTime);
        }
        return Integer.compare(startTime, other.startTime);
    }
}

