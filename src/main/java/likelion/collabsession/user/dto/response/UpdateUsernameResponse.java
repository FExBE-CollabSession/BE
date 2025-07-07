package likelion.collabsession.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "UpdateUsernameResponse DTO", description = "이름 변경에 대한 응답 반환")
public class UpdateUsernameResponse {

  @Schema(description = "변경된 사용자 이름", example = "홍길동")
  private String username;

}
