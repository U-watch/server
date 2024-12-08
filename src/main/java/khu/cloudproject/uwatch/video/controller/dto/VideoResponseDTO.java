package khu.cloudproject.uwatch.video.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VideoResponseDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SentimentAnalysisResponse {
        private double joy;
        private double anger;
        private double sadness;
        private double surprise;
        private double fear;
        private double disgust;
    }
}
