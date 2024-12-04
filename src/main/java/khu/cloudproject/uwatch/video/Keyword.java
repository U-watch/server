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

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    // Getters, Setters, Constructors
}
