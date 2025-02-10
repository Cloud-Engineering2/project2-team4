/* CommentController.java
 * showU Service - 자랑
 * 댓글 api 처리 컨트롤러
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : CommentController 작성
 * ========================================================
 */

package showu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import showu.dto.UserDTO;
import showu.dto.request.CommentRequest;
import showu.entity.Comment;
import showu.entity.constant.UserRole;
import showu.service.CommentService;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> registerComment(@RequestBody CommentRequest commentRequest) {

        // 로그인 가정
        UserDTO userDto = UserDTO.of(1L,
                "admin",
                "admin",
                "admin@showu.store",
                UserRole.ADMIN);



        Comment dummyComment = commentService.registerComment(commentRequest.toDto(userDto));
        return ResponseEntity.ok(dummyComment);
    }

    @GetMapping("/{pid}")
    public ResponseEntity<Comment> getAllComments(@PathVariable Long pid) {
        Comment dummyComment = commentService.getAllComments();
        return ResponseEntity.ok(dummyComment);
    }

    @PutMapping("/{cmid}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long cmid) {
        Comment dummyComment = commentService.updateComment();
        return ResponseEntity.ok(dummyComment);
    }

    @DeleteMapping("/{cmid}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long cmid) {
        Comment dummyComment = commentService.deleteComment();
        return ResponseEntity.ok(dummyComment);
    }
}
