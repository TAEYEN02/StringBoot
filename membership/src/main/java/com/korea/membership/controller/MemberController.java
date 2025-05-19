package com.korea.membership.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.korea.membership.dto.MemberDTO;
import com.korea.membership.model.MemberEntity;
import com.korea.membership.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	@PutMapping
	public ResponseEntity<?> addMember(@RequestBody MemberDTO dto){
		MemberEntity entity =MemberDTO.toEntity(dto);
		List<MemberDTO> member = service.addMember(entity);
		return ResponseEntity.ok(member);
	}
}
