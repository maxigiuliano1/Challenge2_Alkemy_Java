package com.example.challenge2_alkemy.repositories;

import java.util.List;

import com.example.challenge2_alkemy.model.Blogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Long>{
    List<Blogs> findByTitle(String title);
    List<Blogs> findByCategory(String category);
}
