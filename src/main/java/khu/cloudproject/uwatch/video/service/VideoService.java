package khu.cloudproject.uwatch.video.service;

import khu.cloudproject.uwatch.comment.domain.repository.VideoCommentRepository;
import khu.cloudproject.uwatch.global.enums.PositiveStatus;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import khu.cloudproject.uwatch.global.exception.CommonErrorCode;
import khu.cloudproject.uwatch.global.exception.CustomException;
import khu.cloudproject.uwatch.video.controller.dto.CommentTrendIntervalResponseDTO;
import khu.cloudproject.uwatch.video.controller.dto.VideoAnalysisResponseDTO;
import khu.cloudproject.uwatch.video.controller.dto.VideoResponseDTO;
import khu.cloudproject.uwatch.video.domain.Video;
import khu.cloudproject.uwatch.video.domain.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "viewCount"));
        List<Video> videos = videoRepository.findTopVideosByChannelId(channelId, pageable).getContent();

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

    public VideoAnalysisResponseDTO getVideoAnalysis(String videoId) {
        long totalComments = videoCommentRepository.countByVideoId(videoId);

        if (totalComments == 0) {
            return VideoAnalysisResponseDTO.builder()
                    .positiveRate(0.0)
                    .sentimentDistribution(new HashMap<>())
                    .categoryDistribution(new HashMap<>())
                    .build();
        }

        // Positive Rate
        long positiveCount = videoCommentRepository.countByVideoIdAndPositiveStatus(videoId, PositiveStatus.POSITIVE);
        double positiveRate = (double) positiveCount / totalComments * 100;
        
        DecimalFormat df = new DecimalFormat("#.0");
        positiveRate = Double.valueOf(df.format(positiveRate));

        // Sentiment Distribution
        List<Object[]> sentimentCounts = videoCommentRepository.countSentimentsByVideoId(videoId);
        Map<String, Double> sentimentDistribution = calculateDistribution(sentimentCounts, totalComments);

        // Category Distribution
        List<Object[]> categoryCounts = videoCommentRepository.countCategoriesByVideoId(videoId);
        Map<String, Double> categoryDistribution = calculateDistribution(categoryCounts, totalComments);

        return VideoAnalysisResponseDTO.builder()
                .positiveRate(positiveRate)
                .sentimentDistribution(sentimentDistribution)
                .categoryDistribution(categoryDistribution)
                .build();
    }

    private Map<String, Double> calculateDistribution(List<Object[]> counts, long total) {
        Map<String, Double> distribution = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.0"); // 소수점 한 자리까지만 포맷팅

        for (Object[] count : counts) {
            if (count[0] != null && count[1] != null) { // Null 체크
                String key = count[0].toString();
                long value = ((Number) count[1]).longValue();
                double percentage = (double) value / total * 100;
                distribution.put(key, Double.valueOf(df.format(percentage))); // 포맷 적용
            }
        }
        return distribution;
    }

    public List<CommentTrendIntervalResponseDTO> getCommentTrendsBy30MinuteInterval(String videoId) {
        List<Object[]> results = videoCommentRepository.findCommentTrendsBy30MinuteInterval(videoId);

        return results.stream()
                .map(result -> {
                    String intervalStart = result[0].toString();
                    String intervalEnd = LocalDateTime.parse(intervalStart + ":00:00",
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                            .plusMinutes(30)
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return CommentTrendIntervalResponseDTO.builder()
                            .interval(intervalStart + " ~ " + intervalEnd)
                            .commentCount((long) result[1])
                            .build();
                })
                .collect(Collectors.toList());
    }
}

