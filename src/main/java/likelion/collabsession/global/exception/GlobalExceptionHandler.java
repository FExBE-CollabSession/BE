package likelion.collabsession.global.exception;

import likelion.collabsession.global.exception.model.BaseErrorCode;
import java.util.stream.Collectors;
import likelion.collabsession.global.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  // 커스텀 예외
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException ex) {
    BaseErrorCode errorCode = ex.getErrorCode();
    log.error("Custom 오류 발생: {}", ex.getMessage());
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(BaseResponse.error(errorCode.getStatus().value(), ex.getMessage()));
  }

  // Validation 실패
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BaseResponse<Object>> handleValidationException(
      MethodArgumentNotValidException ex) {
    String errorMessages =
        ex.getBindingResult().getFieldErrors().stream()
            .map(e -> String.format("[%s] %s", e.getField(), e.getDefaultMessage()))
            .collect(Collectors.joining(" / "));
    log.warn("Validation 오류 발생: {}", errorMessages);
    return ResponseEntity.badRequest().body(BaseResponse.error(400, errorMessages));
  }

  // 필수 쿼리 파라미터 누릭
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<BaseResponse<Object>> handleMissingParams(MissingServletRequestParameterException ex) {
    log.warn("필수 요청 파라미터 누락: {}", ex.getParameterName());
    return ResponseEntity
        .badRequest()
        .body(BaseResponse.error(400, "필수 요청 파라미터가 누락되었습니다: " + ex.getParameterName()));
  }

  // 잘못된 인자 예외 발생
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<BaseResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
    log.warn("잘못된 인자 예외 발생: {}", ex.getMessage());
    return ResponseEntity
        .badRequest()
        .body(BaseResponse.error(400, ex.getMessage()));
  }

  // 요청 JSON이 잘못된 형식일 때
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<BaseResponse<Object>> handleInvalidJson(HttpMessageNotReadableException ex) {
    log.warn("잘못된 JSON 형식 요청: {}", ex.getMessage());
    return ResponseEntity
        .badRequest()
        .body(BaseResponse.error(400, "요청 형식이 올바르지 않습니다. JSON 구조를 확인해주세요."));
  }

  // 예상치 못한 예외
  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<Object>> handleException(Exception ex) {
    log.error("Server 오류 발생: ", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(BaseResponse.error(500, "예상치 못한 서버 오류가 발생했습니다."));
  }
}
