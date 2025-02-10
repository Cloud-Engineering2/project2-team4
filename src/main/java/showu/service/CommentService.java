/* CommentService.java
 * showU Service - 자랑
 * 댓글용 서비스. 테스트코드 테스팅 후 지워야함
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : CommentService 작성
 * ========================================================
 */

package showu.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import showu.dto.CommentDTO;
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

    public Comment registerComment(CommentDTO commentDTO) {
        User user = userRepository.getReferenceById(commentDTO.getUserDTO().getId());

        Post post = postRepository.getReferenceById(commentDTO.getPid());

        Comment comment = commentDTO.toEntity(post, user);

        return commentRepository.save(comment);
    }

    public Comment getAllComments() {
    }

    public Comment updateComment() {
    }

    public Comment deleteComment() {
    }
}

