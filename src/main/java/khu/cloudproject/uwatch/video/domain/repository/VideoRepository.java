package khu.cloudproject.uwatch.video.domain.repository;

import khu.cloudproject.uwatch.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    @Query("SELECT v.videoId FROM Video v WHERE v.channel.channelId = :channelId")
    List<String> findVideoIdsByChannelId(@Param("channelId") String channelId);
}

