package showu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* PageController.java
* 정적 html 서빙용 컨트롤러
* 작성자 : lion4 (배희창)
* 최종 수정 날짜 : 2025-02-08
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 작업자       날짜       수정 / 보완 내용
* ========================================================
* ㅇㅇㅇ    2024.12.10    ~~~~~~~~~~~~~~~~~
* ㅇㅇㅇ    2024.12.11    ~~~~~~~~~~~~~~~~~
* ========================================================
*/ 

@Controller
public class PageController {
	

	@GetMapping("/")
	public String getMainPage() {
		return "main";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String getSignupPage() {
		return "signup";
	}
	
}
