import java.util.*;

public class TravelingSalesman {

    private static class City {
        int x, y;

        City(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double distanceTo(City city) {
            int dx = this.x - city.x;
            int dy = this.y - city.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the number of cities:");
            int numCities = scanner.nextInt();

            City[] cities = new City[numCities];
            System.out.println("Enter the coordinates of the cities (x y):");
            for (int i = 0; i < numCities; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                cities[i] = new City(x, y);
            }

            List<Integer> tour = nearestNeighbor(cities);
            double tourDistance = calculateTotalDistance(cities, tour);

            System.out.println("Optimal Tour:");
            for (int cityIndex : tour) {
                System.out.print(cityIndex + " ");
            }
            System.out.println("\nTotal Distance: " + tourDistance);
        } finally {
            scanner.close(); // Ensure the scanner is closed
        }
    }

    private static List<Integer> nearestNeighbor(City[] cities) {
        List<Integer> tour = new ArrayList<>();
        boolean[] visited = new boolean[cities.length];
        tour.add(0);
        visited[0] = true;

        for (int i = 1; i < cities.length; i++) {
            int lastCity = tour.get(tour.size() - 1);
            int nextCity = -1;
            double shortestDistance = Double.MAX_VALUE;

            for (int j = 0; j < cities.length; j++) {
                if (!visited[j] && cities[lastCity].distanceTo(cities[j]) < shortestDistance) {
                    nextCity = j;
                    shortestDistance = cities[lastCity].distanceTo(cities[j]);
                }
            }

            tour.add(nextCity);
            visited[nextCity] = true;
        }

        return tour;
    }

    private static double calculateTotalDistance(City[] cities, List<Integer> tour) {
        double totalDistance = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            totalDistance += cities[tour.get(i)].distanceTo(cities[tour.get(i + 1)]);
        }
        totalDistance += cities[tour.get(tour.size() - 1)].distanceTo(cities[tour.get(0)]); // Return to start
        return totalDistance;
    }
}
 

