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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{channelId}/by-author/{authorName}")
    @Operation(summary = "특정 열혈 구독자의 댓글 목록 조회", description = "[channel_id] \n\n 슈카월드: UCsJ6RuBiTVWRX156FVbeaGg \n\n 곽튜브: UClRNDVO8093rmRTtLe4GEPw")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentResponseDTO>>> getCommentsByAuthor(
            @PathVariable String channelId,
            @PathVariable String authorName) {
        List<CommentResponseDTO.VideoCommentResponseDTO> response = videoCommentService.getCommentsByAuthor(channelId, authorName);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/{videoId}/details/keyword")
    @Operation(
            summary = "특정 키워드 포함 댓글 목록 조회",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getDetailedCommentsByKeyword(
            @PathVariable String videoId,
            @RequestParam String keyword) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> response = videoCommentService.getDetailedCommentsByKeyword(videoId, keyword);
        return ResponseEntity.ok(ApiResponse.of(response));
    }

    @GetMapping("/{videoId}/all")
    @Operation(
            summary = "특정 비디오 전체 댓글 조회",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getAllCommentsByVideoId(
            @PathVariable String videoId) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> comments = videoCommentService.getAllCommentsByVideoId(videoId);
        return ResponseEntity.ok(ApiResponse.of(comments));
    }

    @GetMapping("/{videoId}/detail/sentiment")
    @Operation(
            summary = "특정 감정에 해당하는 댓글 조회",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getCommentsBySentiment(
            @PathVariable String videoId,
            @RequestParam Sentiment sentiment) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> comments = videoCommentService.getCommentsBySentiment(videoId, sentiment);
        return ResponseEntity.ok(ApiResponse.of(comments));
    }

    @GetMapping("/{videoId}/detail/category")
    @Operation(
            summary = "특정 카테고리에 해당하는 댓글 조회",
            description = """
                        [video_id]
                        
                        “슈카월드”
                        - JdRcM4fLwgE
                        - 5IWvoKOLX4Y
                        - SWzyztkoagc

                        “곽튜브”
                        - RSv0K4hQyV8
                        - 84zkJa9xIVA
                        - 8_hpcebDNFw
                    """
    )
    public ResponseEntity<ApiResponse<List<CommentResponseDTO.VideoCommentDetailResponseDTO>>> getCommentsByCategory(
            @PathVariable String videoId,
            @RequestParam CommentCategory category) {
        List<CommentResponseDTO.VideoCommentDetailResponseDTO> comments = videoCommentService.getCommentsByCategory(videoId, category);
        return ResponseEntity.ok(ApiResponse.of(comments));
    }

    @PostMapping("/block")
    @Operation(summary = "댓글 차단", description = "| Request: author_id | 특정 author_id의 댓글을 BLOCKED 상태로 변경합니다. |")
    public ResponseEntity<ApiResponse<String>> blockCommentsByAuthor(
            @RequestParam String authorId) {
        videoCommentService.blockCommentsByAuthor(authorId);
        return ResponseEntity.ok(ApiResponse.of("해당 작성자의 댓글이 차단되었습니다."));
    }
}
