package likelion.collabsession.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import likelion.collabsession.global.response.BaseResponse;
import likelion.collabsession.global.security.CustomUserDetails;
import likelion.collabsession.post.dto.request.UpdatePostRequest;
import likelion.collabsession.post.dto.request.CreatePostRequest;
import likelion.collabsession.post.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import likelion.collabsession.post.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Tag(name = "Post", description = "게시글 관련 API")
public class PostController {

  private final PostService postService;

  @Operation(summary = "게시글 생성", description = "게시글 생성 버튼 누르면 게시글 생성")
  @PostMapping("/{courseId}")
  public ResponseEntity<BaseResponse<PostResponse>> createPost(
      @Parameter(description = "수업 ID") @PathVariable Long courseId,
      @Parameter(description = "게시글 작성 내용") @RequestBody CreatePostRequest createPostRequest,
      @AuthenticationPrincipal CustomUserDetails userDetails
      ) {

    Long userId = userDetails.getUser().getId();

    PostResponse response = postService.createPost(courseId, createPostRequest, userId);
    return ResponseEntity.ok(BaseResponse.success("게시글 생성 성공", response));
  }

  @Operation(summary = "게시글 수정", description = "제목, 내용 수정 가능")
  @PutMapping("/{courseId}/{id}")
  public ResponseEntity<BaseResponse<PostResponse>> updatePost(
      @Parameter(description = "수업 ID") @PathVariable Long courseId,
      @Parameter(description = "게시글 ID") @PathVariable Long id,
      @Parameter(description = "게시글 수정 내용") @RequestBody @Valid UpdatePostRequest updatePostRequest) {

    PostResponse response = postService.updatePost(courseId, id, updatePostRequest);
    return ResponseEntity.ok(BaseResponse.success(id + "번 게시글 수정 성공", response));
  }

  @Operation(summary = "게시글 삭제", description = "soft delete")
  @DeleteMapping("/{courseId}/{id}")
  public ResponseEntity<BaseResponse<Boolean>> deletePost(
      @Parameter(description = "수업 ID") @PathVariable Long courseId,
      @Parameter(description = "게시글 ID") @PathVariable Long id) {

    Boolean response = postService.deletePost(courseId, id);
    return ResponseEntity.ok(BaseResponse.success(id + "번 게시글 삭제 성공", response));
  }

  @Operation(summary = "전체 게시글 조회", description = "특정 게시판의 전체 게시글 조회")
  @GetMapping("/{courseId}")
  public ResponseEntity<BaseResponse<List<PostResponse>>> getAllPosts(
      @Parameter(description = "수업 ID") @PathVariable Long courseId) {

    List<PostResponse> responses = postService.getAllPosts(courseId);
    return ResponseEntity.ok(BaseResponse.success("게시글 전체 조회 성공", responses));
  }

  @Operation(summary = "게시글 단일 조회", description = "특정 게시판의 특정 게시글 조회")
  @GetMapping("/{courseId}/{id}")
  public ResponseEntity<BaseResponse<PostResponse>> getPostById(
      @Parameter(description = "수업 ID") @PathVariable Long courseId,
      @Parameter(description = "게시글 ID") @PathVariable Long id) {

    PostResponse response = postService.getPostById(courseId, id);
    return ResponseEntity.ok(BaseResponse.success(id + "번 게시글 조회 성공", response));
  }

  @Operation(summary = "게시글 최신순 조회", description = "게시판에 올려진 게시글을 최신순으로 조회")
  @GetMapping("/{courseId}/latest")
  public ResponseEntity<BaseResponse<List<PostResponse>>> getPostsOrderByCreatedAtDesc(
      @Parameter(description = "수업 ID") @PathVariable Long courseId) {

    List<PostResponse> responses = postService.getPostsOrderByCreatedAtDesc(courseId);
    return ResponseEntity.ok(BaseResponse.success("최신순 조회 성공", responses));
  }

  @Operation(summary = "게시글 조회수순 조회", description = "게시판에 올려진 게시글을 조회 많은 순으로 조회")
  @GetMapping("/{courseId}/popular")
  public ResponseEntity<BaseResponse<List<PostResponse>>> getPostsOrderByViewCountDesc(
      @Parameter(description = "수업 ID") @PathVariable Long courseId) {

    List<PostResponse> responses = postService.getPostsOrderByViewCountDesc(courseId);
    return ResponseEntity.ok(BaseResponse.success("조회수순 조회 성공", responses));
  }

  @Operation(summary = "게시글 좋아요", description = "게시글에 좋아요를 1 증가")
  @PatchMapping("/{courseId}/{postId}/like")
  public ResponseEntity<BaseResponse<PostResponse>> likePost(
      @Parameter(description = "수업 ID") @PathVariable Long courseId,
      @Parameter(description = "게시글 ID") @PathVariable Long postId) {

    PostResponse response = postService.likePost(courseId, postId);
    return ResponseEntity.ok(BaseResponse.success("좋아요 추가 성공", response));
  }

  @Operation(summary = "게시글 좋아요 취소", description = "게시글의 좋아요를 1 감소(0 미만은 불가)")
  @PatchMapping("/{courseId}/{postId}/unlike")
  public ResponseEntity<BaseResponse<PostResponse>> unlikePost(
      @Parameter(description = "수업 ID") @PathVariable Long courseId,
      @Parameter(description = "게시글 ID") @PathVariable Long postId) {

    PostResponse response = postService.unlikePost(courseId, postId);
    return ResponseEntity.ok(BaseResponse.success("좋아요 취소 성공", response));
  }
}