package app.service;

import app.entity.Person;

import java.util.Optional;

public interface PersonService {
    public Optional<Person> getById(long id);
    public Optional<Person> getByEmail(String email);
}
