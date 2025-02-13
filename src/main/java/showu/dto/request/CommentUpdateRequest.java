package showu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import showu.dto.CommentDTO;
import showu.dto.UserDTO;

@ToString
@Getter
@AllArgsConstructor
public class CommentUpdateRequest {
    private Long pid;
    private Long uid;
    private String content;

    public static CommentUpdateRequest of(Long pid, Long uid, String content) {
        return new CommentUpdateRequest(pid,uid,content);
    }

}
