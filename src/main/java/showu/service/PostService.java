/* PostService.java
 * showU Service - 자랑
 * 게시물용 서비스. 테스트 코드 테스트 후 지워야함
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : PostService 작성
 * 배희창   2025.02.09    게시물 전체 조회, 생성, 삭제 구현
 * 배희창   2025.02.10    게시물 수정 구현
 * 배희창   2025.02.11    좋아요 구현
 * ========================================================
 */

package showu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import showu.dto.PostDTO;
import showu.dto.PostWithCommentsDTO;
import showu.entity.Category;
import showu.entity.Post;
import showu.entity.User;
import showu.repository.CategoryRepository;
import showu.repository.CommentRepository;
import showu.repository.PostRepository;
import showu.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final CommentRepository commentRepository;
	private final S3Service s3Service;
	
	public Post createDummyPost() {
		User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
		Category category = categoryRepository.findById(1L)
				.orElseThrow(() -> new RuntimeException("Category not found"));

		Post post = new Post();
		post.setUser(user);
		post.setCategory(category);
		post.setTitle("더미 제목");
		post.setContent("이것은 더미 데이터입니다.");
		post.setLink("https://example.com");
		post.setImageUrl("https://example.com/image.jpg");
		post.setPlike(0);

		return postRepository.save(post);
	}

	@Transactional
	public PostDTO createPost(PostDTO postDTO) {
		// User와 Category 엔티티 조회
		User user = userRepository.findById(postDTO.getUser().getId())
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));
		Category category = categoryRepository.findById(postDTO.getCategory().getId())
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카테고리 ID입니다."));

		// Post 엔티티 생성 및 저장
		Post post = Post.of(user, category, postDTO.getTitle(), postDTO.getContent(), postDTO.getLink(),
				postDTO.getImageUrl() // S3 업로드 후 받은 URL 저장
		);

		post = postRepository.save(post);
		return PostDTO.from(post);
	}

	// 모든 게시물 조회
	public List<PostDTO> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(PostDTO::from).collect(Collectors.toList());
	}

	// 게시물 삭제
	@Transactional
	public void deletePost(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + postId));

		// 📌 S3에서 파일 삭제
		s3Service.deleteS3File(post.getImageUrl());

		// 📌 DB에서 게시글 삭제
		postRepository.delete(post);
	}
	
	// 게시물 단일 조회
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        return PostDTO.from(post);
    }
    
    @Transactional
    public PostDTO updatePost(Long postId, PostDTO postDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // User와 Category 정보 확인
        User user = userRepository.findById(postDTO.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));
        Category category = categoryRepository.findById(postDTO.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카테고리 ID입니다."));

        // 기존 데이터 업데이트
        post.setUser(user);
        post.setCategory(category);
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setLink(postDTO.getLink());

        // 새 파일이 있을 경우에만 업데이트 (없으면 기존 이미지 유지)
        if (postDTO.getImageUrl() != null) {
            post.setImageUrl(postDTO.getImageUrl());
        }

        post = postRepository.save(post);
        return PostDTO.from(post);
    }


	@Transactional
	public PostWithCommentsDTO getPostWithComments(Long pid) {
		return postRepository.findById(pid)
				.map(PostWithCommentsDTO::from)
				.orElseThrow();
	}
	
	public int incrementLikes(Long pid) {
	    Optional<Post> postOptional = postRepository.findById(pid);
	    if (postOptional.isPresent()) {
	        Post post = postOptional.get();
	        post.setPlike(post.getPlike() + 1);
	        postRepository.save(post);
	        return post.getPlike();  // ✅ 변경된 좋아요 개수 반환
	    }
	    throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
	}
}
