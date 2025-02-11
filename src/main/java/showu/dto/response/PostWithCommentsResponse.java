package showu.dto.response;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import showu.dto.CommentDTO;
import showu.dto.PostWithCommentsDTO;

@ToString
@Getter
@AllArgsConstructor
public class PostWithCommentsResponse {
    private Long pid;
    private Long uid;
    private String title;
    private String content;
    private String imageURL;
    private String link;
    private String createdBy;
    private LocalDateTime createdAt;
    private Set<CommentResponse> commentResponse;
    private CategoryResponse category;
    public static PostWithCommentsResponse of(Long pid, Long uid, String title, String content, String imageURL, String link, String createdBy, LocalDateTime createdAt, Set<CommentResponse> commentResponse, CategoryResponse category) {
        return new PostWithCommentsResponse(pid, uid, title, content, imageURL, link, createdBy, createdAt, commentResponse, category);
    }

    public static PostWithCommentsResponse from(PostWithCommentsDTO postWithCommentsDTO) {
        return PostWithCommentsResponse.of(
            postWithCommentsDTO.getId(),
            postWithCommentsDTO.getUserDTO().getId(),
            postWithCommentsDTO.getTitle(),
            postWithCommentsDTO.getContent(),
            postWithCommentsDTO.getImageURL(),
            postWithCommentsDTO.getLink(),
            postWithCommentsDTO.getUserDTO().getUserId(),
            postWithCommentsDTO.getCreatedAt(),
            getCommentResponses(postWithCommentsDTO.getCommentDTOs()),
            postWithCommentsDTO.getCategoryDTO() != null ? CategoryResponse.from(postWithCommentsDTO.getCategoryDTO()) : null // 📌 추가
        );
    }


    private static Set<CommentResponse> getCommentResponses(Set<CommentDTO> commentDTOs) {
        // PostCommentDto 반복 -> Response 변경 -> 중복 제거??
        Map<Long, CommentResponse> map = commentDTOs.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toMap(CommentResponse::getCmid, Function.identity()));

        // id값으로 중복을 제거 -> return
        return map.values().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator
                                        .comparing(CommentResponse::getCreatedAt)
                                        .thenComparingLong(CommentResponse::getCmid)
                                )
                        )
                );

    }

}


