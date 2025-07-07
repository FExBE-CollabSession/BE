package likelion.collabsession.comment.repository;

import likelion.collabsession.comment.entity.Comment;
import likelion.collabsession.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  // 특정 게시글의 댓글 전체 조회 (계층형 조회를 위한 기본)
  List<Comment> findAllByPost(Post post);

  // 특정 게시글의 최상위 댓글만 조회
  List<Comment> findAllByPostAndParentIsNull(Post post);

  // 특정 댓글의 대댓글 조회
  List<Comment> findAllByParent(Comment parent);
}
