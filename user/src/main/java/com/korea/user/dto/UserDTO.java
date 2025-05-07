package com.korea.user.dto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
	public int id;
	public String name;
	public String email;
	
	public UserDTO(final UserEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}
	
	public static UserEntity toEntity(UserDTO dto) {
		return UserEntity.builder()
					.id(dto.getId())
					.name(dto.getName())
					.email(dto.getEmail())
					.build();
	}
}
