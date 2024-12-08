package khu.cloudproject.uwatch.video.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import khu.cloudproject.uwatch.global.response.ApiResponse;
import khu.cloudproject.uwatch.video.controller.dto.VideoResponseDTO;
import khu.cloudproject.uwatch.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Video", description = "비디오 관련 API")
@RequestMapping("/api/v1/video")
public class VideoApi {

    private final VideoService videoService;

    @GetMapping("/analysis/sentiment")
    @Operation(summary = "비디오 댓글 감정 분석 수치 조회 API", description = "전체 댓글 수 대비 특정 감정의 댓글 비율을 반환합니다.")
    public ResponseEntity<ApiResponse<VideoResponseDTO.SentimentAnalysisResponse>> getSentimentAnalysis(@RequestParam String videoId) {
        VideoResponseDTO.SentimentAnalysisResponse response = videoService.getSentimentAnalysis(videoId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/videos")
    @Operation(summary = "채널 비디오 조회", description = "특정 채널의 비디오 목록을 조회합니다. \n\n [channel_id] \n\n 슈카월드: UCsJ6RuBiTVWRX156FVbeaGg \n\n 곽튜브: UClRNDVO8093rmRTtLe4GEPw")
    public ResponseEntity<ApiResponse<List<VideoResponseDTO.VideoSummaryResponse>>> getVideosByChannelId(
            @RequestParam String channelId) {
        List<VideoResponseDTO.VideoSummaryResponse> response = videoService.getVideosByChannelId(channelId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }
}
