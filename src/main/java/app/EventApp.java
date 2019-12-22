package app;

import app.entity.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:9166/h2-console
 *
 */

@SpringBootApplication
public class EventApp {
    public static void main(String[] args) {
        SpringApplication.run(EventApp.class);
    }
}

