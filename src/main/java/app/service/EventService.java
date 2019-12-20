package app.service;

import app.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    public Optional<Event> getById(long id);
    public Optional<Event> getByTitle(String title);
    public Iterable<Event> getAll();
    public Iterable<Event> getAllByTag(String tag);

}
