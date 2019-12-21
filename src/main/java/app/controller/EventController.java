package app.controller;

import app.entity.Event;
import app.service.EventService;
import app.service.EventServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventServiceImpl eventService;

    public EventController(EventServiceImpl service) {
        this.eventService = service;
    }

    @GetMapping
    public Iterable<Event> handle_get_all() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Event> handle_get(@PathVariable("id") long id) {
        return eventService.getById(id);
    }

    @GetMapping("/{tag}")
    public Iterable<Event> handle_get_tag(@PathVariable("tag") String tag) {
        return eventService.getAllByTag(tag);
    }

    @GetMapping("/{title}")
    public Optional<Event> handle_get_title(@PathVariable("title") String title) {
        return eventService.getByTitle(title);
    }

    @PostMapping
    public void postEvent(@RequestBody Event event){
        eventService.saveEvent(event);
    }
}
