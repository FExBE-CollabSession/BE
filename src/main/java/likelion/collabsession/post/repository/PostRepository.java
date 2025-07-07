package likelion.collabsession.post.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import likelion.collabsession.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByCourseIdAndIsDeletedFalse(Long courseId);
  List<Post> findAllByCourseIdAndIsDeletedFalseOrderByCreatedAtDesc(Long courseId);
  List<Post> findAllByCourseIdAndIsDeletedFalseOrderByViewCountDesc(Long courseId);
  Optional<Post> findByIdAndCourseIdAndIsDeletedFalse(Long postId, Long courseId);
  @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% AND p.isDeleted = false ORDER BY p.createdAt DESC")
  List<Post> findTop3ByTitleKeyword(@Param("keyword") String keyword, Pageable pageable);

}