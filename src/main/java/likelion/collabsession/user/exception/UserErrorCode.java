package likelion.collabsession.user.exception;

import likelion.collabsession.global.exception.model.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
  USER_NOT_FOUND("USER_4041", "존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
  EMAIL_ALREADY_EXISTS("USER_4091", "이미 등록된 이메일입니다.", HttpStatus.CONFLICT),
  INVALID_PASSWORD("USER_4042", "기존 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
  COURSE_NOT_FOUND("COURSE_4041", "존재하지 않는 수업입니다.", HttpStatus.NOT_FOUND),
  ALREADY_ENROLLED("USER_4092", "이미 해당 수업을 추가했습니다.", HttpStatus.CONFLICT);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
