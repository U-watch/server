package khu.cloudproject.uwatch.global.exception;

import khu.cloudproject.uwatch.global.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements BaseErrorCode {

    // 가장 일반적인 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // Test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트"),

    // 페이징 관련 에러
    PAGE_NEGATIVE_INPUT(HttpStatus.BAD_REQUEST, "PAGE4001", "페이지 번호는 1이상의 숫자여야 합니다."),

    // NOT FOUND
    CHANNEL_NOT_FOUND(HttpStatus.NOT_FOUND, "CHANNEL404", "해당 채널을 찾을 수 없습니다."),
    VIDEO_NOT_FOUND(HttpStatus.NOT_FOUND, "VIDEO404", "해당 비디오를 찾을 수 없습니다."),
    KEYWORD_NOT_FOUND(HttpStatus.NOT_FOUND, "KEYWORD404", "해당 키워드를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return null;
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}
