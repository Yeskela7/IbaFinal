package app.entity;

public class NearBy {
    private static double earthRadius = 6371;

    public static double distance(double user_lon, double user_lat, double event_lon, double event_lat) {
        double distance = Math.acos(Math.sin(event_lat * Math.PI / 180.0) * Math.sin(user_lat * Math.PI / 180.0) +
                Math.cos(event_lat * Math.PI / 180.0) * Math.cos(user_lat * Math.PI / 180.0) *
                        Math.cos((user_lon - event_lon) * Math.PI / 180.0)) * earthRadius;
        return distance;
    }


}
