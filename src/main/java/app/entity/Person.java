package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Getter
@Table(name = "users")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "user_id")
    private long id;

    @NotBlank(message="Name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message="Surname is required")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message="Email is required")
    @Column(unique = true, name = "email")
    private String email;

    @NotBlank(message="City is required")
    @Column(name = "city")
    private String city;

    @Column(name = "regTime")
    private String regTime;

    @NotBlank(message="Password is required")
    @Column( name = "password")
    private String password;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="guests")
    private Collection<Event> events = new HashSet<>();


    public Person(@NotBlank(message = "Email is required") String email,
                  @NotBlank(message = "Name is required") String name,
                  @NotBlank(message = "Surname is required") String surname,
                  @NotBlank(message = "City is required") String city,
                  @NotBlank(message = "Password is required") String password,
                  String regTime) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.regTime = regTime;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getRegTime() {
        return regTime;
    }

    public Collection<Event> getEvents() {
        return events;
    }
}
