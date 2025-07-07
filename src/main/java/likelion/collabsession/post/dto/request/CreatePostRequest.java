package likelion.collabsession.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "CreatePostRequest: 게시글 생성 요청 DTO")
public class CreatePostRequest {
  @NotBlank(message = "제목은 필수입니다.")
  private String title;

  @NotBlank(message = "내용은 필수입니다.")
  private String content;

//  @NotBlank(message = "작성자는 필수입니다.")
//  private String writer;
}