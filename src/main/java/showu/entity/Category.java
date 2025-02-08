/* Category.java
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
 * 이홍비    2025.02.08    최초 작성 : DB 설계 기반 entity 작성
 * 이홍비    2025.02.08    추가 작성 : of() 추가
 * ========================================================
 */



package showu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(nullable = false, length = 20)
    private String cname;

    // 생성자
    private Category(String cname) {

        // 초기화
        this.cname = cname;
    }

    // static factory method - User 객체 생성
    public static Category of(String cname) {
        return new Category(cname);
    }

}
