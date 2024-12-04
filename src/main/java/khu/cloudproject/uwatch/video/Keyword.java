package khu.cloudproject.uwatch.video;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topkeyword1;
    private String topkeyword2;
    private String topkeyword3;

    @Column(name = "topkeyword1_count")
    private Long topkeyword1Count;
    @Column(name = "topkeyword2_count")
    private Long topkeyword2Count;
    @Column(name = "topkeyword3_count")
    private Long topkeyword3Count;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    // Getters, Setters, Constructors
}
