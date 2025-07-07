package likelion.collabsession.gpt.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionData {
  private String title;
  private String body;
  private List<String> comments;  // 평평한 구조면 이걸로 OK
}
