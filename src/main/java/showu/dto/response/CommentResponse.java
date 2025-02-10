package showu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import showu.dto.CommentDTO;
import showu.dto.request.CommentRequest;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long cmid;
    private Long pid;
    private Long uid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static CommentResponse of(Long cmid, Long pid, Long uid, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new CommentResponse(cmid, pid, uid, content, createdAt, modifiedAt);
    }

    public static CommentResponse from(CommentDTO commentDTO) {
        return CommentResponse.of(
                commentDTO.getId(),
                commentDTO.getPid(),
                commentDTO.getUserDTO().getId(),
                commentDTO.getContent(),
                commentDTO.getCreatedAt(),
                commentDTO.getModifiedAt()
        );
    }
}
