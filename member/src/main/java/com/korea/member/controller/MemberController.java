package com.korea.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.korea.member.dto.MemberDTO;
import com.korea.member.model.MemberEntity;
import com.korea.member.service.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody MemberDTO dto){
		MemberEntity entity = MemberDTO.toEntiy(dto);
		List<MemberDTO> user = service.create(entity);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<?> getfindAll(){
		List<MemberDTO> user = service.AllMemberShow();
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getUserEmail(@PathVariable String email){
		List<MemberDTO> userlist = service.getEmail(email);
		return ResponseEntity.ok(userlist);
	}
	
	@PutMapping("/{email}/password")
	public List<MemberDTO> updatePassword(@PathVariable String email, @RequestBody MemberEntity entity) {
	    String newPassword = entity.getPassword();
	    service.updateMember(entity, newPassword);
		return service.updateMember(entity, email);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
	    try {
	        List<MemberDTO> updatedList = service.deleteMember(id);
	        return ResponseEntity.ok(updatedList);
	    } catch (Exception e) {
	        return ResponseEntity.status(400).body("아이디를 찾을 수 없습니다: " + id);
	    }
	}
}

