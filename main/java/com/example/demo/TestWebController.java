package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")	//http://localhost:8090/test
public class TestWebController {
	
	//http:/localhost:8090/test/hello
	@GetMapping("/hello")
	public String hello() {
		return "hello";	//WEB-INF/views/hello.jsp	hello.jsp 이동
	}
	
	//http://localhost:8090/test/hello2?msg=안녕하세요?	= 쿼리스트링 =>@RquestParam으로씀 => request.getparam이랑 같음 
	// 매우 중요
	@GetMapping("/hello2")
	@ResponseBody
	public String hello2(@RequestParam(value="msg", required=false) String msg) {
		return msg;
	}
	
	//http://localhost:8090/test/hello3/안녕하세요  = 경로 @PathVariable
	@GetMapping("/hello3/{msg}")  //경로에 있는 값으로 지정
	public String hello3(@PathVariable String msg, Model m) {
		m.addAttribute("msg", msg);	//모델 스코프에 정보 전달  =>  /test/hello3/안녕하세요 하면 model scope에 있는 메시지 : ${msg} 에 안녕하세요 출력
		return "hello";
	}
}
