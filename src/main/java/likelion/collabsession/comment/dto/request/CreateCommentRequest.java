package likelion.collabsession.comment.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
  private Long postId;
  private String content;
  private String writer;
  private Long parentId;
}
