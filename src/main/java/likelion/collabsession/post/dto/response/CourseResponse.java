package likelion.collabsession.post.dto.response;

import likelion.collabsession.post.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
  private Long id;
  private String name;

  public static CourseResponse fromEntity(Course course) {
    return CourseResponse.builder()
        .id(course.getId())
        .name(course.getName())
        .build();
  }
}