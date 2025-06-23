package com.korea.swagger.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.korea.swagger.dto.PostDTO;

@Service
public class PostService {
	
	//게시글을 저장하는 db라고 가정
	private final Map<Long, PostDTO> postMap = new HashMap<>();
	
	private Long nextId = 1L;
	//기본 생성자 (프로그램실행시 객체로 올라오면 자동으로 실행됨) 
	public PostService() {
		savePost(new PostDTO(null, "첫번째 게시글"));
		savePost(new PostDTO(null, "두번째 게시글"));
	}

	public List<PostDTO> getAllPosts(){
		return new ArrayList<PostDTO>(postMap.values());
	}
	
	public PostDTO getPostById(Long id) {
		return new PostDTO(id, "게시글 : "+id);
	}
	
	//게시글 작성하는 로직
	public PostDTO savePost(PostDTO postDTO) {
		postDTO.setId(nextId++);
		postMap.put(postDTO.getId(), postDTO);
		return postDTO;
	}
	
	public void deletePostById (Long id) {
		postMap.remove(id);
	}
}
