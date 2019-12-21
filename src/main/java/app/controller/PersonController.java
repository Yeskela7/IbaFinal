package app.controller;

import app.entity.Event;
import app.entity.Person;
import app.service.PersonServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class PersonController {

    private final PersonServiceImpl personService;

    public PersonController(PersonServiceImpl service) {
        this.personService = service;
    }

    @GetMapping("/{id}")
    public Optional<Person> handle_get(long id) {
        return personService.getById(id);
    }

    @GetMapping("/{email}")
    public Optional<Person> handle_get_email(@PathVariable("email") String email) {
        return personService.getByEmail(email);
    }

    @GetMapping("/{event}")
    public Iterable<Person> handle_get_event(@PathVariable("event") Event event) {
        return personService.getAllByEvent(event);
    }

    @PostMapping
    public void postPerson(@RequestBody Person person) {
        personService.savePerson(person);
    }
}
