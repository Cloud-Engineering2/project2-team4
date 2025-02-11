package showu.dto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import showu.entity.Post;

@ToString
@Getter
@AllArgsConstructor
public class PostWithCommentsDTO {
    private Long id;
    private String title;
    private String content;
    private String imageURL;
    private String link;
    private UserDTO userDTO;
    private Set<CommentDTO> commentDTOs;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static PostWithCommentsDTO of(Long id, String title, String content, String imageURL, String link, UserDTO userDTO,
                                         Set<CommentDTO> commentDTOs, LocalDateTime createdDate,
                                         LocalDateTime modifiedDate) {
        return new PostWithCommentsDTO(id, title, content, imageURL, link, userDTO, commentDTOs, createdDate, modifiedDate);
    }

    public static PostWithCommentsDTO from(Post post) {
        return new PostWithCommentsDTO(
                post.getPid(),
                post.getTitle(),
                post.getContent(),
                post.getImageUrl(),
                post.getLink(),
                UserDTO.from(post.getUser()),
                post.getComments().stream()
                        .map(CommentDTO::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                post.getCreatedDate(),
                post.getModifiedDate());
    }

}

