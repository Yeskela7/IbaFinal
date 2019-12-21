package app.dto.req;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class LoginReq {
    private String email;
    private String password;
    private boolean remember;
}
