package com.inter.koffee.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data // Lombok annotation for getters, setters, equals, hashCode, and toString
public class BlogDto {

    private Long id; // Unique identifier for the blog

    private String title; // Title of the blog

    private String content; // Content of the blog

    private String author; // Author of the blog

    private LocalDateTime createdAt; // Timestamp when the blog was created
}