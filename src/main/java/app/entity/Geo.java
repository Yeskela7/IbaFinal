package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Embeddable;

@Data
@Getter
@AllArgsConstructor
@Embeddable
public class Geo {
    private long latitude;
    private long longitude;

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

}
