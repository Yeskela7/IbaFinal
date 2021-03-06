package app.repository;

import app.entity.Comment;
import app.entity.Event;
import app.entity.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Optional<Event> findByTitle(String title);
    Iterable<Event> findAllByPlace(String place);
    Iterable<Event> findAllByCategory(String category);
    Iterable<Event> findAllByTimeLessThan(Long time);
    Iterable<Event> findAllByTimeIsGreaterThan(Long time);
    Iterable<Event> findAllByCategoryIn(Collection<Tag> category);
    Iterable<Event> findAllByTimeBetween(Long t1, Long t2);
    Iterable<Event> findAllByCreatorId(Long id);
}
