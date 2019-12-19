package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
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

    @Pattern(regexp="^(([0-3][0-9])([/])(0[1-9]|1[0-2])([/])([1-9][0-9][0-9][0-9]))$",
            message="Must be formatted DD/MM/YYYY")
    @Column(name = "birth_date")
    private String birth_date;

    @Column(name = "picURl")
    private String picURL;

    @ManyToMany(mappedBy="person")
    private Set<Event> events;
}
