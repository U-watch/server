package khu.cloudproject.uwatch.comment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoCommentResponseDTO {
        private String commentText;    // 댓글 내용
        private LocalDateTime publishedAt; // 댓글 작성일
        private Integer likeCount;     // 좋아요 수
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoCommentDetailResponseDTO {
        private String authorName;          // 작성자 이름
        private String authorProfileImage;  // 작성자 프로필 이미지 URL
        private String commentText;         // 댓글 내용
        private LocalDateTime publishedAt;  // 댓글 작성 시간
        private Integer likeCount;          // 댓글 좋아요 수
        private String commentDownloadUrl;  // 댓글 다운로드 URL
    }
}
