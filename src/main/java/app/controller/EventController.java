package app.controller;

import app.entity.Event;
import app.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService service) {
        this.eventService = service;
    }

    @GetMapping(Paths.get_all_events)
    public Iterable<Event> handle_get_all() {
        return eventService.getAll();
    }

    @GetMapping(Paths.get_by_id+"{id}")
    public Optional<Event> handle_get(@PathVariable("id") long id) {
        return eventService.getById(id);
    }

    @GetMapping(Paths.get_tagged_events+"{tag}")
    public Iterable<Event> handle_get_tag(@PathVariable("tag") String tag) {
        return eventService.getAllByTag(tag);
    }

    @GetMapping(Paths.get_by_title+"{title}")
    public Optional<Event> handle_get_title(@PathVariable("title") String title) {
        return eventService.getByTitle(title);
    }

    @PostMapping
    public void postEvent(@RequestBody Event event){
        eventService.saveEvent(event);
    }
}
