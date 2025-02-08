/* Comment.java
 * showU Service - 자랑
 * 댓글 관련 entity
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창    2025.02.08    최초 작성 : DB 설계 기반 entity 작성
 * 이홍비    2025.02.08    of() 추가
 * ========================================================
 */



package showu.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmid; // 댓글 고유 id

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private Post post; // 해당 게시 글

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private User user; // 댓글 작성자

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용

    @CreationTimestamp
    private LocalDateTime createdDate; // 최초 작성 일자

    @UpdateTimestamp
    private LocalDateTime modifiedDate; // 최종 수정 일자


    // 생성자
    private Comment(Post post, User user, String content) {

        // 초기화
        this.post = post;
        this.user = user;
        this.content = content;
    }

    private Comment(Long id, Post post, User user, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {

        // 초기화
        this.cmid = id;
        this.post = post;
        this.user = user;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


    // static factory method - Comment 객체 생성
    public static Comment of(Post post, User user, String content) {
        return new Comment(post, user, content);
    }

    public static Comment of(Long id, Post post, User user, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        return new Comment(id, post, user, content, createdDate, modifiedDate);
    }


}
