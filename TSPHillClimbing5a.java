/* question 5
a) Implement travelling a salesman problem using hill climbing algorithm.*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPHillClimbing5a {

    // Class to represent a location with cooridnates a and b
    static class Location{
        int a, b;//location coordinates 

        //constructor to initialise coordinates
        Location(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    // Calculate Euclidean distance between two location
    private static double calculateDistance(Location city1, Location city2) {
        //using the distance formula((a2-a1)+(b2-b1)^2)
        return Math.sqrt(Math.pow(city1.a - city2.b, 2) + Math.pow(city1.b - city2.b, 2));
    }

    // Calculate the total distance of a route
    private static double calculateTotalDistance(List<Location> route) {
        double totalDistance = 0.0;//initialising total distance 
        for (int i = 0; i < route.size(); i++) {
            totalDistance += calculateDistance(route.get(i), route.get((i + 1) % route.size()));
        }
        return totalDistance;//return total distance of the route
    }

    // Hill Climbing algorithm for TSP
    public static List<Location> hillClimbing(List<Location> cities) {
        // Start with a random route
        List<Location> currentRoute = new ArrayList<>(cities);
        Collections.shuffle(currentRoute);//shuffling to create a random starting route

        double currentDistance = calculateTotalDistance(currentRoute); //calculating the distance of the current route
        boolean improvement = true;

        while (improvement) {
            improvement = false;
            // Generate neighboring tours by swapping two cities
            for (int i = 0; i < currentRoute.size(); i++) {
                for (int j = i + 1; j < currentRoute.size(); j++) {
                    // Create a new neighbor by swapping
                    List<Location> neighborRoute = new ArrayList<>(currentRoute);
                    Collections.swap(neighborRoute, i, j);
                    double neighborDistance = calculateTotalDistance(neighborRoute);

                    // If neighbor is better, update current tour
                    if (neighborDistance < currentDistance) {
                        currentRoute = neighborRoute;
                        currentDistance = neighborDistance;
                        improvement = true;
                    }
                }
            }
        }
        return currentRoute;// Return the best route found

    }

    public static void main(String[] args) {
        // Example cities (x, y coordinates)
        List<Location> cities = new ArrayList<>();
        cities.add(new Location(0, 0));
        cities.add(new Location(1, 2));
        cities.add(new Location(2, 4));
        cities.add(new Location(3, 1));
        cities.add(new Location(5, 5));

        // Solve TSP using Hill Climbing
        List<Location> bestRoute = hillClimbing(cities);
        double bestDistance = calculateTotalDistance(bestRoute);

        // Output the best tour and its distance
        System.out.println("Best Route:");
        for (Location city : bestRoute) {
            System.out.println("City at (" + city.a + ", " + city.b + ")");
        }
        System.out.println("Best distance: " + bestDistance);
    }
}
