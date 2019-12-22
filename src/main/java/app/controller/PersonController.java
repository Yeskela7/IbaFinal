package app.controller;

import app.entity.Event;
import app.entity.Person;
import app.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService service) {
        this.personService = service;
    }


    ///TODO get id from token
    @GetMapping(Paths.getPersonPath)
    public Optional<Person> handle_get(long id) {
        return personService.getById(id);
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
