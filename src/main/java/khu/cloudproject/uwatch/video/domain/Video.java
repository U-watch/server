package khu.cloudproject.uwatch.video.domain;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.channel.domain.Channel;
import khu.cloudproject.uwatch.comment.domain.VideoComment;
import khu.cloudproject.uwatch.global.enums.AnalyzingStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_id")
    private String videoId;

    private String title;

    private String thumbnail;

    private String duration;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "dislike_count")
    private Integer dislikeCount;

    @Column(name = "comment_count")
    private Integer commentCount;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "analyzing_status")
    @Enumerated(EnumType.STRING)
    private AnalyzingStatus analyzingStatus;

    @Column(name = "positive_rate")
    private Integer positiveRate;

    @Column(name = "comment_download_url")
    private String commentDownloadUrl;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VideoComment> comments;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Keyword> keywords;

    // Getters, Setters, Constructors
}
