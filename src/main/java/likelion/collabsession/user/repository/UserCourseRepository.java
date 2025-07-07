package likelion.collabsession.user.repository;

import java.util.List;
import likelion.collabsession.post.entity.Course;
import likelion.collabsession.user.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import likelion.collabsession.user.entity.User;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
  boolean existsByUserAndCourse(User user, Course course);
  List<UserCourse> findByUserId(Long userId);
}