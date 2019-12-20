package app.service;

import app.entity.Event;
import app.entity.Tag;
import app.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<Event> getById(long id){
        return eventRepository.findById(id);
    }

    public Optional<Event> getByTitle(String title){
        return eventRepository.findByTitle(title);
    }

    public Iterable<Event> getAll(){
        Set<Event> events = new HashSet<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    public Iterable<Event> getAllByTag(String tag){
        Set<Event> events = new HashSet<>();
        eventRepository.findAllByTags(tag).forEach(events::add);
        return events;
    }

    public Iterable<Event> getAllByTags(Collection<Tag> tags){
        Set<Event> events = new HashSet<>();
        eventRepository.findAllByTagsIn(tags).forEach(events::add);
        return events;
    }
}
