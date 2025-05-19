package com.korea.membership.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long num;
	
	private String id;
	private String pass;
	private String addr;
	private String email;
}
