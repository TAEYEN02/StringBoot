package com.korea.member.dto;

import java.util.List;
import java.util.Optional;

import com.korea.member.model.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
	private String error;
	private List<MemberDTO> data;
}
