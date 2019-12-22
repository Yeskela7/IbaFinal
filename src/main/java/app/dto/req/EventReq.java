package app.dto.req;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class EventReq {
    private String title;
    private long creatorId;
    private String description;
    private long latitude;
    private long longitude;
    private String place;
    private long time;

    public EventReq(String title, long creatorId, String description, long latitude, long longitude, String place, long time) {
        this.title = title;
        this.creatorId = creatorId;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.place = place;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public String getDescription() {
        return description;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }
}
