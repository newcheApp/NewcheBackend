package com.newche.service;

import com.newche.dao.TagDAO;
import com.newche.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {

    private final TagDAO tagDAO;

    @Autowired
    public TagService(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    // Create a new tag
    public Tag createTag(Tag tag) {
        // Additional logic before saving the tag (if necessary)
        return tagDAO.createTag(tag);
    }

    // Retrieve a tag by ID
    public Tag getTagById(String id) {
        return tagDAO.findTagById(id);
    }

    // Retrieve a tag by name
    public Tag getTagByName(String name) {
        return tagDAO.findTagByName(name);
    }

    // Retrieve all tags
    public List<Tag> getAllTags() {
        return tagDAO.findAllTags();
    }

    // Delete a tag
    public void deleteTag(String id) {
        tagDAO.deleteTag(id);
    }

    public List<Tag> getTagsByLevel(int level) {
        return tagDAO.findTagsByLevel(level);
    }


}
