package app.EventController;

import app.entity.Event;
import app.repository.EventRepository;
import app.service.EventService;
import app.service.EventServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private EventRepository aaa=null;
    private EventService esi=new EventServiceImpl(aaa);


    @RequestMapping(value = Paths.get_all_events, method = RequestMethod.GET)
    public @ResponseBody
    List<Event> getAll() {
        return esi.getAll();
    }

    @RequestMapping(value = Paths.get_by_id, method = RequestMethod.GET)
    public @ResponseBody
    Optional<Event> getById(long id) {
        return esi.getById(id);
    }

    @RequestMapping(value = Paths.get_by_title, method = RequestMethod.GET)
    public @ResponseBody
    Optional<Event> getByTitle(String title) {
        return esi.getByTitle(title);
    }
    @RequestMapping(value = Paths.get_tagged_events, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Event> getByTag(String tag) {
        return esi.getAllByTag(tag);
    }
}
