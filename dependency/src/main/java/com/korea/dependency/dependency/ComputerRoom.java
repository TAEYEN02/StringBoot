package com.korea.dependency.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ComputerRoom {

	//Computer클래스를 상속받는 삼성,애플 클래스의
	//객체가 모두 들어올 수 있다.
	
	@Autowired
	@Qualifier("Samsung")
	Computer com1;
	
	@Autowired
	@Qualifier("Apple")
	Computer com2;
	
	
	
	
}
