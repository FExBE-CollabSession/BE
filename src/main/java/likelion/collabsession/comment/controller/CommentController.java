package likelion.collabsession.comment.controller;

import java.util.List;
import likelion.collabsession.comment.dto.request.CreateCommentRequest;
import likelion.collabsession.comment.dto.response.CommentResponse;
import likelion.collabsession.comment.service.CommentService;
import likelion.collabsession.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{postId}")
  public ResponseEntity<BaseResponse<CommentResponse>> createComment(
      @PathVariable Long postId,
      @RequestBody CreateCommentRequest request,
      @AuthenticationPrincipal UserDetails userDetails
  ) {
    String username = userDetails.getUsername(); // 로그인한 사용자 이름
    request.setWriter(username); // 댓글 작성자 설정
    request.setPostId(postId);

    CommentResponse response = commentService.createComment(request);
    return ResponseEntity.ok(BaseResponse.success(response));
  }

  @GetMapping("/{postId}")
  public ResponseEntity<BaseResponse<List<CommentResponse>>> getCommentsByPostId(
      @PathVariable Long postId
  ) {
    List<CommentResponse> responses = commentService.getCommentsByPostId(postId);
    return ResponseEntity.ok(BaseResponse.success(responses));
  }
}