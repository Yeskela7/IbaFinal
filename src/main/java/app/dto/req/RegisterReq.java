package app.dto.req;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RegisterReq {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String city;
    private String password;

}
