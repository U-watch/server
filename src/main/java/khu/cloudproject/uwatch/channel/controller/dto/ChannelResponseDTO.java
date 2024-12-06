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
    public class ChannelProfileResponse {
        private String channelId;
        private String channelName;
        private String customUrl;
    }
}
