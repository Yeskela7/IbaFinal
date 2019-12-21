package app.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig  implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        String[][] mappings = {
                //  mapping     file name in `resources/templates` folder `.html`
                {"/"      , "menu" },
                {"/guest" , "guest"},
                {"/home"  , "home" },
                {"/admin" , "admin"},
                {"/user"  , "user" },
        };

        for (String[] item: mappings) {
            registry.addViewController(item[0]).setViewName(item[1]);
        }
    }
}
