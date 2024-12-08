package khu.cloudproject.uwatch.video.domain.repository;

import khu.cloudproject.uwatch.video.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    @Query("SELECT k FROM Keyword k WHERE k.video.videoId = :videoId")
    Optional<Keyword> findByVideoId(@Param("videoId") String videoId);
}
