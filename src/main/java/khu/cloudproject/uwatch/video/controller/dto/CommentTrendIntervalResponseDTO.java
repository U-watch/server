package khu.cloudproject.uwatch.video.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentTrendIntervalResponseDTO {
    private String interval; // 예: "2024-12-09 12:00 ~ 2024-12-09 12:30"
    private long commentCount; // 해당 30분 간격의 댓글 수
}
