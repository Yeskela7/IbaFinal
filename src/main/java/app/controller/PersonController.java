package app.controller;

import app.dto.req.TokenReq;
import app.dto.resp.PersonResp;
import app.entity.Event;
import app.entity.Person;
import app.security.jwt.JwtTokenServiceImpl;
import app.security.userdetails.MyUserDetailsService;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping
public class PersonController {
    @Autowired
    private final PersonService personService;
    @Autowired
    private final MyUserDetailsService uds;
    @Autowired
    private final JwtTokenServiceImpl jwt;

    public PersonController(PersonService service, MyUserDetailsService uds, JwtTokenServiceImpl jwt) {
        this.personService = service;
        this.uds = uds;
        this.jwt = jwt;
    }

    @GetMapping(Paths.getPersonPath)
    public PersonResp handle_get(@RequestHeader (name="Authorization") TokenReq req) {
        Optional<Long> userId = Optional.of(req.getToken().split(" ")[1])
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        Optional<Person> person = personService.getById(userId.get());
        return new PersonResp(person.get().getId(),person.get().getEmail(),
                person.get().getName(),person.get().getSurname());
    }

    @GetMapping(Paths.getJoinedEvent)
    public Iterable<Event> getMyEvents(@RequestHeader (name="Authorization") TokenReq req) {
        Optional<Long> userId = Optional.of(req.getToken().split(" ")[1])
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        return personService.getMyEvents(userId.get());
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
