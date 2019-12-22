package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Person.class)
    @JoinTable(name = "c_person_event",
            joinColumns = {
                    @JoinColumn(name = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")}
    )
    private Collection<Person> person = new HashSet<>();

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
        System.out.println(p);
        person.add(p);
        System.out.println(person);
    }

    public Event(String title, long creatorId, String description, Geo geo, String place, long time) {
        this.title = title;
        this.creatorId = creatorId;
        this.description = description;
        this.geo = geo;
        this.place = place;
        this.time = time;
    }

            @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Tag.class)
            @JoinTable(name = "c_tag_event",
            joinColumns = {
                    @JoinColumn(name = "e_id", referencedColumnName = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "t_id", referencedColumnName = "tag_id")}
    )
    private Collection<Tag>tags = new HashSet<>();

    public void addTagsToEvent(Collection<? extends Tag> t){
        tags.addAll(t);
    }

    @JsonManagedReference
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

    public Event(String title, long creatorId, String description, Geo geo, String place, long time, Collection<Person> person, Collection<Tag> tags, Collection<Comment> comments) {
        this.title = title;
        this.creatorId = creatorId;
        this.description = description;
        this.geo = geo;
        this.place = place;
        this.time = time;
        this.person = StreamSupport.stream(person.spliterator(), false).collect(Collectors.toSet());
        this.tags = StreamSupport.stream(tags.spliterator(), false).collect(Collectors.toSet());
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

    public Geo getGeo() {
        return geo;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public Integer getPersonCap() {
        return Optional.of(person.size()).orElse(null);
    }
}
