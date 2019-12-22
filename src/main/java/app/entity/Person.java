package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    @NotBlank(message="Password is required")
    @Column( name = "password")
    private String password;

    @Pattern(regexp="^(([0-3][0-9])([/])(0[1-9]|1[0-2])([/])([1-2][0-9][0-9][0-9]))$",
            message="Must be formatted DD/MM/YYYY")
    @Column(name = "birth_date")
    private String birth_date;

//    @Column(name = "picURl")
//    private String picURL;

    @ManyToMany(mappedBy="person")
    private Set<Event> events =  new HashSet<>();

    public Person(@NotBlank(message = "Email is required") String email,
                  @NotBlank(message = "Password is required") String password,
                  @NotBlank(message = "Name is required") String name,
                  @NotBlank(message = "Surname is required") String surname,
                  @NotBlank(message = "birth_date is required") String birth_date) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birth_date = birth_date;
    }
}
