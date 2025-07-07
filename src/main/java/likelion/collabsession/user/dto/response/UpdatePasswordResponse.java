package likelion.collabsession.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "UpdatePasswordResponse DTO", description = "비밀번호 변경 응답")
public class UpdatePasswordResponse {

  @Schema(description = "사용자 ID", example = "1")
  private String username;

  @Schema(description = "비밀번호 변경 여부", example = "true")
  private boolean updated;

}
