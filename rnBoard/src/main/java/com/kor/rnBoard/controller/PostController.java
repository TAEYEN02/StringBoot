package com.kor.rnBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kor.rnBoard.domain.Post;
import com.kor.rnBoard.dto.PostDTO;
import com.kor.rnBoard.dto.ResponseDTO;
import com.kor.rnBoard.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService service;

	@GetMapping
	public ResponseEntity<?> getfindAll() {
		List<PostDTO> post = service.AllPostShow();
		return ResponseEntity.ok(post);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody PostDTO dto) {
		Post entity = PostDTO.toEntity(dto);
		PostDTO created = service.addPost(entity);
		ResponseDTO<PostDTO> response = ResponseDTO.<PostDTO>builder().data(created).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById(@PathVariable("id") Integer id) {
	    PostDTO post = service.getPostById(id);
	    return ResponseEntity.ok(post);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<PostDTO>> getPostsByAuthor(@RequestParam("author") String author) {
	    List<PostDTO> posts = service.findByAuthor(author);
	    return ResponseEntity.ok(posts);
	}


}
