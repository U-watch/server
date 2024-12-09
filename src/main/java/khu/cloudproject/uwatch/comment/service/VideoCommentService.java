package khu.cloudproject.uwatch.comment.service;

import jakarta.transaction.Transactional;
import khu.cloudproject.uwatch.comment.controller.dto.CommentResponseDTO;
import khu.cloudproject.uwatch.comment.domain.VideoComment;
import khu.cloudproject.uwatch.comment.domain.repository.VideoCommentRepository;
import khu.cloudproject.uwatch.global.enums.BlockedStatus;
import khu.cloudproject.uwatch.global.enums.CommentCategory;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import khu.cloudproject.uwatch.global.exception.CommonErrorCode;
import khu.cloudproject.uwatch.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoCommentService {

    private final VideoCommentRepository videoCommentRepository;

    public List<CommentResponseDTO.VideoCommentResponseDTO> getCommentsByAuthor(String channelId, String authorName) {
        // 특정 채널의 동영상 댓글 중 해당 작성자의 댓글 가져오기
        List<VideoComment> comments = videoCommentRepository.findByChannelIdAndAuthorName(channelId, authorName);

        // DTO로 변환
        return comments.stream()
                .map(comment -> CommentResponseDTO.VideoCommentResponseDTO.builder()
                        .commentText(comment.getCommentText())
                        .publishedAt(comment.getPublishedAt())
                        .likeCount(comment.getLikeCount())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CommentResponseDTO.VideoCommentDetailResponseDTO> getDetailedCommentsByKeyword(String videoId, String keyword) {
        List<VideoComment> comments = videoCommentRepository.findDetailedCommentsByVideoIdAndKeyword(videoId, keyword);

        return comments.stream()
                .map(comment -> CommentResponseDTO.VideoCommentDetailResponseDTO.builder()
                        .authorName(comment.getAuthorName())
                        .authorProfileImage(comment.getAuthorProfileImageUrl())
                        .commentText(comment.getCommentText())
                        .publishedAt(comment.getPublishedAt())
                        .likeCount(comment.getLikeCount())
                        .build())
                .toList();
    }

    public List<CommentResponseDTO.VideoCommentDetailResponseDTO> getAllCommentsByVideoId(String videoId) {
        List<VideoComment> comments = videoCommentRepository.findByVideoIdAndNotBlocked(videoId);

        if (comments.isEmpty()) {
            throw new CustomException(CommonErrorCode.NO_COMMENTS_FOUND);
        }

        return comments.stream()
                .map(comment -> CommentResponseDTO.VideoCommentDetailResponseDTO.builder()
                        .authorName(comment.getAuthorName())
                        .authorProfileImage(comment.getAuthorProfileImageUrl())
                        .commentText(comment.getCommentText())
                        .publishedAt(comment.getPublishedAt())
                        .likeCount(comment.getLikeCount())
                        .commentDownloadUrl(
                                comment.getVideo().getCommentDownloadUrl() != null ?
                                        comment.getVideo().getCommentDownloadUrl() :
                                        "No download available"
                        )
                        .build())
                .collect(Collectors.toList());
    }

    public List<CommentResponseDTO.VideoCommentDetailResponseDTO> getCommentsBySentiment(String videoId, Sentiment sentiment) {
        List<VideoComment> comments = videoCommentRepository.findByVideoIdAndSentimentAndNotBlocked(videoId, sentiment);

        if (comments.isEmpty()) {
            throw new CustomException(CommonErrorCode.NO_COMMENTS_FOUND);
        }

        return comments.stream()
                .map(comment -> CommentResponseDTO.VideoCommentDetailResponseDTO.builder()
                        .authorName(comment.getAuthorName())
                        .authorProfileImage(comment.getAuthorProfileImageUrl())
                        .commentText(comment.getCommentText())
                        .publishedAt(comment.getPublishedAt())
                        .likeCount(comment.getLikeCount())
                        .build())
                .toList();
    }

    public List<CommentResponseDTO.VideoCommentDetailResponseDTO> getCommentsByCategory(String videoId, CommentCategory category) {
        List<VideoComment> comments = videoCommentRepository.findByVideoIdAndCategoryAndNotBlocked(videoId, category);

        if (comments.isEmpty()) {
            throw new CustomException(CommonErrorCode.NO_COMMENTS_FOUND);
        }

        return comments.stream()
                .map(comment -> CommentResponseDTO.VideoCommentDetailResponseDTO.builder()
                        .authorName(comment.getAuthorName())
                        .authorProfileImage(comment.getAuthorProfileImageUrl())
                        .commentText(comment.getCommentText())
                        .publishedAt(comment.getPublishedAt())
                        .likeCount(comment.getLikeCount())
                        .build())
                .toList();
    }

    @Transactional
    public void blockCommentsByAuthor(String authorId) {
        int updatedCount = videoCommentRepository.updateBlockedStatusByAuthorId(authorId, BlockedStatus.BLOCKED);

        if (updatedCount == 0) {
            throw new CustomException(CommonErrorCode.NO_COMMENTS_FOUND);
        }
    }
}
