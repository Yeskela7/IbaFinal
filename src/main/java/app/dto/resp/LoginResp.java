package app.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class LoginResp {
    private String message;
    private String token;

}
