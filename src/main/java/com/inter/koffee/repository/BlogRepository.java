package com.inter.koffee.repository;

import com.inter.koffee.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring Data repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}