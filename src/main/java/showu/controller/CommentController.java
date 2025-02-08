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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import showu.entity.Comment;
import showu.service.CommentService;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/testdata")
    public ResponseEntity<Comment> createTestComment() {
        Comment dummyComment = commentService.createDummyComment();
        return ResponseEntity.ok(dummyComment);
    }
}
