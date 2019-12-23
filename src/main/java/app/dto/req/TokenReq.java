package app.dto.req;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class TokenReq {
    private String token;

    public TokenReq(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
