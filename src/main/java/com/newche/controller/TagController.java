package com.newche.controller;

import com.newche.model.Tag;
import com.newche.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags") // Base path for all tag-related endpoints
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // Create a new tag
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = tagService.createTag(tag);
        return ResponseEntity.ok(createdTag);
    }

    // Retrieve a tag by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable String id) {
        Tag tag = tagService.getTagById(id);
        return ResponseEntity.ok(tag);
    }

    // Retrieve all tags
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    // Delete a tag
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable String id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }

    // Additional tag-related endpoints...

}



