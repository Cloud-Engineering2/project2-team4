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
