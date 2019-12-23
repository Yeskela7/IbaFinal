package app.controller;

import app.entity.Tag;
import app.service.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CategoriesController {

    private final TagService tagService;

    public CategoriesController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(Paths.getCategories)
    public Iterable<Tag> tags(){
        return tagService.getAll();
    }
}
