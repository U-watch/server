package khu.cloudproject.uwatch.channel.service;

import khu.cloudproject.uwatch.channel.controller.dto.ChannelResponseDTO;
import khu.cloudproject.uwatch.channel.domain.Channel;
import khu.cloudproject.uwatch.channel.domain.repository.ChannelRepository;
import khu.cloudproject.uwatch.global.exception.CommonErrorCode;
import khu.cloudproject.uwatch.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelResponseDTO.ChannelProfileResponse getChannelInfo(String channelId) {
        Channel channel = channelRepository.findByChannelId(channelId)
                .orElseThrow(() -> new CustomException(CommonErrorCode.CHANNEL_NOT_FOUND));

        return ChannelResponseDTO.ChannelProfileResponse.builder()
                .channelId(channel.getChannelId())
                .channelName(channel.getChannelName())
                .customUrl(channel.getCustomUrl())
                .build();
    }
}
