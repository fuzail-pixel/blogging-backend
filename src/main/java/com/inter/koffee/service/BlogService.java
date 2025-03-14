package com.inter.koffee.service;

import com.inter.koffee.dto.BlogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {

    // Method to create a new blog
    BlogDto createBlog(BlogDto blogDto);

    // Method to fetch all blogs with pagination
    Page<BlogDto> getAllBlogs(Pageable pageable);

    // Method to fetch a single blog by ID
    BlogDto getBlogById(Long id);

    // Method to update an existing blog
    BlogDto updateBlog(Long id, BlogDto blogDto);

    // Method to delete a blog by ID
    void deleteBlog(Long id);
}