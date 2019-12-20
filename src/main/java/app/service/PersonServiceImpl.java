package app.service;

import app.entity.Person;
import app.repository.PersonRepository;
import java.util.Optional;

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
}
