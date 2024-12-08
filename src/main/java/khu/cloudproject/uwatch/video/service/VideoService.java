package khu.cloudproject.uwatch.video.service;

import khu.cloudproject.uwatch.comment.domain.repository.VideoCommentRepository;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import khu.cloudproject.uwatch.global.exception.CommonErrorCode;
import khu.cloudproject.uwatch.global.exception.CustomException;
import khu.cloudproject.uwatch.video.controller.dto.VideoResponseDTO;
import khu.cloudproject.uwatch.video.domain.Video;
import khu.cloudproject.uwatch.video.domain.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoCommentRepository videoCommentRepository;
    private final VideoRepository videoRepository;

    public VideoResponseDTO.SentimentAnalysisResponse getSentimentAnalysis(String videoId) {
        long totalComments = videoCommentRepository.countByVideoId(videoId);

        List<Object[]> sentimentCounts = videoCommentRepository.countSentimentsByVideoId(videoId);
        Map<Sentiment, Long> sentimentMap = sentimentCounts.stream()
                .collect(Collectors.toMap(
                        row -> (Sentiment) row[0], // 감정 종류
                        row -> (Long) row[1]      // 해당 감정의 댓글 수
                ));

        return VideoResponseDTO.SentimentAnalysisResponse.builder()
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

    public List<VideoResponseDTO.VideoSummaryResponse> getVideosByChannelId(String channelId) {
        List<Video> videos = videoRepository.findByChannelId(channelId);

        // DTO로 변환
        return videos.stream()
                .map(video -> VideoResponseDTO.VideoSummaryResponse.builder()
                        .videoId(video.getVideoId())
                        .title(video.getTitle())
                        .thumbnail(video.getThumbnail())
                        .viewCount(video.getViewCount())
                        .likeCount(video.getLikeCount())
                        .commentCount(video.getCommentCount())
                        .publishedAt(video.getPublishedAt())
                        .analyzingStatus(video.getAnalyzingStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<VideoResponseDTO.VideoSummaryResponse> getTopVideosByViewCount(String channelId) {
        List<Video> videos = videoRepository.findTop20ByChannelIdOrderByViewCountDesc(channelId);

        // DTO로 변환
        return videos.stream()
                .map(video -> VideoResponseDTO.VideoSummaryResponse.builder()
                        .videoId(video.getVideoId())
                        .title(video.getTitle())
                        .thumbnail(video.getThumbnail())
                        .viewCount(video.getViewCount())
                        .likeCount(video.getLikeCount())
                        .commentCount(video.getCommentCount())
                        .publishedAt(video.getPublishedAt())
                        .analyzingStatus(video.getAnalyzingStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public VideoResponseDTO.VideoDetailsResponse getVideoDetails(String videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new CustomException(CommonErrorCode.VIDEO_NOT_FOUND));

        return VideoResponseDTO.VideoDetailsResponse.builder()
                .videoId(video.getVideoId())
                .title(video.getTitle())
                .thumbnail(video.getThumbnail())
                .viewCount(video.getViewCount())
                .commentCount(video.getCommentCount())
                .lastUpdated(video.getLastUpdated())
                .wordCloudUrl(video.getWordcloud())
                .build();
    }
}

