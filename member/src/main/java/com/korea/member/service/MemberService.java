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
	
	//전체 회원 조회
	public List<MemberDTO> AllMemberShow(){ 
		return repository.findAll().stream()
				.map(MemberDTO::new).collect(Collectors.toList());
	}
	//이메일 받기
	public List<MemberDTO> getEmail(String email) {
	    Optional<MemberEntity> entity = repository.findByEmail(email);
	    if (entity.isPresent()) {
	        return List.of(new MemberDTO(entity.get()));
	    } else {
	        return List.of();
	    }
	}
	//회원 추가
	public List<MemberDTO> create(MemberEntity entity){
		//JPA로 데이터베이스에 데이터를 추가할 때 만큼은 Entity타입이어야한다
		repository.save(entity);
		return repository.findAll().stream()
					.map(MemberDTO::new).collect(Collectors.toList());
	}
	//비밀번호 변경
	public List<MemberDTO> updateMember(MemberEntity entity, String email){
		Optional<MemberEntity> member = repository.findByEmail(email);
		member.ifPresent(memberentity->{
			memberentity.setPassword(entity.getPassword());
			repository.save(memberentity);
		});
		return AllMemberShow();
	}
	//회원 삭제
	public List<MemberDTO> deleteMember(int id){
		if(repository.existsById(id)) {
			repository.deleteById(id);
		}else {
			throw new RuntimeException("조회된 회원이 없습니다");
		}
	    return AllMemberShow();
	}
}
