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


    @Column(name = "latitude")
    private long latitude;

    @Column(name = "longitude")
    private long longitude;

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public Geo() {
    }
}
