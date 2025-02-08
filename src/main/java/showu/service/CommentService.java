package showu.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import showu.entity.Comment;
import showu.entity.Post;
import showu.entity.User;
import showu.repository.CommentRepository;
import showu.repository.PostRepository;
import showu.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment createDummyComment() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent("이것은 더미 댓글입니다.");

        return commentRepository.save(comment);
    }
}

