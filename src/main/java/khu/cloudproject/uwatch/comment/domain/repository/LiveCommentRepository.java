package khu.cloudproject.uwatch.comment.domain.repository;

import khu.cloudproject.uwatch.comment.domain.LiveComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveCommentRepository extends JpaRepository<LiveComment, Long> {
}
