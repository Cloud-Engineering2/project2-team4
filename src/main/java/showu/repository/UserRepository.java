/* UserRepository.java
 * showU Service - 자랑
 * 로그인 처리용 레파지토리
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.08    최초 작성 : UserRepository 작성
 * ========================================================
 */

package showu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import showu.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUserId(String userId);
	Optional<User> findByUserId(String userId);
}
