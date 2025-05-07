package com.korea.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name="User")
@NoArgsConstructor

public class UserEntity {
	@Id
	//JPA에서 기본 키를 자동으로 생성하느 방법을 정의하는 어노테이션
	//H2와 같은 내장 데이터베이스를 사용하는 경우, 기본적으로 숫자값이 증가하는 방식으로 ID가 설정된다.
	@GeneratedValue(strategy =GenerationType.AUTO)
	public int id;
	public String name;
	public String email;
}
