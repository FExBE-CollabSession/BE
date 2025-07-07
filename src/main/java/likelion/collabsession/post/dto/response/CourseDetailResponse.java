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
public class CourseDetailResponse {
  private Long id;
  private String name;
  private String professor;
  private String classroom;
  private String lectureDay;
  private String startTime;
  private String endTime;

  public static CourseDetailResponse fromEntity(Course course) {
    return CourseDetailResponse.builder()
        .id(course.getId())
        .name(course.getName())
        .professor(course.getProfessor())
        .classroom(course.getClassroom())
        .lectureDay(course.getLectureDay())
        .startTime(course.getStartTime().toString())
        .endTime(course.getEndTime().toString())
        .build();
  }
}