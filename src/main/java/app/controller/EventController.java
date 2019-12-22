package app.controller;

import app.dto.req.EventReq;
import app.dto.req.TokenReq;
import app.entity.Event;
import app.entity.Person;
import app.security.jwt.JwtTokenServiceImpl;
import app.security.userdetails.MyUserDetailsService;
import app.service.EventService;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class EventController {
    @Autowired
    private final EventService eventService;
    @Autowired
    private final PersonService personService;
    @Autowired
    private final MyUserDetailsService uds;
    @Autowired
    private final JwtTokenServiceImpl jwt;

    public EventController(EventService service, PersonService personService, MyUserDetailsService uds, JwtTokenServiceImpl jwt) {
        this.eventService = service;
        this.personService = personService;
        this.uds = uds;
        this.jwt = jwt;
    }

    //TODO getAll++
    @GetMapping(Paths.getEventPath)
    public Iterable<Event> handle_get_all() {
        System.out.println("------------------------");
        System.out.println(eventService.getAll());
        System.out.println("------------------------");
        return eventService.getAll();
    }

    //TODO getById++
    @GetMapping(Paths.getEventPath + "{id}")
    public Optional<Event> handle_get(@PathVariable("id") long id____) {
        return eventService.getById(id____);
    }

//    @GetMapping(Paths.get_tagged_events+"{tag}")
//    public Iterable<Event> handle_get_tag(@PathVariable("tag") String tag) {
//        return eventService.getAllByTag(tag);
//    }
//
//    @GetMapping(Paths.get_by_title+"{title}")
//    public Optional<Event> handle_get_title(@PathVariable("title") String title) {
//        return eventService.getByTitle(title);
//    }

    //    @PostMapping
//    public String postEvent(@RequestBody Event event){
//        eventService.saveEvent(event);
//        return "Added";
//    }
//    String title, long creatorId, String description, Geo geo, String place, long time
    //TODO SaveOne++
    @PostMapping(Paths.getEventPath)
    public String postEvent(@RequestBody EventReq req) {
        eventService.saveEvent(new Event(req.getTitle(),
                req.getCreatorId(),
                req.getDescription(),
                req.getLocation(),
                req.getPlace(),
                req.getTime()));
        return "Added";
    }

    @PostMapping(Paths.getEventPath +"{id}")
    public String addPerson(@PathVariable("id") long id, @RequestBody TokenReq req){
        Optional<Long> userId = Optional.of(req.getToken())
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        Optional<Person> person = personService.getById(userId.get());
        eventService.savePersonIntoEvent(id,person.get());
        Event event = eventService.getById(id).get();
        eventService.saveEvent(event);
        return "Person joined";
    }
}
