package khu.cloudproject.uwatch.comment.domain.repository;

import jakarta.transaction.Transactional;
import khu.cloudproject.uwatch.comment.domain.VideoComment;
import khu.cloudproject.uwatch.global.enums.BlockedStatus;
import khu.cloudproject.uwatch.global.enums.CommentCategory;
import khu.cloudproject.uwatch.global.enums.PositiveStatus;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("""
                SELECT vc.sentiment, COUNT(vc)
                FROM VideoComment vc
                WHERE vc.video.videoId = :videoId
                GROUP BY vc.sentiment
            """)
    List<Object[]> countSentimentsByVideoId(@Param("videoId") String videoId);

    @Query("""
                SELECT vc.authorName, vc.authorProfileImageUrl, COUNT(vc)
                FROM VideoComment vc
                WHERE vc.video.channel.channelId = :channelId
                GROUP BY vc.authorName, vc.authorProfileImageUrl
                ORDER BY COUNT(vc) DESC
            """)
    List<Object[]> countComments(@Param("channelId") String channelId);

    @Query("""
             SELECT vc
             FROM VideoComment vc
             WHERE vc.video.channel.channelId = :channelId
             AND vc.authorName = :authorName
             ORDER BY vc.publishedAt DESC
            """)
    List<VideoComment> findByChannelIdAndAuthorName(@Param("channelId") String channelId, @Param("authorName") String authorName);

    @Query("""
            SELECT vc
            FROM VideoComment vc
            WHERE vc.video.videoId = :videoId
            AND vc.commentText LIKE %:keyword%
            """)
    List<VideoComment> findDetailedCommentsByVideoIdAndKeyword(@Param("videoId") String videoId, @Param("keyword") String keyword);

    @Query("SELECT COUNT(vc) FROM VideoComment vc WHERE vc.video.videoId = :videoId AND vc.positiveStatus = :status")
    long countByVideoIdAndPositiveStatus(@Param("videoId") String videoId, @Param("status") PositiveStatus status);

    @Query("""
                SELECT vc.category, COUNT(vc)
                FROM VideoComment vc
                WHERE vc.video.videoId = :videoId
                GROUP BY vc.category
            """)
    List<Object[]> countCategoriesByVideoId(@Param("videoId") String videoId);

    @Query("""
            SELECT vc 
            FROM VideoComment vc 
            JOIN FETCH vc.video 
            WHERE vc.video.videoId = :videoId
            AND vc.blockedStatus = khu.cloudproject.uwatch.global.enums.BlockedStatus.NOT_BLOCKED
            """)
    List<VideoComment> findByVideoIdAndNotBlocked(@Param("videoId") String videoId);

    @Query("""
            SELECT vc
            FROM VideoComment vc
            WHERE vc.video.videoId = :videoId
            AND vc.sentiment = :sentiment
            AND vc.blockedStatus = khu.cloudproject.uwatch.global.enums.BlockedStatus.NOT_BLOCKED
            """)
    List<VideoComment> findByVideoIdAndSentimentAndNotBlocked(
            @Param("videoId") String videoId,
            @Param("sentiment") Sentiment sentiment);

    @Query("""
            SELECT 
                CONCAT(DATE_FORMAT(vc.publishedAt, '%Y-%m-%d %H:'), 
                       CASE WHEN MINUTE(vc.publishedAt) < 30 THEN '00:00' ELSE '30:00' END) AS intervalStart,
                COUNT(vc) AS commentCount
            FROM VideoComment vc
            WHERE vc.video.videoId = :videoId
            GROUP BY intervalStart
            ORDER BY intervalStart ASC
            """)
    List<Object[]> findCommentTrendsBy30MinuteInterval(@Param("videoId") String videoId);

    @Query("""
            SELECT vc
            FROM VideoComment vc
            WHERE vc.video.videoId = :videoId
            AND vc.category = :category
            AND vc.blockedStatus = khu.cloudproject.uwatch.global.enums.BlockedStatus.NOT_BLOCKED
            """)
    List<VideoComment> findByVideoIdAndCategoryAndNotBlocked(
            @Param("videoId") String videoId,
            @Param("category") CommentCategory category);

    @Modifying
    @Transactional
    @Query("""
            UPDATE VideoComment vc
            SET vc.blockedStatus = :blockedStatus
            WHERE vc.author.authorId = :authorId
            """)
    int updateBlockedStatusByAuthorId(@Param("authorId") String authorId, @Param("blockedStatus") BlockedStatus blockedStatus);
}
