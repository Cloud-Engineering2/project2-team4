/* UserDTO.java
 * showU Service - 자랑
 * 사용자 관련 DTO
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창    2025.02.08   최초 작성 : User용 DTO
 * 이홍비    2025.02.08   entity 기반 DTO 추가 작성
 * 채혜송    2025.02.09   Jackson라이브러리 이슈로 NoArgsConstructor 추가
 * ========================================================
 */

package showu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import showu.entity.User;
import showu.entity.constant.UserRole;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id; // 사용자 고유 id
    private String userId; // 사용자 로그인 id
    private String userPw; // 비밀번호
    private String nickname; // 별명
    private UserRole userRole = UserRole.MEMBER; // default = MEMBER


    // static factory method - UserDTO 객체 생성
    public static UserDTO of(String userId, String pw, String nickname) {
        return UserDTO.of(userId, pw, nickname);
    }

    // static factory method - UserDTO 객체 생성
    public static UserDTO of(String id, String userId, String pw, String nickname, UserRole userRole) {
        return UserDTO.of(id, userId, pw, nickname, userRole);
    }

    // entity -> dto
    public static UserDTO from(User user) {
        return new UserDTO(
                user.getUid(),
                user.getUserId(),
                user.getUserPw(),
                user.getUserNickname(),
                user.getUserRole()
        );
    }

    // dto => entity
    public User toEntity() {
        return User.of(userId, userPw, nickname);
    }
}
