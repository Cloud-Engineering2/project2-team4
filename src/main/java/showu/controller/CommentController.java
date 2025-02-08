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
