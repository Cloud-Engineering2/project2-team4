/* User.java
 * showU Service - 자랑
 * 게시 글 유형 관련 entity
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

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import showu.entity.constant.UserRole;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(unique = true, nullable = false, length = 20)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    // 생성자
    private User(String userId, String userPw) {

        // 초기화
        this.userId = userId;
        this.userPw = userPw;
        this.userRole = UserRole.MEMBER;// default value = member
    }

    // static factory method - User 객체 생성
    public static User of(String userId, String userPw) {
        return new User(userId, userPw);
    }
}

