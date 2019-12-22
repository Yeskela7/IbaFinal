package app.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventResp {
    String title;
    long creatorId;
    String description;
    long latitude;
    long longitude;
    String place;
    long time;
}
