package com.kor.rnBoard.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kor.rnBoard.domain.Post;
import com.kor.rnBoard.dto.PostDTO;
import com.kor.rnBoard.repository.PostRepository;

@Service
public class PostService {
	private PostRepository repository;

	// 생성자 주입
	// 또는 @Autowired
	public PostService(PostRepository postRepository) {
		this.repository = postRepository;
	}

	public List<PostDTO> AllPostShow() {
		return repository.findAll().stream().map(PostDTO::new).collect(Collectors.toList());
	}

	public PostDTO addPost(Post entity) {
		Post saved = repository.save(entity);
		return new PostDTO(saved); // 저장된 글 하나만 반환
	}

	public PostDTO getPostById(int id) {
		Post post = repository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다. ID: " + id));

		// 조회수 증가 (int 타입이라 null 체크 불필요)
		post.setViews(post.getViews() + 1);
		repository.save(post);
		return new PostDTO(post);
	}

	public List<PostDTO> findByAuthor(String author) {
		List<Post> posts = repository.findByAuthorContainingIgnoreCase(author);
		return posts.stream().map(PostDTO::new).collect(Collectors.toList());
	}

}
