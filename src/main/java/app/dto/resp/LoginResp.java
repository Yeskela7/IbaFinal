package app.dto.resp;

import app.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class LoginResp {
    private Person person;
    private String token;
}
