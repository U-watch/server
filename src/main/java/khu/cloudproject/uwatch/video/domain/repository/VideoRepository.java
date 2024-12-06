package khu.cloudproject.uwatch.video.domain.repository;

import khu.cloudproject.uwatch.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
