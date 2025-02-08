/* CategoryDTO.java
 * showU Service - 자랑
 * 게시 글 유형 관련 DTO
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 이홍비    2025.02.08    최초 작성 : entity 기반 DTO 작성
 * ========================================================
 */

package showu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import showu.entity.Category;

@AllArgsConstructor
@ToString
@Getter
public class CategoryDTO {
    private long id; // 카테고리 고유 id
    private String name; // 게시 글 유형명


    // static factory method - CategoryDTO 객체 생성
    public static CategoryDTO of(String name) {
        return CategoryDTO.of(name);
    }

    public static CategoryDTO of(Long id, String name) {
        return CategoryDTO.of(id, name);
    }

    // entity -> dto
    public static CategoryDTO from(Category category) {
        return new CategoryDTO(
                category.getCid(),
                category.getCname()
        );
    }

    // dto -> entity
    public Category toEntity() {
        return Category.of(name);
    }

}
