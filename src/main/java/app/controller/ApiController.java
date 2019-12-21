package app.controller;

import app.dto.resp.ApiMessageResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/guest")
    public ApiMessageResp handleGuest(){
        return new ApiMessageResp("OK:any unregistered");
    }
}
