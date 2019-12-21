package app.dto.resp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class ApiMessageResp {
    private String content;

    public ApiMessageResp(String content) {
        this.content = content;
    }
}
