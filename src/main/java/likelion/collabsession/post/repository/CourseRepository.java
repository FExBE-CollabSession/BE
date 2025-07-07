package likelion.collabsession.post.repository;

import java.util.List;
import likelion.collabsession.post.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  List<Course> findAll();
}