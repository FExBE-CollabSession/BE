package likelion.collabsession.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(title = "UpdatePasswordRequest DTO", description = "비밀번호 변경 요청")
public class UpdatePasswordRequest {

  @NotBlank(message = "기존 비밀번호는 필수입니다.")
  @Schema(description = "기존 비밀번호", example = "oldPassword123!")
  private String currentPassword;

  @NotBlank(message = "새 비밀번호는 필수입니다.")
  @Schema(description = "새 비밀번호", example = "newPassword123!")
  private String newPassword;

}
