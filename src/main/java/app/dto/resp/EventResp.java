package app.dto.resp;

import app.entity.Geo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventResp {
    private String title;
    private long creatorId;
    private String description;
    private String place;
    private Geo location;
    private long time;
}
