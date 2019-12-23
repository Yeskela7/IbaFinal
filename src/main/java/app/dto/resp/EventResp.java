package app.dto.resp;

import app.entity.Geo;
import app.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventResp {
    private String title;
    private Person author;
    private String description;
    private String place;
    private Geo location;
    private String date;
    private String time;
    private boolean isJoined;
}
