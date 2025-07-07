package likelion.collabsession.comment.exception;

import likelion.collabsession.global.exception.model.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {
  COMMENT_NOT_FOUND("COMMENT_4041", "존재하지 않는 댓글입니다.", HttpStatus.NOT_FOUND),
  UNAUTHORIZED_COMMENT_ACCESS("COMMENT_4031", "해당 댓글에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
  COMMENT_ALREADY_DELETED("COMMENT_4001", "이미 삭제된 댓글입니다.", HttpStatus.BAD_REQUEST),
  INVALID_PARENT_COMMENT("COMMENT_4002", "유효하지 않은 부모 댓글입니다.", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
