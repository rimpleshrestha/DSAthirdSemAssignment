/*question 1 a  */

import java.util.*;

public class ClassroomScheduler {

    // this is a static class to repreasent a scheduled classroom
    static class Class {
        int start;
        int end;

        Class(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int most_Utilized_Classroom(int n, int[][] classes) {
        List<Class> classList = new ArrayList<>();
        for (int[] c : classes) {
            classList.add(new Class(c[0], c[1]));
        }

        //sorting the classes by start time and end time 
        classList.sort((a, b) -> {
            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }
            return Integer.compare(a.end, b.end);
        });

        // Array to track when each room will be free
        int[] roomEndTimes = new int[n]; // End times for each room
        int[] roomCounts = new int[n]; // Count of classes held in each room

        for (Class c : classList) {
            // Finding  the first available room
            int available_Room = -1;
            for (int i = 0; i < n; i++) {
                if (roomEndTimes[i] <= c.start) {
                    available_Room = i; // Room is available
                    break;
                }
            }

            if (available_Room != -1) {
                // Assign the class to the available room
                roomEndTimes[available_Room] = c.end; // Update the end time for this room
                roomCounts[available_Room]++; // Increment count for this room
            } else {
                // here all rooms are busy, find the room that becomes free first 
                int earliestRoom = 0;
                for (int i = 1; i < n; i++) {
                    if (roomEndTimes[i] < roomEndTimes[earliestRoom]) {
                        earliestRoom = i;
                    }
                }
                // Delay the class until the earliest room becomes available
                roomEndTimes[earliestRoom] += (c.end - c.start); // Update end time for the delayed class
                roomCounts[earliestRoom]++; // Increment count for this room
            }
        }

        // Find the classroom with the most classes held
        int maxClasses = 0;
        int maxRoom = -1;

        for (int i = 0; i < n; i++) {
            if (roomCounts[i] > maxClasses) {
                maxClasses = roomCounts[i];
                maxRoom = i; // Update the room with max classes
            }
        }

        return maxRoom; // Return the room with the most classes
    }

    public static void main(String[] args) {
        // Example Input
        int n = 3; // Number of classrooms
        int[][] classes = {
            {1, 20},
            {2, 10},
            {3, 5},
            {4, 9},
            {6, 8}
        };
        
        // now we print the most utilized room
        int result = most_Utilized_Classroom(n, classes);
        System.out.println(result); //  Output: 1
    }
}
