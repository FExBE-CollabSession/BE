package likelion.collabsession.post.repository;

import java.util.List;
import java.util.Optional;
import likelion.collabsession.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByCourseIdAndIsDeletedFalse(Long courseId);
  List<Post> findAllByCourseIdAndIsDeletedFalseOrderByCreatedAtDesc(Long courseId);
  List<Post> findAllByCourseIdAndIsDeletedFalseOrderByViewCountDesc(Long courseId);
  Optional<Post> findByIdAndCourseIdAndIsDeletedFalse(Long postId, Long courseId);
}