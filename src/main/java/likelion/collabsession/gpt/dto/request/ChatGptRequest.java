package likelion.collabsession.gpt.dto.request;

import java.util.List;
import likelion.collabsession.gpt.dto.QuestionData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatGptRequest {
  private List<QuestionData> questions;
}
