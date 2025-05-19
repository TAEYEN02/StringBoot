package com.korea.board.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.board.dto.BoardDTO;
import com.korea.board.dto.IdListDTO;
import com.korea.board.model.BoardEntity;
import com.korea.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;

	@GetMapping
	public ResponseEntity<?> allData() {
		List<BoardDTO> list = service.allData();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody BoardDTO dto) {
		List<BoardDTO> list = service.create(dto);
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
		boolean result = service.deleteBoard(id);
		if (result) {
			return ResponseEntity.ok("삭제되었습니다");
		} else {
			return ResponseEntity.status(400).body("삭제에 실패했습니다. 아이디를 찾을 수 없거나 제약조건에 의해 삭제할 수 없습니다 : " + id);
		}
	}

	@GetMapping("id/{id}")
	public ResponseEntity<?> searchBoard(@PathVariable("id") Long id) {
		Optional<BoardEntity> result = service.selectId(id);
		if (result.isPresent()) {
			return ResponseEntity.ok(result.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("정보를 찾을 수 없습니다.");
		}
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<?> searchTitle(@PathVariable("title") String title) {
		List<BoardEntity> result = service.searchTitle(title);
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색 결과가 없습니다.");
		}
	}

	@GetMapping("/author/{author}")
	public ResponseEntity<?> searchAuthor(@PathVariable("author") String author) {
		List<BoardEntity> result = service.searchAuthor(author);
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색 결과가 없습니다.");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody BoardDTO dto) {
		BoardEntity entity = BoardDTO.toEntity(dto);
		List<BoardDTO> list = service.update(entity);
		return ResponseEntity.ok(list);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteBoards(@RequestBody IdListDTO idListDto) {
		service.deleteBoardsByIds(idListDto.getIds());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/boards")
	public ResponseEntity<List<BoardDTO>> getList(@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String keyword) {

		List<BoardDTO> list = service.searchBoards(searchType, keyword);
		return ResponseEntity.ok(list);
	}

}
