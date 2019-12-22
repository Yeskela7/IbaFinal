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

}
