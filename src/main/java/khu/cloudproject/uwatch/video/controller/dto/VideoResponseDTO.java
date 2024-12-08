package khu.cloudproject.uwatch.video.controller.dto;

import khu.cloudproject.uwatch.global.enums.AnalyzingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoSummaryResponse {
        private String videoId;                     // 비디오 ID
        private String title;                       // 비디오 제목
        private String thumbnail;                   // 비디오 썸네일 URL
        private Long viewCount;                     // 조회수
        private Integer likeCount;                  // 좋아요 수
        private Integer commentCount;               // 댓글 수
        private LocalDateTime publishedAt;          // 게시 날짜
        private AnalyzingStatus analyzingStatus;    // 비디오 분석 여부
    }
}
