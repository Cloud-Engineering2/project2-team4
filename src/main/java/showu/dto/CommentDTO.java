/* CommentDTO.java
 * showU Service - 자랑
 * 댓글 관련 DTO
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 이홍비    2025.02.08    최초 작성 : entity 기반 DTO 작성
 * ========================================================
 */

package showu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import showu.entity.Comment;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class CommentDTO {
    private Long id; // 댓글 고유 id
    private PostDTO post; // 해당 게시 글
    private UserDTO user; // 댓글 작성자
    private String content; // 내용
    private LocalDateTime createdAt; // 최초 작성 날짜
    private LocalDateTime modifiedAt; // 최종 수정 날짜

    // static factory method - CommentDTO 객체 생성
    public static CommentDTO of(PostDTO post, UserDTO user, String content) {
        return CommentDTO.of(post, user, content);
    }

    public static CommentDTO of(Long id, PostDTO post, UserDTO user, String content, LocalDateTime createdAt) {
        return CommentDTO.of(id, post, user, content, createdAt);
    }

    // entity -> dto
    public static CommentDTO from(Comment comment) {
        return new CommentDTO(
                comment.getCmid(),
                PostDTO.from(comment.getPost()),
                UserDTO.from(comment.getUser()),
                comment.getContent(),
                comment.getCreatedDate(),
                comment.getModifiedDate()
        );
    }

    // dto -> entity
    public Comment toEntity() {
        return Comment.of(id, post.toEntity(), user.toEntity(), content, createdAt, modifiedAt);
    }

}
