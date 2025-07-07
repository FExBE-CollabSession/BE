package likelion.collabsession.comment.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
  private Long id;
  private String content;
  private String writer;
  private Long parentId;
}
