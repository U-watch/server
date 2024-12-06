package khu.cloudproject.uwatch.channel.controller;

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

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Channel", description = "채널 관련 API")
@RequestMapping("/api/v1/channel")
public class ChannelApi {

    private final ChannelService channelService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<ChannelResponseDTO.ChannelProfileResponse>> getChannelInfo(@RequestParam String channelId) {
        ChannelResponseDTO.ChannelProfileResponse response = channelService.getChannelInfo(channelId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }
}
