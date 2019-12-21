package app.controller;

import app.entity.Event;
import app.entity.Person;
import app.service.EventService;
import app.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class personController {

    private final PersonService person_service;

    public personController(PersonService service) {
        this.person_service = service;
    }

    @GetMapping(Paths.get_by_person_id+"/{id}")
    public Optional<Person> handle_get(@PathVariable("id") long id) {
        return person_service.getById(id);
    }

    @GetMapping(Paths.get_person_by_email+"/{email}")
    public Optional<Person> handle_get_tag(@PathVariable("email") String email) {
        return person_service.getByEmail(email);
    }
    @GetMapping(Paths.get_events_person+"/{event}")
    public Iterable<Person> handle_get_title(@PathVariable("event") Event event_name) {
        return person_service.getAllByEvent(event_name);
    }
}
