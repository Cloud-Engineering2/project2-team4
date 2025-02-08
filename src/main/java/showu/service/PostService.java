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
 * ========================================================
 */

package showu.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import showu.entity.Category;
import showu.entity.Post;
import showu.entity.User;
import showu.repository.CategoryRepository;
import showu.repository.PostRepository;
import showu.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    
    public Post createDummyPost() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));
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

}
