import java.util.*;

public class BusService {
    // This code makes the passengers get on the bus in a better way
    public List<Integer> optimizeBoarding(List<Integer> passengers, int k) {
        // Make a new list to put the fixed order of passengers
        List<Integer> newOrder = new ArrayList<>();

        // Look at the passengers in groups of k
        for (int i = 0; i < passengers.size(); i += k) {
            // Find the last person in this group
            int lastInGroup = Math.min(i + k, passengers.size());

            // Copy the group of passengers
            List<Integer> group = new ArrayList<>(passengers.subList(i, lastInGroup));

            // If the group has k people, flip the order
            if (group.size() == k) {
                Collections.reverse(group);
            }

            // Add the group to the new order
            newOrder.addAll(group);
        }

        // Return the new order
        return newOrder;
    }

    public static void main(String[] args) {
        BusService bus = new BusService();

        // Try it with 5 people and groups of 3
        List<Integer> people1 = Arrays.asList(1, 2, 3, 4, 5);
        int groupSize1 = 3;
        List<Integer> newOrder1 = bus.optimizeBoarding(people1, groupSize1);
        System.out.println("New order: " + newOrder1); // Output: [3, 2, 1, 4, 5]

        // Try it with 5 people and groups of 2
        List<Integer> people2 = Arrays.asList(1, 2, 3, 4, 5);
        int groupSize2 = 2;
        List<Integer> newOrder2 = bus.optimizeBoarding(people2, groupSize2);
        System.out.println("New order: " + newOrder2); // Output: [2, 1, 4, 3, 5]
    }
}
