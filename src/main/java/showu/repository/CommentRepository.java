/* CommentRepository.java
 * showU Service - 자랑
 * 댓글 레파지토리
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : CommentRepository작성
 * ========================================================
 */

package showu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import showu.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByCmidAndUser_Uid(Long cmid, Long uid);
}

