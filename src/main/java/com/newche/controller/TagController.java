package com.newche.controller;

import com.newche.model.Tag;
import com.newche.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = tagService.createTag(tag);
        return ResponseEntity.ok(createdTag);
    }

    // Retrieve a tag by ID
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable String id) {
        Tag tag = tagService.getTagById(id);
        return ResponseEntity.ok(tag);
    }

    // Retrieve all tags
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    // Delete a tag
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable String id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/level")
    @CrossOrigin
    public ResponseEntity<List<Tag>> getTagsByLevel(@RequestParam int level) {
        List<Tag> leveledTags = tagService.getTagsByLevel(level);
        return ResponseEntity.ok(leveledTags);
    }
    

}



