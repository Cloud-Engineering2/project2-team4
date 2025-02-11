/* CommentService.java
 * showU Service - 자랑
 * 댓글용 서비스. 테스트코드 테스팅 후 지워야함
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.10
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : CommentService 작성
 * 김예린   2025.02.10    CommentService 메소드 구현
 * ========================================================
 */

package showu.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import showu.dto.CommentDTO;
import showu.dto.UserDTO;
import showu.dto.request.CommentRequest;
import showu.dto.request.CommentUpdateRequest;
import showu.entity.Comment;
import showu.entity.Post;
import showu.entity.User;
import showu.repository.CommentRepository;
import showu.repository.PostRepository;
import showu.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentDTO registerComment(CommentRequest commentRequest) {
        User user = userRepository.getReferenceById(commentRequest.getUid());

        Post post = postRepository.getReferenceById(commentRequest.getPid());

        CommentDTO commentDTO = commentRequest.toDto(UserDTO.from(user));
        Comment comment = commentDTO.toEntity(post, user, LocalDateTime.now(), LocalDateTime.now());
        Comment registeredComment = commentRepository.save(comment);

        return CommentDTO.from(registeredComment);
    }



    public CommentDTO updateComment(Long cmid, CommentUpdateRequest commentUpdateRequest) {
        Long userId = commentUpdateRequest.getUid();
        User user = userRepository.getReferenceById(userId);
        Comment comment = commentRepository.getReferenceById(cmid);
        if (!userId.equals(comment.getUser().getUid())) {
            throw new NoSuchElementException("해당 댓글을 작성한 유저가 아닙니다.");
        }

        comment.updateContentAndModifiedDate(commentUpdateRequest.getContent(),LocalDateTime.now());
        Comment updatedComment = commentRepository.save(comment);

        return CommentDTO.from(updatedComment);
    }

    @Transactional
    public void deleteComment(Long cmid, Long uid) {
        commentRepository.deleteByCmidAndUser_Uid(cmid,uid);
    }
}

