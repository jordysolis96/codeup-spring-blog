package com.spring.springblog.repositories;

import com.spring.springblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);

    @Query(nativeQuery = true, value = "select * from posts where user_id = ?")
    List<Post> findAllByUserId(long id);
}