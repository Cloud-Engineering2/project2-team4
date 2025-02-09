/* User.java
 * showU Service - 자랑
 * 사용자 관련 entity
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
 * 이홍비    2025.02.08    별명 필드 추가 (nickname)
 * 이홍비    2025.02.09    비밀번호 길이 + 별명 필드 유니크 설정
 * 이홍비    2025.02.09    UserRole 설정 변경
 * ========================================================
 */


package showu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import showu.common.utils.UserRoleAttributeConverter;
import showu.entity.constant.UserRole;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid; // 사용자 고유 id

    @Column(unique = true, nullable = false, length = 20)
    private String userId; // 로그인 시 id

    @Column(nullable = false, length = 61)
    private String userPw; // 비밀번호

    @Column(unique = true, nullable = false, length = 20)
    private String userNickname; // 별명

    @Column(nullable = false, length = 20)
    @Convert(converter = UserRoleAttributeConverter.class)
    private UserRole userRole; // 역할 (권한)


    // 생성자
    private User(String userId, String userPw, String userNickname) {

        // 초기화
        this.userId = userId;
        this.userPw = userPw;
        this.userNickname = userNickname;
        this.userRole = UserRole.MEMBER;// default value = member
    }

    // static factory method - User 객체 생성
    public static User of(String userId, String userPw, String userNickname) {
        return new User(userId, userPw, userNickname);
    }
}

