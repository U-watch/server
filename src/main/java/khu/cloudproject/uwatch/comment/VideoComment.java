package khu.cloudproject.uwatch.comment;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.global.enums.BlockedStatus;
import khu.cloudproject.uwatch.global.enums.CommentCategory;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import khu.cloudproject.uwatch.video.Video;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "video_comment")
public class VideoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "analyzed_at")
    private LocalDateTime analyzedAt;

    @Enumerated(EnumType.STRING)
    private Sentiment sentiment;

    @Enumerated(EnumType.STRING)
    private CommentCategory category;

    @Column(name = "category_rate")
    private Integer categoryRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "blocked_status")
    private BlockedStatus blockedStatus;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Getters, Setters, Constructors
}
