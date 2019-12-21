package app.controller;

import app.dto.req.LoginReq;
import app.dto.req.LogoutReq;
import app.dto.req.RegisterReq;
import app.dto.resp.LoginResp;
import app.dto.resp.LogoutResp;
import app.dto.resp.RegisterResp;
import app.service.AuthServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResp handleLogin(@RequestBody LoginReq lr) {
        return authService.login(lr.getEmail(), lr.getPassword(), lr.isRemember())
                .map(t -> new LoginResp("Ok", t))
                .orElse(new LoginResp("Error", null));
    }

    @PostMapping("/logout")
    public LogoutResp handleLogout(@RequestBody LogoutReq lr){
        return new LogoutResp("POST:/logout:couldn't be implemented by using this approach");
    }

    @PostMapping("/register")
    public RegisterResp handleRegister(@RequestBody RegisterReq rq){
        boolean result = authService.registerNew(rq.getEmail(),rq.getPassword(),rq.getName(),rq.getSurname(),rq.getBirthDate());
        return result ? RegisterResp.Ok() : RegisterResp.AlreadyExists();
    }
}
