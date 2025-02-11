/* PostDTO.java
 * showU Service - 자랑
 * 게시 글 관련 DTO
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 이홍비    2025.02.08    최초 작성 : entity 기반 DTO 작성
 * 배희창    2025.02.09    NoArgsConstructor 추가
 * 배희창    2025.02.10    댓글부분 추가
 * ========================================================
 */

package showu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import showu.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
public class PostDTO {
    private Long id; // 게시 글 고유 id
    private UserDTO user; // 게시 글 작성자
    private CategoryDTO category; // 게시 글 유형
    private String title; // 제목
    private String content; // 내용
    private String link; // 링크
    private String imageUrl; // 파일 url
    private int likes; // 좋아요 수
    private LocalDateTime createdAt; // 최초 작성 날짜
    private LocalDateTime modifiedAt; // 최종 수정 날짜
    private List<CommentDTO> comments; // 댓글 목록 추가

    // static factory method - PostDTO 객체 생성
    public static PostDTO of(UserDTO user, CategoryDTO category, String title, String content, String link, String imageUrl, int likes) {
        return PostDTO.of(user, category, title, content, link, imageUrl, likes);
    }

    public static PostDTO of(Long id, UserDTO user, CategoryDTO category, String title, String content, String link, String imageUrl, int likes, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return PostDTO.of(id, user, category, title, content, link, imageUrl, likes, createdAt, modifiedAt);
    }

    // entity -> dto
    public static PostDTO from(Post post) {
        return new PostDTO(
                post.getPid(),
                UserDTO.from(post.getUser()),
                CategoryDTO.from(post.getCategory()),
                post.getTitle(),
                post.getContent(),
                post.getLink(),
                post.getImageUrl(),
                post.getPlike(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                post.getComments().stream()
                        .map(CommentDTO::from)
                        .collect(Collectors.toList())
        );
    }
    // dto -> entity
    public Post toEntity() {
        return Post.of(user.toEntity(), category.toEntity(), title, content, link, imageUrl, likes, createdAt, modifiedAt);
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
