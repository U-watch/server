package khu.cloudproject.uwatch.comment.domain;

import jakarta.persistence.*;
import khu.cloudproject.uwatch.live.domain.Live;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "live_comment")
public class LiveComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorId;

    private String profileImageUrl;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @ManyToOne
    @JoinColumn(name = "live_id")
    private Live live;

    // Getters, Setters, Constructors
}
