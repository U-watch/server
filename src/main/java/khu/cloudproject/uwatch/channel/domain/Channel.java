package khu.cloudproject.uwatch.channel.domain;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.live.domain.Live;
import khu.cloudproject.uwatch.member.domain.Member;
import khu.cloudproject.uwatch.video.domain.Video;
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
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channel_id", unique = true)
    private String channelId;

    private String thumbnail;

    @Column(name = "channel_name")
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
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Video> videos;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Live> lives;

    // Getters, Setters, Constructors
}
