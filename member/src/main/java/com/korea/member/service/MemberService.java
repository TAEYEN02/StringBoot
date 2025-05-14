package com.korea.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.korea.member.dto.MemberDTO;
import com.korea.member.model.MemberEntity;
import com.korea.member.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;
	
	public List<MemberDTO> AllMemberShow(){ 
		return repository.findAll().stream()
				.map(MemberDTO::new).collect(Collectors.toList());
	}
	
	public List<MemberDTO> getEmail(String email) {
	    Optional<MemberEntity> entity = repository.findByEmail(email);
	    if (entity.isPresent()) {
	        return List.of(new MemberDTO(entity.get()));
	    } else {
	        return List.of();
	    }
	}

	public List<MemberDTO> create(MemberEntity entity){
		repository.save(entity);
		return repository.findAll().stream()
					.map(MemberDTO::new).collect(Collectors.toList());
	}
	
	public List<MemberDTO> updateMember(MemberEntity entity, String email){
		Optional<MemberEntity> member = repository.findByEmail(email);
		member.ifPresent(memberentity->{
			memberentity.setName(entity.getName());
			memberentity.setPassword(entity.getPassword());
			
			repository.save(memberentity);
		});
		return AllMemberShow();
	}
	
	public boolean deleteMember(int id){
		Optional<MemberEntity> user = repository.findById(id);
		if(user.isPresent()) {
			repository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
