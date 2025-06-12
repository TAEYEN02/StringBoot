package com.kor.rnBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kor.rnBoard.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
	List<Post> findByAuthorContainingIgnoreCase(String author);
}
