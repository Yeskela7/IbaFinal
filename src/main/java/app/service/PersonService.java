package app.service;

import app.entity.Event;
import app.entity.Person;

import java.util.Optional;

public interface PersonService {
    Optional<Person> getById(long id);
    Optional<Person> getByEmail(String email);
    Iterable<Person> getAllByEvent(Event event);
}
