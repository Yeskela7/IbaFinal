package app.service;

import app.entity.Event;
import app.entity.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EventService {

    Optional<Event> getById(long id);
    Optional<Event> getByTitle(String title);
    Iterable<Event> getAll();
    Iterable<Event> getAllByTag(String tag);
    Iterable<Event> getByPlace(String place);
    Iterable<Event> getAllByTags(Collection<Tag> tags);
    public Iterable<Event> getAllByCreator(long id);
}
