package com.korea.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//데이터를 반환하는 컨트롤러로 사용
//JSON이나 XML형식의 데이터를 반환한다
//@Controller, @ResponseBody 두개의 어노테이션의 결합이다.
//@ResponseBody는 메서드의 반환값을 HTTP ResponseBody로 직렬화해 클라이언트에게 전달한다.
@RequestMapping("test")//test주소로 요청이 들어왔을때 현재 컨트롤러로 들어올 수 있게 해준다

public class TestController {
	
	@GetMapping("/testGetMapping")
	public String testController() {
		return "Hello World testGetMapping";
	}
}
