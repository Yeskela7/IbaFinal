package app;

import app.entity.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/h2-console
 */

@SpringBootApplication
public class EventApp {
    public static void main(String[] args) {
        Event e = new Event("event",2,"df",2,2,"23",331);
        System.out.println(e.getPerson());
        SpringApplication.run(EventApp.class);
    }
}

