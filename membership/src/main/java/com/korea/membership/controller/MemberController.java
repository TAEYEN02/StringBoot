package com.korea.membership.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.membership.dto.MemberDTO;
import com.korea.membership.model.MemberEntity;
import com.korea.membership.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@PostMapping
	public ResponseEntity<?> addMember(@RequestBody MemberDTO dto){
		MemberEntity entity =MemberDTO.toEntity(dto);
		List<MemberDTO> member = service.addMember(entity);
		return ResponseEntity.ok(member);
	}
	@GetMapping
	public ResponseEntity<?> memberList(){
		List<MemberDTO> member = service.memberList();
		return ResponseEntity.ok(member);
	}
}
