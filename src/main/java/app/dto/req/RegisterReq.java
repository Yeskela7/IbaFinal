package app.dto.req;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RegisterReq {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String birthDate;

    public RegisterReq(String email, String password, String name, String surname, String birthDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
