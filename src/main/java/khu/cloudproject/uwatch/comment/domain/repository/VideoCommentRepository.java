package khu.cloudproject.uwatch.comment.domain.repository;

import khu.cloudproject.uwatch.comment.domain.VideoComment;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoCommentRepository extends JpaRepository<VideoComment, Long> {

    @Query("SELECT COUNT(vc) FROM VideoComment vc WHERE vc.video.videoId = :videoId")
    long countByVideoId(@Param("videoId") String videoId);

    @Query("SELECT COUNT(vc) FROM VideoComment vc WHERE vc.video.videoId IN :videoIds")
    long countByVideoIds(@Param("videoIds") List<String> videoIds);

    @Query("SELECT vc.sentiment, COUNT(vc) FROM VideoComment vc WHERE vc.video.videoId IN :videoIds GROUP BY vc.sentiment")
    List<Object[]> countSentimentsByVideoIds(@Param("videoIds") List<String> videoIds);

    @Query("SELECT COUNT(vc) FROM VideoComment vc WHERE vc.video.videoId IN :videoIds AND vc.sentiment = :sentiment")
    long countByVideoIdsAndSentiment(@Param("videoIds") List<String> videoIds, @Param("sentiment") Sentiment sentiment);

    @Query("SELECT vc.sentiment, COUNT(vc) FROM VideoComment vc WHERE vc.video.videoId = :videoId GROUP BY vc.sentiment")
    List<Object[]> countSentimentsByVideoId(@Param("videoId") String videoId);

    @Query("""
                SELECT vc.authorName, vc.authorProfileImageUrl, COUNT(vc)
                FROM VideoComment vc
                WHERE vc.video.channel.channelId = :channelId
                GROUP BY vc.authorName, vc.authorProfileImageUrl
                ORDER BY COUNT(vc) DESC
            """)
    List<Object[]> countComments(@Param("channelId") String channelId);
}
