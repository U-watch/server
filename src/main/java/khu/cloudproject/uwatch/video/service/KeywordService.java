package khu.cloudproject.uwatch.video.service;

import khu.cloudproject.uwatch.global.exception.CommonErrorCode;
import khu.cloudproject.uwatch.global.exception.CustomException;
import khu.cloudproject.uwatch.video.controller.dto.KeywordResponseDTO;
import khu.cloudproject.uwatch.video.domain.Keyword;
import khu.cloudproject.uwatch.video.domain.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordResponseDTO getTopKeywords(String videoId) {
        Keyword keyword = keywordRepository.findByVideoId(videoId)
                .orElseThrow(() -> new CustomException(CommonErrorCode.KEYWORD_NOT_FOUND));

        return KeywordResponseDTO.builder()
                .topKeyword1(keyword.getTopkeyword1())
                .topKeyword2(keyword.getTopkeyword2())
                .topKeyword3(keyword.getTopkeyword3())
                .topKeyword1Count(keyword.getTopkeyword1Count())
                .topKeyword2Count(keyword.getTopkeyword2Count())
                .topKeyword3Count(keyword.getTopkeyword3Count())
                .build();
    }
}
