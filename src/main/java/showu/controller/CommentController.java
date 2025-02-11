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

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import showu.dto.CommentDTO;
import showu.dto.UserDTO;
import showu.dto.request.CommentRequest;
import showu.dto.response.CommentResponse;
import showu.entity.Comment;
import showu.entity.constant.UserRole;
import showu.service.CommentService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> registerComment(@RequestBody CommentRequest commentRequest) {

        // 로그인 가정
//        UserDTO userDTO = UserDTO.of(2L,
//                "admin",
//                "admin",
//                "admin@showu.store",
//                UserRole.ADMIN);


        CommentDTO commentDTO = commentService.registerComment(commentRequest);

        return ResponseEntity.ok(CommentResponse.from(commentDTO));
    }


    @PutMapping("/{cmid}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long cmid, @RequestBody CommentRequest commentRequest) {
        // 로그인 가정
        UserDTO userDTO = UserDTO.of(2L,
                "admin",
                "admin",
                "admin@showu.store",
                UserRole.ADMIN);

        CommentDTO commentDTO = commentService.updateComment(cmid, commentRequest.toDto(userDTO));
        return ResponseEntity.ok(CommentResponse.from(commentDTO));
    }

    @DeleteMapping("/{cmid}")
    public ResponseEntity<String> deleteComment(@PathVariable Long cmid, @RequestBody UserDTO userDto) {
        commentService.deleteComment(cmid, userDto.getId());
        return ResponseEntity.ok("comment deleted successfully");
    }
}
