package khu.cloudproject.uwatch.video.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoAnalysisResponseDTO {

    private double positiveRate; // 긍정 비율
    private Map<String, Double> sentimentDistribution; // 감정 분포 (기쁨, 화남 등)
    private Map<String, Double> categoryDistribution;  // 카테고리 분포 (반응, 질문 등)
}
