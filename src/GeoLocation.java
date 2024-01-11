import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GeoLocation {
    private double lat;
    private double lon;
    private static int numLocations = 0;

    public GeoLocation() {
        Random random = new Random();
        this.lat = Math.floor((random.nextDouble() * 180 - 90)*1000000.0) / 1000000.0;
        this.lon = Math.floor((random.nextDouble() * 180 - 90)*1000000.0) / 1000000.0;
        numLocations++;
    }
    public GeoLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        numLocations++;
    }

    public GeoLocation(GeoLocation location) {
        Random random = new Random();
        this.lat = Math.floor((location.lat + random.nextDouble() * 0.2 - 0.1)*1000000.0) / 1000000.0;
        this.lon = Math.floor((location.lon + random.nextDouble() * 0.2 - 0.1)*1000000.0) / 1000000.0;
        numLocations++;
    }

    public void print() {
        System.out.println("[" + lat + ", " + lon + "]");
    }

    //haversino formule pasiskolinta is interneto :)
    public static double distance(GeoLocation location1, GeoLocation location2) {
        double lat1 = location1.lat;
        double lat2 = location2.lat;
        double lon1 = location1.lon;
        double lon2 = location2.lon;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return Math.floor(rad * c * 10.0) / 10.0;
    }

    public static int getNumLocations() {
        return numLocations;
    }

    public static void main(String[] args) {

        GeoLocation someLocation = new GeoLocation();
        GeoLocation vilnius = new GeoLocation(54.683333, 25.283333);
        GeoLocation kaunas = new GeoLocation(54.897222, 23.886111);
        GeoLocation nearVilnius = new GeoLocation(vilnius);

        someLocation.print();
        vilnius.print();
        nearVilnius.print();

        System.out.println("Number of locations: " + GeoLocation.getNumLocations());
        System.out.println("From Vilnius to Kaunas: " + GeoLocation.distance(vilnius, kaunas));
        System.out.println("From Vilnius to random location: " + GeoLocation.distance(vilnius, someLocation));
        System.out.println("From Vilnius to random neighbour: " + GeoLocation.distance(vilnius, nearVilnius));
    }
}