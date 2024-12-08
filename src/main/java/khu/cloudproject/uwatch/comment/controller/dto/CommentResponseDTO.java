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
}
