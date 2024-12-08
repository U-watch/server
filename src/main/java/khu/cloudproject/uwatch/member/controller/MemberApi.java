package khu.cloudproject.uwatch.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import khu.cloudproject.uwatch.global.response.ApiResponse;
import khu.cloudproject.uwatch.member.controller.dto.MemberResponseDTO;
import khu.cloudproject.uwatch.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Member", description = "회원 관련 API")
@RequestMapping("/api/v1/member")
public class MemberApi {

    private final MemberService memberService;

    @GetMapping("/mypage")
    @Operation(summary = "마이페이지 조회", description = "[member_id] \n\n 슈카월드: 1, 곽튜브: 2 \n\n 회원의 마이페이지 정보를 조회합니다.")
    public ResponseEntity<ApiResponse<MemberResponseDTO.MyPageResponse>> getMyPage(
            @RequestParam Long memberId) {
        MemberResponseDTO.MyPageResponse response = memberService.getMyPage(memberId);
        return ResponseEntity.ok(ApiResponse.of(response));
    }
}
