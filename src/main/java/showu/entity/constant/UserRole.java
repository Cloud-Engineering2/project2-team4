/* UserRole.java
 * showU Service - 자랑
 * 사용자 역할
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 이홍비    2025.02.08    최초 작성 : 사용자 역할 열거형
 * 이홍비    2025.02.09    값 부여 + 생산자 추가 + annotation 설정
 * ========================================================
 */

package showu.entity.constant;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@ToString
@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public static UserRole getInstance(String userRole) { // userRole 의 값을 문자열로 찾는 함수

        return Arrays.stream(UserRole.values()) // UserRole 값 -> 배열 -> 스트림
                .filter(type -> type.name().equals(userRole)) // userRole 과 type(UserRole) 값 일치 여부로 정제
                .findFirst() // 정제된 것에서 첫 번째 요소 반환
                .orElseThrow(); // 값 x => 예외
    }



}
