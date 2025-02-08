/* Post.java
 * showU Service - 자랑
 * 게시 글 관련 entity
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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid; // 게시 글 고유 id

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private User user; // 작성자

    @ManyToOne
    @JoinColumn(name = "cid", nullable = false)
    private Category category; // 게시 글 유형

    @Column(nullable = false, length = 50)
    private String title; // 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용

    @Column(columnDefinition = "TEXT")
    private String link; // 링크

    @Column(columnDefinition = "TEXT")
    private String imageUrl; // 파일 링크

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int plike; // 좋아요 수

    @CreationTimestamp
    private LocalDateTime createdDate; // 최초 작성 일자

    @UpdateTimestamp
    private LocalDateTime modifiedDate; // 최종 수정 일자


    // 생성자
    public Post(User user, Category category, String title, String content, String link, String imageUrl) {

        // 초기화
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.link = link;
        this.imageUrl = imageUrl;
        this.plike = 0;
    }

    public Post(User user, Category category, String title, String content, String link, String imageUrl, int plike, LocalDateTime createdDate, LocalDateTime modifiedDate) {

        // 초기화
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.link = link;
        this.imageUrl = imageUrl;
        this.plike = plike;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


    // static factory method - Post 객체 생성
    public static Post of(User user, Category category, String title, String content, String link, String imageUrl) {
        return new Post(user, category, title, content, link, imageUrl);
    }

    public static Post of(User user, Category category, String title, String content, String link, String imageUrl, int plike, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        return new Post(user, category, title, content, link, imageUrl, plike, createdDate, modifiedDate);
    }

}

