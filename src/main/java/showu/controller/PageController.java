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
 * 채혜송   2025.02.11    Board 페이지 추가
 * ========================================================
 */

package showu.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import showu.dto.CategoryDTO;
import showu.dto.PostDTO;
import showu.security.JwtTokenProvider;
import showu.service.CategoryService;
import showu.service.PostService;

@Controller
@RequiredArgsConstructor
public class PageController {

	private final PostService postService;
	private final CategoryService categoryService;
	private final JwtTokenProvider jwtTokenProvider;

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
	@GetMapping("/postdetail")
	public String getPostDetailPage() {
		return "postdetail";
	}

	@GetMapping("/tmp")
	public String getTmpPage() {
		return "postdetail";
	}
	@GetMapping("/postput")
	public String getPostPutPage() {
		return "postput";
	}
	
	@GetMapping("/board")
	public String getBoard(Model model, HttpServletRequest request) {
		
		String token = jwtTokenProvider.getToken(request);
		Object object = jwtTokenProvider.userIdFromToken(token);
		System.out.println(object);
		
		List<PostDTO> posts = postService.getAllPosts();	
		List<CategoryDTO> categories = categoryService.getAllCategory();	
		
		model.addAttribute("posts", posts);
		model.addAttribute("topFive", posts);
		model.addAttribute("categories", categories);
		
		return "board";
	}
	
}
