package app.controller;

import app.entity.Event;
import app.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService service) {
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
}
