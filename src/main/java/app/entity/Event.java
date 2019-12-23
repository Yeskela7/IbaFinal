package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    private Geo location;

//    @Column(name = "latitude")
//    private long latitude;
//
//    @Column(name = "longitude")
//    private long longitude;

    @Column(name = "place")
    private String place;

    @Column(name = "time")
    private String time;

    @Column(name = "date")
    private String date;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Person.class)
    @JoinTable(name = "c_person_event",
            joinColumns = {
                    @JoinColumn(name = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")}
    )
    private Collection<Person> guests = new HashSet<>();

    public Collection<Person> getGuests() {
        return guests;
    }

    //    @JsonManagedReference
//    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Person.class)
//    @JoinTable(name = "c_person_event",
//            joinColumns = {
//                    @JoinColumn(name = "e_id", referencedColumnName = "event_id")},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "p_id", referencedColumnName = "user_id")}
//    )
//    private Collection<Person> person = new HashSet<>();

    public void addNewPersonToEvent(Person p){
        guests.add(p);
    }

    public void deletePersonFromEvent(Person p){
        guests.remove(p);
    }

    public Event(String title, long creatorId, String description, Geo location, String place, String time, String date) {
        this.title = title;
        this.creatorId = creatorId;
        this.description = description;
        this.location = location;
        this.place = place;
        this.time = time;
        this.date = date;
    }


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Tag.class)
            @JoinTable(name = "c_tag_event",
            joinColumns = {
                    @JoinColumn(name = "e_id", referencedColumnName = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "t_id", referencedColumnName = "tag_id")}
    )
    private Collection<Tag> category = new HashSet<>();

    public void addTagsToEvent(Collection<? extends Tag> t){
        category.addAll(t);
    }


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Comment.class)
    @JoinTable(name = "c_comment_event",
            joinColumns = {
                    @JoinColumn(name = "e_id", referencedColumnName = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "c_id", referencedColumnName = "comment_id")}
    )
    private Collection<Comment> comments = new HashSet<>();

    public void addCommentToEvent(Collection<? extends Comment> c){
        comments.addAll(c);
    }

    public Event(String title, long creatorId, String description, Geo location, String place, String time, String date, Collection<Person> person, Collection<Tag> category, Collection<Comment> comments) {
        this.title = title;
        this.creatorId = creatorId;
        this.description = description;
        this.location = location;
        this.place = place;
        this.time = time;
        this.date = date;
        this.guests = StreamSupport.stream(person.spliterator(), false).collect(Collectors.toSet());
        this.category = StreamSupport.stream(category.spliterator(), false).collect(Collectors.toSet());
        this.comments = StreamSupport.stream(comments.spliterator(), false).collect(Collectors.toSet());
    }

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

    public Geo getLocation() {
        return location;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public Integer getPersonCap() {
        return Optional.of(guests.size()).orElse(null);
    }
}
