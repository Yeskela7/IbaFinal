package app.controller;

import app.dto.req.EventReq;
import app.dto.req.TokenReq;
import app.entity.DateConverter;
import app.entity.Event;
import app.entity.Geo;
import app.entity.Person;
import app.security.jwt.JwtTokenServiceImpl;
import app.security.userdetails.MyUserDetailsService;
import app.service.EventService;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
        return eventService.getAll();
    }

    //TODO getById++
    @GetMapping(Paths.getEventPath + "{id}")
    public EventReq handle_get(@PathVariable("id") long id____, @RequestHeader (name="Authorization") TokenReq req) {
        Optional<Long> userId = Optional.of(req.getToken().split(" ")[1])
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        Person joiner = personService.getById(userId.get()).get();
        Event event = eventService.getById(id____).get();
        Person person = personService.getById(event.getCreatorId()).get();
        return new EventReq(event.getTitle(),
                person,
                event.getDescription(),
                event.getPlace(),
                event.getLocation(),
                event.getDate(),
                event.getTime(),
                eventService.contains(id____,joiner));
    }

//    @GetMapping(Paths.getEventPath+"{tag}")
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
    public String postEvent(@RequestBody EventReq req, @RequestHeader (name="Authorization") TokenReq tokenReq) throws ParseException {
        Optional<Long> userId = Optional.of(tokenReq.getToken().split(" ")[1])
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        eventService.saveEvent(new Event(req.getTitle(),
                userId.get(),
                req.getDescription(),
                req.getLocation(),
                req.getPlace(),
                req.getTime(),
                req.getDate()));                ;
        return "Added";
    }

    @GetMapping(Paths.getNearBy)
    public Iterable<Event> nearBy(@RequestParam(name = "latitude") double lat, @RequestParam(name = "longitude") double lon){
        return eventService.getEventNearBy(lat, lon);
    }

    @PostMapping(Paths.getEventPath +"{id}")
    public String addPerson(@PathVariable("id") long id, @RequestHeader (name="Authorization") TokenReq req){
        Optional<Long> userId = Optional.of(req.getToken().split(" ")[1])
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        Optional<Person> person = personService.getById(userId.get());
        eventService.savePersonIntoEvent(id,person.get());
        Event event = eventService.getById(id).get();
        eventService.saveEvent(event);
        return "on the way";
    }
}
