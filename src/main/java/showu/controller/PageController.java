/* PageController.java
 * showU Service - 자랑
 * 정적페이지 처리 컨트롤러
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : PageController 작성
 * 배희창   2025.02.09    S3TestPage 작성
 * 배희창   2025.02.09    POST 테스트 페이지 작성
 * ========================================================
 */

package showu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/posttest")
	public String getTestPage() {
		return "posttest";
	}
	
	@GetMapping("/postget")
	public String getPostTestPage() {
		return "postget";
	}
	@GetMapping("/postdelete")
	public String getPostDeletePage() {
		return "postdelete";
	}
	@GetMapping("/postone")
	public String getPostOnePage() {
		return "postone";
	}
	@GetMapping("/postput")
	public String getPostPutPage() {
		return "postput";
	}
	
}
