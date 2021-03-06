package app.service;

import app.entity.Tag;
import app.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public Iterable<Tag> getAll(){
        Set<Tag> tags = new HashSet<>();
        repository.findAll().forEach(tags::add);
        return tags;
    }
}
