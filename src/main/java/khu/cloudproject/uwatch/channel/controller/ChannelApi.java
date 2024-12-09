package khu.cloudproject.uwatch.channel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import khu.cloudproject.uwatch.channel.controller.dto.ChannelResponseDTO;
import khu.cloudproject.uwatch.channel.service.ChannelService;
import khu.cloudproject.uwatch.global.response.ApiResponse;
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
@Tag(name = "Channel", description = "채널 관련 API")
@RequestMapping("/api/v1/channel")
public class ChannelApi {

    private final ChannelService channelService;

    @GetMapping("/info")
    @Operation(summary = "온보딩 페이지용 채널 간단 조회", description = "[member_id] 멤버 ID를 기반으로 채널 정보를 조회합니다."
            + "\n\n 슈카월드: 1 \n\n 곽튜브: 2")
    public ResponseEntity<ApiResponse<ChannelResponseDTO.ChannelProfileResponse>> getChannelInfoByMemberId(
            @RequestParam Long memberId) {
        ChannelResponseDTO.ChannelProfileResponse response = channelService.getChannelInfoByMemberId(memberId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/details")
    @Operation(summary = "채널 상세 정보 조회", description = "[channel_id] \n\n 슈카월드: UCsJ6RuBiTVWRX156FVbeaGg \n\n 곽튜브: UClRNDVO8093rmRTtLe4GEPw")
    public ResponseEntity<ApiResponse<ChannelResponseDTO.ChannelDetailsResponse>> getChannelDetails(@RequestParam String channelId) {
        ChannelResponseDTO.ChannelDetailsResponse response = channelService.getChannelDetails(channelId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/analysis/sentiment")
    @Operation(summary = "채널 감정 분석 조회", description = "[channel_id] \n\n 슈카월드: UCsJ6RuBiTVWRX156FVbeaGg \n\n 곽튜브: UClRNDVO8093rmRTtLe4GEPw")
    public ResponseEntity<ApiResponse<ChannelResponseDTO.ChannelSentimentAnalysisResponse>> getChannelSentimentAnalysis(
            @RequestParam String channelId) {
        ChannelResponseDTO.ChannelSentimentAnalysisResponse response = channelService.getChannelSentimentAnalysis(channelId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/superfans")
    @Operation(summary = "열혈 구독자 조회", description = "가장 많은 댓글을 작성한 상위 3명의 구독자를 반환합니다. \n\n [channel_id] \n\n 슈카월드: UCsJ6RuBiTVWRX156FVbeaGg \n\n 곽튜브: UClRNDVO8093rmRTtLe4GEPw")
    public ResponseEntity<ApiResponse<List<ChannelResponseDTO.SuperFanResponse>>> getSuperFans(@RequestParam String channelId) {
        List<ChannelResponseDTO.SuperFanResponse> response = channelService.getSuperFans(channelId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }
}
