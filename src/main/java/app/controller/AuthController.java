package app.controller;

import app.dto.req.LoginReq;
import app.dto.req.LogoutReq;
import app.dto.req.RegisterReq;
import app.dto.resp.LoginResp;
import app.dto.resp.LogoutResp;
import app.dto.resp.RegisterResp;
import app.service.AuthService;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private final AuthService authService;
    @Autowired
    private final PersonService personService;

    public AuthController(AuthService authService, PersonService personService) {
        this.authService = authService;
        this.personService = personService;
    }

    @PostMapping("/login")
    public LoginResp handleLogin(@RequestBody LoginReq lr) {
        return authService.login(lr.getEmail(), lr.getPassword(), lr.isRemember())
                .map(t -> new LoginResp(personService.getByEmail(lr.getEmail()).get(), t))
                .orElse(new LoginResp(null, null));
    }

    @PostMapping("/logout")
    public LogoutResp handleLogout(@RequestBody LogoutReq lr){
        return new LogoutResp("POST:/logout:couldn't be implemented by using this approach");
    }

    @PostMapping("/register")
    public RegisterResp handleRegister(@RequestBody RegisterReq rq){
        boolean result = authService.registerNew(rq.getEmail(),rq.getPassword(),rq.getName(),rq.getSurname(), rq.getCity());
        return result ? RegisterResp.Ok() : RegisterResp.AlreadyExists();
    }
}
