package likelion.collabsession.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCourseRequest {
  private Long courseId;  // DB에 있는 course의 ID
}