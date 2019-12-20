package app.repository;

import app.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Optional<Event> findByTitle(String title);
    Iterable<Event> findAllByPlace(String place);
    Iterable<Event> findAllByTags(String tag);
    Iterable<Event> findAllByTimeLessThan(Long time);
    Iterable<Event> findAllByTimeIsGreaterThan(Long time);
    Iterable<Event> findAllByTimeLessThanOrderByTime(Long time);

    List<Event> getAllByTitle(String title);
}
