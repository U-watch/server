package khu.cloudproject.uwatch.global.exception;

import khu.cloudproject.uwatch.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    ErrorResponse getErrorResponse();

    String getMessage();

    HttpStatus getStatus();
}
