package khu.cloudproject.uwatch.video.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import khu.cloudproject.uwatch.global.response.ApiResponse;
import khu.cloudproject.uwatch.video.controller.dto.CommentTrendIntervalResponseDTO;
import khu.cloudproject.uwatch.video.controller.dto.KeywordResponseDTO;
import khu.cloudproject.uwatch.video.controller.dto.VideoAnalysisResponseDTO;
import khu.cloudproject.uwatch.video.controller.dto.VideoResponseDTO;
import khu.cloudproject.uwatch.video.service.KeywordService;
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
    private final KeywordService keywordService;

    // TODO: 페이징 적용
    @GetMapping("/all")
    @Operation(summary = "채널 전체 비디오 목록 조회", description = "특정 채널의 비디오 목록을 조회합니다. \n\n [channel_id] \n\n 슈카월드: UCsJ6RuBiTVWRX156FVbeaGg \n\n 곽튜브: UClRNDVO8093rmRTtLe4GEPw")
    public ResponseEntity<ApiResponse<List<VideoResponseDTO.VideoSummaryResponse>>> getVideosByChannelId(
            @RequestParam String channelId) {
        List<VideoResponseDTO.VideoSummaryResponse> response = videoService.getVideosByChannelId(channelId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/top-videos")
    @Operation(summary = "채널 조회순 상위 10개 비디오 조회", description = "조회수를 기준으로 상위 10개의 비디오를 조회합니다. \n\n [channel_id] \n\n 슈카월드: UCsJ6RuBiTVWRX156FVbeaGg \n\n 곽튜브: UClRNDVO8093rmRTtLe4GEPw")
    public ResponseEntity<ApiResponse<List<VideoResponseDTO.VideoSummaryResponse>>> getTopVideosByViewCount(
            @RequestParam String channelId) {
        List<VideoResponseDTO.VideoSummaryResponse> response = videoService.getTopVideosByViewCount(channelId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/info")
    @Operation(
            summary = "비디오 세부 정보 조회",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<VideoResponseDTO.VideoDetailsResponse>> getVideoDetails(
            @RequestParam String videoId) {
        VideoResponseDTO.VideoDetailsResponse response = videoService.getVideoDetails(videoId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/comments/trend/interval")
    @Operation(
            summary = "댓글 추이 조회(30분 간격)",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<List<CommentTrendIntervalResponseDTO>>> getCommentTrendsByInterval(@RequestParam String videoId) {
        List<CommentTrendIntervalResponseDTO> response = videoService.getCommentTrendsBy30MinuteInterval(videoId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/keywords")
    @Operation(
            summary = "가장 많이 언급된 키워드 조회",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<KeywordResponseDTO>> getTopKeywords(@RequestParam String videoId) {
        KeywordResponseDTO response = keywordService.getTopKeywords(videoId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/analysis")
    @Operation(
            summary = "비디오 댓글 AI 분석 및 비율 분포도 조회(긍정 비율, 감정 분포, 카테고리 분포)",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<VideoAnalysisResponseDTO>> getVideoAnalysis(@RequestParam String videoId) {
        VideoAnalysisResponseDTO response = videoService.getVideoAnalysis(videoId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/analysis/sentiment")
    @Operation(
            summary = "비디오 댓글 감정 분석 수치 조회",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<VideoResponseDTO.SentimentAnalysisResponse>> getSentimentAnalysis(@RequestParam String videoId) {
        VideoResponseDTO.SentimentAnalysisResponse response = videoService.getSentimentAnalysis(videoId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

//    @GetMapping("/analysis/category")
//    @Operation(summary = "비디오 댓글 카테고리 분포 조회", description = "| Request: video_id | 기준으로 전체 댓글 수 대비 특정 카테고리의 댓글 비율을 반환합니다. |")
//    public ResponseEntity<ApiResponse<VideoResponseDTO.CategoryAnalysisResponse>> getCategoryAnalysis(@RequestParam String videoId) {
//        VideoResponseDTO.CategoryAnalysisResponse response = videoService.getCategoryAnalysis(videoId);
//        return ResponseEntity.ok(ApiResponse.of(response));
//    }
}
