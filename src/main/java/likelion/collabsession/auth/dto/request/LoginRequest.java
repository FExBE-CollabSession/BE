package likelion.collabsession.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="LoginRequest DTO", description = "사용자 로그인을 위한 데이터 전송")
public class LoginRequest {

  @Email(message = "이메일 형식이 올바르지 않습니다.")
  @NotBlank(message = "이메일은 필수입니다.")
  @Schema(description = "사용자 이메일", example = "test@example.com")
  private String email;

  @NotBlank(message = "비밀번호 항목은 필수입니다")
  @Schema(description = "비밀번호", example = "password123!")
  private String password;
}
