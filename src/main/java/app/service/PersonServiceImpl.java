package app.service;

import app.entity.Event;
import app.entity.Person;
import app.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getById(long id) {
        return personRepository.findById(id);
    }

    public Optional<Person> getByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Iterable<Person> getAllByEvent(Event event){
        Set<Person> personSet = new HashSet<>();
        personRepository.findAllByEvents(event).forEach(personSet::add);
        return personSet;
    }

    public void savePerson(Person person){
        personRepository.save(person);
    }
}
