package showu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import showu.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}

