package app.security.userdetails;

import app.entity.Person;
import app.repository.PersonRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

    private final PersonRepository repository;

    public MyUserDetailsService(PersonRepository repository) {
        this.repository = repository;
    }

    public static UserDetails mapper(Person person) {
        return new MyUserDetails(
                person.getId(),
                person.getEmail(),
                person.getPassword()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .map(MyUserDetailsService::mapper)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User `%s` not found", email)));
    }

    public UserDetails loadUserById(long userid) throws UsernameNotFoundException {
        return repository.findById(userid)
                .map(MyUserDetailsService::mapper)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with id:%d` not found", userid)));
    }
}
