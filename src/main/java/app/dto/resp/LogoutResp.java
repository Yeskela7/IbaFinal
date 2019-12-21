package app.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LogoutResp {
    private String message;

    public LogoutResp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
