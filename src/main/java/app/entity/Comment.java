package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "comment_id")
    private long id;

    @Column(name = "userId")
    private long user_id;

    @Column(name = "title")
    private String title;


    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="comments")
    private Collection<Event> events =  new HashSet<>();
}
