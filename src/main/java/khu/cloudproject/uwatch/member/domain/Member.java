package khu.cloudproject.uwatch.member.domain;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.channel.domain.Channel;
import khu.cloudproject.uwatch.global.domain.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "google_id", unique = true)
    private String googleId;

    private String name;

    private String role;

    private String email;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "channel_title")
    private String channelTitle;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "fcm_token")
    private String fcmToken;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Channel channel;

    // Getters, Setters, Constructors
}
