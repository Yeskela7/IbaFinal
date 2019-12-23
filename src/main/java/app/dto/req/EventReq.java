package app.dto.req;

import app.entity.Geo;
import app.entity.Person;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class EventReq {
    private String title;
    private Person author;
    private String description;
    private String place;
    private Geo location;
    private String date;
    private String time;
    private boolean isJoined;

    public EventReq(String title, Person author, String description, String place, Geo location, String date, String time, boolean isJoined) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.place = place;
        this.location = location;
        this.date = date;
        this.time = time;
        this.isJoined = isJoined;
    }

    public String getTitle() {
        return title;
    }

    public Person getAuthor() {
        return author;
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

    public boolean isJoined() {
        return isJoined;
    }
}
