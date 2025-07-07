package likelion.collabsession.comment.service;

import jakarta.transaction.Transactional;
import java.util.List;
import likelion.collabsession.comment.dto.request.CreateCommentRequest;
import likelion.collabsession.comment.dto.response.CommentResponse;
import likelion.collabsession.comment.entity.Comment;
import likelion.collabsession.comment.exception.CommentErrorCode;
import likelion.collabsession.comment.repository.CommentRepository;
import likelion.collabsession.global.exception.CustomException;
import likelion.collabsession.post.entity.Post;
import likelion.collabsession.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public CommentResponse createComment(Long postId, String writer, CreateCommentRequest request) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));

    Comment parent = null;
    if (request.getParentId() != null) {
      parent = commentRepository.findById(request.getParentId())
          .orElseThrow(() -> new CustomException(CommentErrorCode.INVALID_PARENT_COMMENT));
    }

    Comment comment = Comment.builder()
        .post(post)
        .content(request.getContent())
        .writer(writer)
        .parent(parent)
        .build();

    Comment saved = commentRepository.save(comment);

    return new CommentResponse(saved.getId(), saved.getContent(), saved.getWriter(),
        saved.getParent() != null ? saved.getParent().getId() : null);
  }


  // 댓글 불러오기
  public List<CommentResponse> getCommentsByPostId(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));

    List<Comment> comments = commentRepository.findAllByPost(post);

    return comments.stream()
        .map(comment -> new CommentResponse(
            comment.getId(),
            comment.getContent(),
            comment.getWriter(),
            comment.getParent() != null ? comment.getParent().getId() : null
        ))
        .toList();
  }

}
