package app.repository;

import app.entity.Event;
import app.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    Iterable<Person> findAllByEvents(Event event);
}
