package app.entity;

import app.security.jwt.JwtTokenServiceImpl;
import app.security.userdetails.MyUserDetailsService;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicLine;
import net.sf.geographiclib.GeodesicMask;

public class Converter {
    private final MyUserDetailsService uds;
    private final JwtTokenServiceImpl jwt;

    private static Geodesic geod = Geodesic.WGS84;// This matches EPSG4326, which is the coordinate system used by Geolake

    public Converter(MyUserDetailsService uds, JwtTokenServiceImpl jwt) {
        this.uds = uds;
        this.jwt = jwt;
    }

    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        GeodesicLine line = geod.InverseLine(lat1, lon1, lat2, lon2, GeodesicMask.DISTANCE_IN | GeodesicMask.LATITUDE | GeodesicMask.LONGITUDE);
        return line.Distance();
    }


}
