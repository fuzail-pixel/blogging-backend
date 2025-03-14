package com.inter.koffee.service.impl;

import com.inter.koffee.dto.BlogDto;
import com.inter.koffee.entity.Blog;
import com.inter.koffee.repository.BlogRepository;
import com.inter.koffee.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service // Marks this class as a Spring service component
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    // Constructor-based dependency injection
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        // Convert DTO to Entity
        Blog blog = mapToEntity(blogDto);
        // Save the entity to the database
        Blog savedBlog = blogRepository.save(blog);
        // Convert the saved entity back to DTO
        return mapToDto(savedBlog);
    }

    @Override
    public Page<BlogDto> getAllBlogs(Pageable pageable) {
        // Fetch all blogs with pagination and convert entities to DTOs
        return blogRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    public BlogDto getBlogById(Long id) {
        // Fetch a blog by ID or throw an exception if not found
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        // Convert the entity to DTO
        return mapToDto(blog);
    }

    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
        // Fetch the existing blog by ID
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        // Update only the fields that are provided in the request
        if (blogDto.getTitle() != null && !blogDto.getTitle().isEmpty()) {
            blog.setTitle(blogDto.getTitle());
        }
        if (blogDto.getContent() != null && !blogDto.getContent().isEmpty()) {
            blog.setContent(blogDto.getContent());
        }
        if (blogDto.getAuthor() != null && !blogDto.getAuthor().isEmpty()) {
            blog.setAuthor(blogDto.getAuthor());
        }

        // Save the updated blog
        Blog updatedBlog = blogRepository.save(blog);

        // Convert the updated entity to DTO
        return mapToDto(updatedBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        // Delete the blog by ID
        blogRepository.deleteById(id);
    }

    // Helper method to convert Entity to DTO
    private BlogDto mapToDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setTitle(blog.getTitle());
        blogDto.setContent(blog.getContent());
        blogDto.setAuthor(blog.getAuthor());
        blogDto.setCreatedAt(blog.getCreatedAt());
        return blogDto;
    }

    // Helper method to convert DTO to Entity
    private Blog mapToEntity(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setAuthor(blogDto.getAuthor());
        return blog;
    }
}