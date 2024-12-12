package khu.cloudproject.uwatch.member2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageResponse {
        private Long id;
        private String googleId;
        private String name;
        private String email;
        private String profileImageUrl;
        private String role;
        private String channelTitle;
    }
}
