package khu.cloudproject.uwatch.channel.service;

import khu.cloudproject.uwatch.channel.controller.dto.ChannelResponseDTO;
import khu.cloudproject.uwatch.channel.domain.Channel;
import khu.cloudproject.uwatch.channel.domain.repository.ChannelRepository;
import khu.cloudproject.uwatch.comment.domain.repository.VideoCommentRepository;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import khu.cloudproject.uwatch.global.exception.CommonErrorCode;
import khu.cloudproject.uwatch.global.exception.CustomException;
import khu.cloudproject.uwatch.member.domain.Member;
import khu.cloudproject.uwatch.member.domain.repository.MemberRepository;
import khu.cloudproject.uwatch.video.domain.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final VideoCommentRepository videoCommentRepository;
    private final MemberRepository memberRepository;

    public ChannelResponseDTO.ChannelProfileResponse getChannelInfoByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(CommonErrorCode.MEMBER_NOT_FOUND));

        Channel channel = member.getChannel();
        if (channel == null) {
            throw new CustomException(CommonErrorCode.CHANNEL_NOT_FOUND);
        }

        return ChannelResponseDTO.ChannelProfileResponse.builder()
                .channelId(channel.getChannelId())
                .channelName(channel.getChannelName())
                .thumbnail(channel.getThumbnail())
                .build();
    }

    public ChannelResponseDTO.ChannelDetailsResponse getChannelDetails(String channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new CustomException(CommonErrorCode.CHANNEL_NOT_FOUND));

        return ChannelResponseDTO.ChannelDetailsResponse.builder()
                .channelId(channel.getChannelId())
                .channelName(channel.getChannelName())
                .customUrl(channel.getCustomUrl())
                .thumbnail(channel.getThumbnail())
                .subscriberCount(channel.getSubscriberCount())
                .videoCount(channel.getVideoCount())
                .viewCount(channel.getViewCount())
                .likeCount(channel.getLikeCount())
                .wordcloud(channel.getWordcloud())
                .build();
    }

    public ChannelResponseDTO.ChannelSentimentAnalysisResponse getChannelSentimentAnalysis(String channelId) {
        List<String> videoIds = videoRepository.findVideoIdsByChannelId(channelId);

        long totalComments = videoCommentRepository.countByVideoIds(videoIds);

        List<Object[]> sentimentCounts = videoCommentRepository.countSentimentsByVideoIds(videoIds);
        Map<Sentiment, Long> sentimentMap = sentimentCounts.stream()
                .collect(Collectors.toMap(
                        row -> (Sentiment) row[0],
                        row -> (Long) row[1]
                ));

        return ChannelResponseDTO.ChannelSentimentAnalysisResponse.builder()
                .joy(calculatePercentage(sentimentMap.getOrDefault(Sentiment.JOY, 0L), totalComments))
                .anger(calculatePercentage(sentimentMap.getOrDefault(Sentiment.ANGER, 0L), totalComments))
                .sadness(calculatePercentage(sentimentMap.getOrDefault(Sentiment.SADNESS, 0L), totalComments))
                .surprise(calculatePercentage(sentimentMap.getOrDefault(Sentiment.SURPRISE, 0L), totalComments))
                .fear(calculatePercentage(sentimentMap.getOrDefault(Sentiment.FEAR, 0L), totalComments))
                .disgust(calculatePercentage(sentimentMap.getOrDefault(Sentiment.DISGUST, 0L), totalComments))
                .build();
    }

    private double calculatePercentage(long count, long total) {
        return Math.round(((double) count / total) * 10000.0) / 100.0;
    }

    public List<ChannelResponseDTO.SuperFanResponse> getSuperFans(String channelId) {
        List<Object[]> commentCounts = videoCommentRepository.countComments(channelId);

        return commentCounts.stream()
                .limit(3) // 상위 3명 제한
                .map(row -> ChannelResponseDTO.SuperFanResponse.builder()
                        .authorName((String) row[0]) // authorName
                        .authorProfileImageUrl((String) row[1]) // authorProfileImageUrl
                        .commentCount((Long) row[2]) // commentCount
                        .build())
                .collect(Collectors.toList());
    }
}
