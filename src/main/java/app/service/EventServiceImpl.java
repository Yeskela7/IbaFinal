package app.service;

import app.entity.Event;
import app.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Event> getAll(){
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    public Iterable<Event> getAllByTag(String tag){
        List<Event> events = new ArrayList<>();
        eventRepository.findAllByTags(tag).forEach(events::add);
        return events;
    }
}
