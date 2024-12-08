package khu.cloudproject.uwatch.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import khu.cloudproject.uwatch.comment.controller.dto.CommentResponseDTO;
import khu.cloudproject.uwatch.comment.service.VideoCommentService;
import khu.cloudproject.uwatch.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "Comment", description = "댓글 관련 API")
@RequestMapping("/api/v1/comment")
public class CommentApi {

    private final VideoCommentService videoCommentService;

    @GetMapping("/comments/by-author")
    @Operation(summary = "특정 열혈 구독자의 댓글 목록 조회", description = "채널 ID와 authorName을 기반으로 해당 작성자가 작성한 댓글을 조회합니다.")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentResponseDTO>>> getCommentsByAuthor(
            @RequestParam String channelId,
            @RequestParam String authorName) {
        List<CommentResponseDTO.VideoCommentResponseDTO> response = videoCommentService.getCommentsByAuthor(channelId, authorName);
        return ResponseEntity.ok(ApiResponse.of(response));
    }
}
