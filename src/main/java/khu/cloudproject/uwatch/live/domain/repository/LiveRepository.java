package khu.cloudproject.uwatch.live.domain.repository;

import khu.cloudproject.uwatch.live.domain.Live;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveRepository extends JpaRepository<Live, Long> {
}
