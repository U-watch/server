package khu.cloudproject.uwatch.comment.domain;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.global.enums.BlockedStatus;
import khu.cloudproject.uwatch.global.enums.CommentCategory;
import khu.cloudproject.uwatch.global.enums.PositiveStatus;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import khu.cloudproject.uwatch.video.domain.Video;
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

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_profile_image_url")
    private String authorProfileImageUrl;

    @Column(name = "comment_text")
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
    private PositiveStatus positiveStatus;

    @Enumerated(EnumType.STRING)
    private CommentCategory category;

    @Enumerated(EnumType.STRING)
    private Sentiment sentiment;

    @Enumerated(EnumType.STRING)
    @Column(name = "blocked_status")
    private BlockedStatus blockedStatus = BlockedStatus.NOT_BLOCKED;

    @ManyToOne
    @JoinColumn(name = "video_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Video video;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Author author;

    // Getters, Setters, Constructors
}
