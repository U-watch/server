package khu.cloudproject.uwatch.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import khu.cloudproject.uwatch.comment.controller.dto.CommentResponseDTO;
import khu.cloudproject.uwatch.comment.service.VideoCommentService;
import khu.cloudproject.uwatch.global.enums.CommentCategory;
import khu.cloudproject.uwatch.global.enums.Sentiment;
import khu.cloudproject.uwatch.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "Comment", description = "댓글 관련 API")
@RequestMapping("/api/v1/comments")
public class CommentApi {

    private final VideoCommentService videoCommentService;

    //TODO: 댓글 관련 리스트 조회 API -> 페이징 적용

    @GetMapping("/{channelId}/by-author?authorName={authorName}")
    @Operation(summary = "특정 열혈 구독자의 댓글 목록 조회", description = "채널 ID와 authorName을 기반으로 해당 작성자가 작성한 댓글을 조회합니다.")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentResponseDTO>>> getCommentsByAuthor(
            @PathVariable String channelId,
            @PathVariable String authorName) {
        List<CommentResponseDTO.VideoCommentResponseDTO> response = videoCommentService.getCommentsByAuthor(channelId, authorName);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/{videoId}/details?keyword={keyword}")
    @Operation(summary = "특정 키워드 포함 댓글 목록 조회",
            description = "| Request: video_id, keyword | 특정 비디오에서 키워드를 포함한 댓글의 상세 리스트를 제공합니다. |")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getDetailedCommentsByKeyword(
            @PathVariable String videoId,
            @PathVariable String keyword) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> response = videoCommentService.getDetailedCommentsByKeyword(videoId, keyword);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/{videoId}/all")
    @Operation(summary = "특정 비디오 전체 댓글 조회", description = "특정 비디오 ID의 전체 댓글을 조회합니다.")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getAllCommentsByVideoId(
            @PathVariable String videoId) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> comments = videoCommentService.getAllCommentsByVideoId(videoId);
        return ResponseEntity.ok(ApiResponse.of(comments));
    }

    @GetMapping("/{videoId}/detail?sentiment={sentiment}")
    @Operation(summary = "특정 감정에 해당하는 댓글 조회", description = "비디오 ID와 감정을 기준으로 댓글을 필터링합니다.")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getCommentsBySentiment(
            @PathVariable String videoId,
            @PathVariable Sentiment sentiment) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> comments = videoCommentService.getCommentsBySentiment(videoId, sentiment);
        return ResponseEntity.ok(ApiResponse.of(comments));
    }

    @GetMapping("/{videoId}/detail?category={category}")
    @Operation(summary = "특정 카테고리에 해당하는 댓글 조회", description = "비디오 ID와 카테고리를 기준으로 댓글을 필터링합니다.")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getCommentsByCategory(
            @PathVariable String videoId,
            @PathVariable CommentCategory category) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> comments = videoCommentService.getCommentsByCategory(videoId, category);
        return ResponseEntity.ok(ApiResponse.of(comments));
    }
}
