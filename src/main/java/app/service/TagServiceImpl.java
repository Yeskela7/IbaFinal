package app.service;

import app.entity.Tag;
import app.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagServiceImpl{

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Iterable<Tag> getAll() {
        Set<Tag> tags = new HashSet<>();
        tagRepository.findAll().forEach(tags::add);
        return tags;
    }

}