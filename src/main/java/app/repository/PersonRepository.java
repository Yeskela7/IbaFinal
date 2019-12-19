package app.repository;

import app.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//TODO add more methods for filter

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
