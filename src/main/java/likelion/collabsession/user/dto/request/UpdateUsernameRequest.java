package likelion.collabsession.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(title = "UpdateUsernameRequest DTO", description = "이름(별명) 변경 요청")
public class UpdateUsernameRequest {

  @NotBlank(message = "새 이름(별명)은 필수입니다.")
  @Schema(description = "새 이름(별명)", example = "홍길동")
  private String username;

}
