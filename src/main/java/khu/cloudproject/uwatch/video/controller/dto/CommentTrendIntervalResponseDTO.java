package khu.cloudproject.uwatch.video.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentTrendIntervalResponseDTO {
    private int interval; // 간격 (분 단위)
    private String startedAt; // 시작 시간
    private List<Long> commentCount; // 누적 댓글 수 리스트
}
