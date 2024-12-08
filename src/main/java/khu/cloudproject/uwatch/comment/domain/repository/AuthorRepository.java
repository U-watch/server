package khu.cloudproject.uwatch.comment.domain.repository;

import khu.cloudproject.uwatch.comment.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
