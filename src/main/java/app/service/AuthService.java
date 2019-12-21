package app.service;

import app.entity.Person;
import app.repository.PersonRepository;
import app.security.jwt.Const;
import app.security.jwt.JwtTokenServiceImpl;
import app.security.userdetails.MyUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthenticationManager am;
    private final JwtTokenServiceImpl tp;
    private final PersonRepository repo;
    private final PasswordEncoder enc;

    public AuthService(AuthenticationManager am,
                       JwtTokenServiceImpl tp,
                       PersonRepository repo,
                       PasswordEncoder enc) {
        this.am = am;
        this.tp = tp;
        this.repo = repo;
        this.enc = enc;
    }

    public boolean registerNew(String email, String password,String name,String surname,String birthDate) {
        Optional<Person> found = repo.findByEmail(email);
        if (!found.isPresent()) {
            repo.save(new Person(email,enc.encode(password),name,surname,birthDate));
        }
        return !found.isPresent();
    }

    public Optional<String> login(String username, String password, boolean remember) {
        return Optional.of(am.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
                .filter(Authentication::isAuthenticated)
                .map(a -> { SecurityContextHolder.getContext().setAuthentication(a); return  a; })
                .map(a -> (MyUserDetails) a.getPrincipal())
                .map(ud -> tp.generateToken(ud.getId(), remember))
                .map(t -> Const.AUTH_BEARER + t);
    }

}
