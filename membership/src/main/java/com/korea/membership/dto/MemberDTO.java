package com.korea.membership.dto;

import com.korea.membership.model.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
	private Long num;
	private String id;
	private String pass;
	private String addr;
	private String email;
	
	public MemberDTO(MemberEntity entity) {
		this.num = entity.getNum();
		this.id = entity.getId();
		this.pass = entity.getPass();
		this.addr = entity.getAddr();
		this.email = entity.getEmail();
	}
	
	public static MemberEntity toEntity (MemberDTO dto) {
		return MemberEntity.builder()
					.num(dto.num)
					.id(dto.id)
					.pass(dto.pass)
					.addr(dto.addr)
					.email(dto.email)
					.build();
	}
}
