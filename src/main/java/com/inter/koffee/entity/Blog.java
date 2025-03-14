package com.inter.koffee.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data // Lombok annotation for getters, setters, equals, hashCode, and toString
@Entity // Marks this class as a JPA entity
public class Blog {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    private Long id;

    @Column(nullable = false) // Ensures this field cannot be null in the database
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // Maps this field to a TEXT column
    private String content;

    @Column(nullable = false) // Ensures this field cannot be null in the database
    private String author;

    @Column(nullable = false, updatable = false) // Ensures this field cannot be null or updated
    private LocalDateTime createdAt = LocalDateTime.now(); // Automatically sets the creation time

}
