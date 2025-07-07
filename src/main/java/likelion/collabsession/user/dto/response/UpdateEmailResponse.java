package likelion.collabsession.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "UpdateEmailResponse DTO", description = "이메일 변경에 대한 응답 반환")
public class UpdateEmailResponse {

  @Schema(description = "변경된 사용자 이름", example = "채린")
  private String username;

  @Schema(description = "변경된 이메일", example = "newtest@example.com")
  private String newEmail;

}
