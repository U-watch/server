package khu.cloudproject.uwatch.comment.service;

import khu.cloudproject.uwatch.comment.controller.dto.CommentResponseDTO;
import khu.cloudproject.uwatch.comment.domain.VideoComment;
import khu.cloudproject.uwatch.comment.domain.repository.VideoCommentRepository;
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
}
