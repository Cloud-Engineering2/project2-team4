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
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static CommentResponse of(Long cmid, String createdBy, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new CommentResponse(cmid, createdBy, content, createdAt, modifiedAt);
    }

    public static CommentResponse from(CommentDTO commentDTO) {
        return CommentResponse.of(
                commentDTO.getId(),
                commentDTO.getContent(),
                commentDTO.getUserDTO().getUserId(),
                commentDTO.getCreatedAt(),
                commentDTO.getModifiedAt()
        );
    }
}
