package app.dto.req;

import app.entity.Geo;
import app.entity.Person;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class EventReq {
    private long id;
    private String title;
    private Person author;
    private String description;
    private String place;
    private Geo location;
    private String date;
    private String time;
    private long category;
    private boolean isJoined;

    public EventReq(long id, String title, Person author, String description, String place, Geo location, String date, String time, long category, boolean isJoined) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.place = place;
        this.location = location;
        this.date = date;
        this.time = time;
        this.category = category;
        this.isJoined = isJoined;
    }

    public long getCategory() {
        return category;
    }

    public long getId() {
        return id;
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
