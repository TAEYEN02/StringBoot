package com.korea.membership.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.membership.dto.MemberDTO;
import com.korea.membership.model.MemberEntity;
import com.korea.membership.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;
	
	public List<MemberDTO> addMember(MemberEntity entity){
		repository.save(entity);
		return repository.findAll().stream()
					.map(MemberDTO::new).collect(Collectors.toList());
	}
	
	public List<MemberDTO> memberList(){
		return repository.findAll().stream().map(MemberDTO::new)
					.collect(Collectors.toList());
	}
}
