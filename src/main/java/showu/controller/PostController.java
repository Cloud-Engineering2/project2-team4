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
 * 배희창   2025.02.10    게시물 수정 구현
 * 김예린   2025.02.11    게시물 댓글과 함께 조회 구현
 * 배희창   2025.02.11    좋아요 구현
 * 배희창   2025.02.11    post modify 작성
 * ========================================================
 */

package showu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import showu.dto.PostDTO;
import showu.dto.response.PostWithCommentsResponse;
import showu.entity.Post;
import showu.service.PostService;
import showu.service.S3Service;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;
	private final S3Service s3Service;

	@PatchMapping("/like/{postId}")
	public ResponseEntity<Map<String, Object>> likePost(@PathVariable Long postId) {
	    int updatedLikes = postService.incrementLikes(postId);

	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "좋아요가 증가되었습니다.");
	    response.put("likes", updatedLikes);

	    return ResponseEntity.ok(response);
	}

    // 추후 기능 고려
    @PatchMapping("/dislike/{postId}")
    public ResponseEntity<Map<String, Object>> dislikePost(@PathVariable Long postId) {
        int updatedLikes = postService.decrementLikes(postId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "좋아요가 감소되었습니다.");
        response.put("likes", updatedLikes);

        return ResponseEntity.ok(response);
    }
	
	// 게시물 업로드
    @ResponseBody
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
    @ResponseBody
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    
    // 게시물 삭제
    @ResponseBody
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글 삭제 완료");
    }
    
    // 게시물 업데이트
    @ResponseBody
    @PutMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable Long postId,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("post") PostDTO postDTO) {

        System.out.println("📌 받은 PostDTO 데이터: " + postDTO);
        System.out.println("📌 받은 파일: " + (file != null ? file.getOriginalFilename() : "파일 없음"));

        // 파일이 있으면 업로드 후 URL 설정
        if (file != null && !file.isEmpty()) {
            String imageUrl = s3Service.uploadS3File(file);
            postDTO.setImageUrl(imageUrl);
        }

        PostDTO updatedPost = postService.updatePost(postId, postDTO);
        System.out.println("📌 수정된 PostDTO 데이터: " + updatedPost);

        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/{postId}")
    public String getPostWithComments(
            @PathVariable Long postId,
            HttpServletRequest request,
            ModelMap map
    ) {

        PostWithCommentsResponse post = PostWithCommentsResponse.from(postService.getPostWithComments(postId));
//        Long userId = (Long) request.getAttribute("userId");

        map.addAttribute("post", post);
//        map.addAttribute("uid", userId);
        map.addAttribute("comments", post.getCommentResponse());

        return "/postdetail";
    }
    
    @GetMapping("/modify/{postId}")
    public String getPostForEdit(
            @PathVariable Long postId,
            HttpServletRequest request,
            ModelMap map
    ) {

    	PostWithCommentsResponse post = PostWithCommentsResponse.from(postService.getPostWithComments(postId));
    	
        map.addAttribute("post", post);
        
        return "/postmodify";
    }
    
}
