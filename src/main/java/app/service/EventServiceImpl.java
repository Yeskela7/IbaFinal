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

    public Iterable<Event> getByPlace(String place){
        Set<Event> events = new HashSet<>();
        eventRepository.findAllByPlace(place).forEach(events::add);
        return events;
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

    public Iterable<Event> getAllByCreator(long id){
        Set<Event> events = new HashSet<>();
        eventRepository.findAllByCreatorId(id).forEach(events::add);
        return events;
    }

    public Iterable<Event> getAllByTime(long t1, long t2){
        Set<Event> events = new HashSet<>();
        if (t1 == 0 && t2 == 0) throw new IllegalArgumentException("Both are null");
        if (t2 == 0) {
            eventRepository.findAllByTimeLessThan(t2).forEach(events::add);
            return events;
        }
        if (t1 == 0) {
            eventRepository.findAllByTimeIsGreaterThan(t1).forEach(events::add);
            return events;
        }
        eventRepository.findAllByTimeBetween(t1,t2).forEach(events::add);
        return events;
    }

    public void saveEvent(Event event){
        eventRepository.save(event);
    }
}
