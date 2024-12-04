package khu.cloudproject.uwatch.channel;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.global.domain.BaseTimeEntity;
import khu.cloudproject.uwatch.member.Member;
import khu.cloudproject.uwatch.video.Video;
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
@Table(name = "channel")
public class Channel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channel_id", nullable = false, unique = true)
    private String channelId;

    private String thumbnail;

    @Column(name = "channel_name", nullable = false)
    private String channelName;

    private String description;

    @Column(name = "custom_url")
    private String customUrl;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "subscriber_count")
    private Integer subscriberCount;

    @Column(name = "video_count")
    private Integer videoCount;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "total_views")
    private Long totalViews;

    private String country;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Video> videos;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Live> lives;

    // Getters, Setters, Constructors
}
