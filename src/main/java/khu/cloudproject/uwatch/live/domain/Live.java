package khu.cloudproject.uwatch.live.domain;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.channel.domain.Channel;
import khu.cloudproject.uwatch.comment.domain.LiveComment;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "live")
public class Live {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "live_id", nullable = false, unique = true)
    private String liveId;

    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "comment_count")
    private Integer commentCount;

    @Column(name = "wordcloud_img_url")
    private String wordcloudImageUrl;

    private Integer joy;
    private Integer surprise;
    private Integer anger;
    private Integer sadness;
    private Integer fear;
    private Integer disgust;

    private Integer response;
    private Integer feedback;
    private Integer question;
    private Integer spam;
    private Integer slander;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LiveComment> comments;

    // Getters, Setters, Constructors
}
