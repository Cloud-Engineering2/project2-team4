/* UserRoleAttributeConverter.java
 * showU Service - 자랑
 * 열거형 UserRole <=> DB 문자열 값
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 이홍비    2025.02.08    최초 작성 : 컨버터 작성
 * ========================================================
 */

package showu.common.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import showu.entity.constant.UserRole;

@Convert
public class UserRoleAttributeConverter implements AttributeConverter<UserRole, String> {

    // 열거형 UserRole => DB 문자열
    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return attribute.getUserRole();
    }

    // DB 문자열 => 열거형 UserRole
    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return UserRole.getInstance(dbData);
    }
}