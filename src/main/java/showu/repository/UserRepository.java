package showu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import showu.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUserId(String userId);
}
