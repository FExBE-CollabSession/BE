package likelion.collabsession.post.repository;

import likelion.collabsession.post.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}