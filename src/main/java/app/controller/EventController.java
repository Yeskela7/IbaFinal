package app.controller;

import app.dto.req.EventReq;
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
    public String postEvent(@RequestBody Event event){
        eventService.saveEvent(event);
        return "Added";
    }
//    String title, long creatorId, String description, long latitude, long longitude, String place, long time
    @PostMapping("/event/save")
    public String postEvent(@RequestBody EventReq req){
        eventService.saveEvent(new Event(
                req.getTitle(),
                req.getCreatorId(),
                req.getDescription(),
                req.getLatitude(),
                req.getLongitude(),
                req.getPlace(),
                req.getTime()));
        return "Added";
    }
}
