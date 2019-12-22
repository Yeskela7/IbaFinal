package app.dto.req;

import app.entity.Geo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class EventReq {
    private String title;
    private long creatorId;
    private String description;
    private String place;
    private Geo location;
    private String date;
    private String time;

    public String getTitle() {
        return title;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public Geo getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public EventReq(String title, long creatorId, String description, String place, Geo location, String date, String time) {
        this.title = title;
        this.creatorId = creatorId;
        this.description = description;
        this.place = place;
        this.location = location;
        this.date = date;
        this.time = time;
    }
}
