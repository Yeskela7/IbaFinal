package app.service;

import app.entity.Person;
import app.repository.PersonRepository;
import app.security.jwt.JwtTokenServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl {

    private final AuthenticationManager am;
    private final JwtTokenServiceImpl tp;
    private final PersonRepository repo;
    private final PasswordEncoder enc;

    public AuthServiceImpl(AuthenticationManager am,
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

}
