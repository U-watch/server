package khu.cloudproject.uwatch.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "author_id")
    private String authorId;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VideoComment> videoComments;

    // Getters, Setters, Constructors
}
