package app.repository;

import app.entity.Event;
import app.entity.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Optional<Event> findByTitle(String title);
    Iterable<Event> findAllByPlace(String place);
    Iterable<Event> findAllByTags(String tag);
    Iterable<Event> findAllByTimeLessThan(Long time);
    Iterable<Event> findAllByTimeIsGreaterThan(Long time);
    Iterable<Event> findAllByTagsIn(Collection<Tag> tags);
    //TODO get bi personId
    Iterable<Event> findAllByCreatorId(Long creatorId);
}