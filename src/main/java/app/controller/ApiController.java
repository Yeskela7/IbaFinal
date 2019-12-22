package app.controller;

import app.dto.resp.ApiMessageResp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/login/")
    public ApiMessageResp handleGuest(){
        return new ApiMessageResp("OK:any unregistered");
    }

    @GetMapping("/home")
    public ApiMessageResp handleHome(){
        return new ApiMessageResp("OK:any authenticated");
    }

    @GetMapping("/user")
    public ApiMessageResp handleUser(){
        return new ApiMessageResp("OK:any authorized with USER role");
    }

    @GetMapping("/event/*")
    public ApiMessageResp handleEvent(){
        return new ApiMessageResp("OK:any authorized with USER role");
    }
}
