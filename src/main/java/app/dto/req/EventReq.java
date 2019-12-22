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
    private Geo geo;
    private long time;

//    public EventReq(String title, long creatorId, String description, String place, Geo geo, long time) {
//        this.title = title;
//        this.creatorId = creatorId;
//        this.description = description;
//        this.place = place;
//        this.geo = geo;
//        this.time = time;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public long getCreatorId() {
//        return creatorId;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Geo getGeo() {
//        return geo;
//    }
//
//    public String getPlace() {
//        return place;
//    }
//
//    public long getTime() {
//        return time;
//    }
}
