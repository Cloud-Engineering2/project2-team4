/* PostController.java
 * showU Service - 자랑
 * 게시물 api 처리 컨트롤러
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : PostController 작성
 * ========================================================
 */

package showu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import showu.entity.Post;
import showu.service.PostService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;
	
	@GetMapping("/test")
	public String test() {
		return "post";
	}
	
	@PostMapping("/testdata")
	public ResponseEntity<Post> createTestData() {
		Post dummyPost = postService.createDummyPost();
		return ResponseEntity.ok(dummyPost);
	}
	
}
