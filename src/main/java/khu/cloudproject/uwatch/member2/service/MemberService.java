package khu.cloudproject.uwatch.member2.service;

import khu.cloudproject.uwatch.global.exception.CommonErrorCode;
import khu.cloudproject.uwatch.global.exception.CustomException;
import khu.cloudproject.uwatch.member2.controller.dto.MemberResponseDTO;
import khu.cloudproject.uwatch.member2.domain.Member;
import khu.cloudproject.uwatch.member2.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDTO.MyPageResponse getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(CommonErrorCode.MEMBER_NOT_FOUND));

        return MemberResponseDTO.MyPageResponse.builder()
                .id(member.getId())
                .googleId(member.getGoogleId())
                .name(member.getName())
                .email(member.getEmail())
                .profileImageUrl(member.getProfileImageUrl())
                .role(member.getRole())
                .channelTitle(member.getChannelTitle())
                .build();
    }
}
