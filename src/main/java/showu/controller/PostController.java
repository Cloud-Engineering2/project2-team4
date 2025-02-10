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
 * 배희창   2025.02.09    게시물 전체 조회, 생성, 삭제 구현
 * ========================================================
 */

package showu.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import showu.dto.PostDTO;
import showu.entity.Post;
import showu.service.PostService;
import showu.service.S3Service;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;
	private final S3Service s3Service;

	@GetMapping("/test")
	public String test() {
		return "post";
	}

	@PostMapping("/testdata")
	public ResponseEntity<Post> createTestData() {
		Post dummyPost = postService.createDummyPost();
		return ResponseEntity.ok(dummyPost);
	}
	
	// 게시물 업로드
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createPost(
	        @RequestPart("file") MultipartFile file,
	        @RequestPart("post") PostDTO postDTO) {

	    System.out.println("📌 받은 PostDTO 데이터: " + postDTO);
	    System.out.println("📌 받은 파일: " + file.getOriginalFilename());

	    String imageUrl = s3Service.uploadS3File(file);
	    postDTO.setImageUrl(imageUrl);

	    PostDTO savedPost = postService.createPost(postDTO);

	    System.out.println("📌 저장된 PostDTO 데이터: " + savedPost);

	    return ResponseEntity.ok(savedPost);
	}
	
	// 전체 게시물 조회
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    
    // 게시물 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글 삭제 완료");
    }
    
    // 단일 게시물 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
    	PostDTO post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }
    
//    @PutMapping("{postId}")
    
}
