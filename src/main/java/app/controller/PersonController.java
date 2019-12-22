package app.controller;

import app.dto.req.TokenReq;
import app.entity.Person;
import app.security.jwt.JwtTokenServiceImpl;
import app.security.userdetails.MyUserDetailsService;
import app.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping
public class PersonController {

    private final PersonService personService;
    private final MyUserDetailsService uds;
    private final JwtTokenServiceImpl jwt;

    public PersonController(PersonService service, MyUserDetailsService uds, JwtTokenServiceImpl jwt) {
        this.personService = service;
        this.uds = uds;
        this.jwt = jwt;
    }

    @GetMapping(Paths.getPersonPath)
    public Optional<Person> handle_get(@RequestBody TokenReq req) {
        Optional<Long> userId = Optional.of(req.getToken())
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        Optional<Person> person = personService.getById(userId.get());
        return person;
    }

//    @GetMapping(Paths.get_person_by_email + "{email}")
//    public Optional<Person> handle_get_email(@PathVariable("email") String email) {
//        return personService.getByEmail(email);
//    }

//    @GetMapping(Paths.get_events_person + "{event}")
//    public Iterable<Person> handle_get_event(@PathVariable("event") Event event) {
//        return personService.getAllByEvent(event);
//    }

//    @PostMapping
//    public void postPerson(@RequestBody Person person) {
//        personService.savePerson(person);
//    }
}
