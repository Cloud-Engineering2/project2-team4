package showu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import showu.dto.CommentDTO;
import showu.dto.UserDTO;

@ToString
@Getter
@AllArgsConstructor
public class CommentRequest {
    private Long pid;
    private Long uid;
    private String content;

    public static CommentRequest of(Long pid, Long uid, String content) {
        return new CommentRequest(pid,uid,content);
    }

    public CommentDTO toDto(UserDTO userDto) {
        return CommentDTO.of(
                pid,
                userDto,
                content
        );
    }

}
