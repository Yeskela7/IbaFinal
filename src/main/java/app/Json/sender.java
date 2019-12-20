package app.Json;
import app.DummyClass.Human;
import app.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class sender {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public List<Human> test() {
        return new ArrayList<Human>(){{
            add(new Human("human1","surname1"));
            add(new Human("human2","surname2"));
            add(new Human("human3","surname3"));
            add(new Human("human4","surname4"));
            add(new Human("human5","surname5"));
            add(new Human("human6","surname6"));
        }};
    }

}
