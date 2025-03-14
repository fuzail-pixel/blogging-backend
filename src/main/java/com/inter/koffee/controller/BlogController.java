package com.inter.koffee.controller;

import com.inter.koffee.dto.BlogDto;
import com.inter.koffee.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/blogs") // Base URL for all endpoints in this controller
public class BlogController {

    private final BlogService blogService;

    // Constructor-based dependency injection
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // Create a new blog
    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto) {
        BlogDto createdBlog = blogService.createBlog(blogDto);
        return ResponseEntity.status(201).body(createdBlog); // 201 Created
    }

    // Fetch all blogs with pagination
    @GetMapping
    public ResponseEntity<Page<BlogDto>> getAllBlogs(Pageable pageable) {
        Page<BlogDto> blogs = blogService.getAllBlogs(pageable);
        return ResponseEntity.ok(blogs); // 200 OK
    }

    // Fetch a single blog by ID
    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) {
        BlogDto blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog); // 200 OK
    }

    // Update an existing blog
    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        BlogDto updatedBlog = blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok(updatedBlog); // 200 OK
    }

    // Delete a blog by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}