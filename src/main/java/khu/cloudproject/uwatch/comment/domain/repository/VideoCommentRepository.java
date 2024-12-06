package khu.cloudproject.uwatch.comment.domain.repository;

import khu.cloudproject.uwatch.comment.domain.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCommentRepository extends JpaRepository<VideoComment, Long> {
}
