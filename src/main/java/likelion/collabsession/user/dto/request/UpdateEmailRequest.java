package likelion.collabsession.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "UpdateEmailRequest DTO")
public class UpdateEmailRequest {
  @Email(message = "올바른 이메일 형식이 아닙니다.")
  @NotBlank(message = "이메일은 필수입니다.")
  @Schema(description = "변경할 이메일", example = "new@example.com")
  private String email;
}
