package khu.cloudproject.uwatch.video.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordResponseDTO {
    private String topKeyword1;
    private String topKeyword2;
    private String topKeyword3;
    private Long topKeyword1Count;
    private Long topKeyword2Count;
    private Long topKeyword3Count;
}
