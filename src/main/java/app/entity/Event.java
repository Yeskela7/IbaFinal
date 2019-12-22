package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Getter
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "event_id")
    private long id;

    @Column(unique = true, name = "title")
    private String title;

    @Column(name = "creatorId")
    private long creatorId;

    @Column(name = "description")
    private String description;

    @Embedded
    private Geo geo;

//    @Column(name = "latitude")
//    private long latitude;
//
//    @Column(name = "longitude")
//    private long longitude;

    @Column(name = "place")
    private String place;

    @Column(name = "time")
    private long time;

    @ManyToMany
    @JoinTable(name = "c_person_event",
            joinColumns = {
                    @JoinColumn(name = "e_id", referencedColumnName = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "p_id", referencedColumnName = "user_id")}
    )
    private Set<Person> person = new HashSet<>();

    public Event(String title, long creatorId, String description, Geo geo, String place, long time) {
        this.title = title;
        this.creatorId = creatorId;
        this.description = description;
        this.geo = geo;
        this.place = place;
        this.time = time;
    }

    @ManyToMany
    @JoinTable(name = "c_tag_event",
            joinColumns = {
                    @JoinColumn(name = "e_id", referencedColumnName = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "t_id", referencedColumnName = "tag_id")}
    )
    private Set<Tag>tags = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "c_comment_event",
            joinColumns = {
                    @JoinColumn(name = "e_id", referencedColumnName = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "c_id", referencedColumnName = "comment_id")}
    )
    private Set<Comment> comments = new HashSet<>();

    public long getId() {
        return id;
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

    public Geo getGeo() {
        return geo;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public Integer getPerson() {
        return Optional.of(person.size()).orElse(null);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Set<Comment> getComments() {
        return comments;
    }
}
