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

    @GetMapping(Paths.getEventPath)
    public Iterable<Event> handle_get_all() {
        return eventService.getAll();
    }

    @GetMapping(Paths.getEventPath + "{id}")
    public EventReq handle_get(@PathVariable("id") long id____, @RequestHeader (name="Authorization") TokenReq req) {
        Optional<Long> userId = Optional.of(req.getToken().split(" ")[1])
                .flatMap(jwt::tokenToClaim)
                .map(jwt::extractUserIdFromClaims);
        Person joiner = personService.getById(userId.get()).get();
        Event event = eventService.getById(id____).get();
        Person person = personService.getById(event.getCreatorId()).get();
        return new EventReq(id____
                ,event.getTitle(),
                person,
                event.getDescription(),
                event.getPlace(),
                event.getLocation(),
                event.getDate(),
                event.getTime(),
                event.getCategory(),
                eventService.contains(id____,joiner));
    }

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

    @PutMapping(Paths.getEventPath + "{id}")
    public String updateEvent(@PathVariable("id") long id____, @RequestBody EventReq req){
        Event event = eventService.getById(id____).orElseThrow(() -> new RuntimeException("Event not found on :: " + id____));

        event.setTitle(req.getTitle());
        event.setTime(req.getTime());
        event.setDate(req.getDate());
        event.setDescription(req.getDescription());
        event.setLocation(req.getLocation());
        event.setPlace(req.getPlace());
        event.setCategory(req.getCategory());

        eventService.update(event);
        return "Update done";
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
