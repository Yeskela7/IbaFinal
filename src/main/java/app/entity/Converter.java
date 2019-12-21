package app.entity;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicLine;
import net.sf.geographiclib.GeodesicMask;

public class Converter {

    private static Geodesic geod = Geodesic.WGS84;// This matches EPSG4326, which is the coordinate system used by Geolake

    /**
     * Get the distance between two points in meters.
     * @param lat1 First point's latitude
     * @param lon1 First point's longitude
     * @param lat2 Second point's latitude
     * @param lon2 Second point's longitude
     * @return Distance between the first and the second point in meters
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        GeodesicLine line = geod.InverseLine(lat1, lon1, lat2, lon2, GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
        return line.Distance();
    }
}
