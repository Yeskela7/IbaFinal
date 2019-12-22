package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Getter
@AllArgsConstructor
@Embeddable
public class Geo {

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;


    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Geo() {
    }
}
