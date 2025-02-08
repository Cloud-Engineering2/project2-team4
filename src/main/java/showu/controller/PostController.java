package showu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import showu.entity.Post;
import showu.service.PostService;

/* PostController.java
* Post 데이터 서빙 컨트롤러
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

@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostService postService;
	
	@GetMapping("/api/post/test")
	public String test() {
		return "post";
	}
	
	@PostMapping("/api/post/testdata")
	public ResponseEntity<Post> createTestData() {
		Post dummyPost = postService.createDummyPost();
		return ResponseEntity.ok(dummyPost);
	}
	
}
