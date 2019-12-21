package app.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterResp {

    private String message;

    public RegisterResp(String message) {
        this.message = message;
    }

    public static RegisterResp Ok() {
        return new RegisterResp("Ok");
    }

    public static RegisterResp AlreadyExists() {
        return new RegisterResp("Error: User Already Exists");
    }
}
