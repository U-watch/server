package khu.cloudproject.uwatch.channel.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChannelResponseDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChannelProfileResponse {
        private String channelId;
        private String channelName;
        private String thumbnail;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChannelDetailsResponse {
        private String channelId;
        private String channelName;
        private String customUrl;
        private String thumbnail;
        private Integer subscriberCount;
        private Integer videoCount;
        private Long viewCount;
        private Long likeCount;
        private String wordcloud;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChannelSentimentAnalysisResponse {
        private double joy;
        private double anger;
        private double sadness;
        private double surprise;
        private double fear;
        private double disgust;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SuperFanResponse {
        private String authorName;
        private String authorProfileImageUrl;
        private Long commentCount;
    }
}
