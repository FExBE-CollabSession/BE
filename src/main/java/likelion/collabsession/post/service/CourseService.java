package likelion.collabsession.post.service;

import java.util.List;
import java.util.stream.Collectors;
import likelion.collabsession.post.dto.response.CourseResponse;
import likelion.collabsession.user.repository.UserCourseRepository;
import likelion.collabsession.user.entity.UserCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {

  private final UserCourseRepository userCourseRepository;

  @Transactional
  public List<CourseResponse> getCoursesByUser(Long userId) {
    List<UserCourse> userCourses = userCourseRepository.findByUserId(userId);
    return userCourses.stream()
        .map(userCourse -> CourseResponse.fromEntity(userCourse.getCourse()))
        .collect(Collectors.toList());
  }
}