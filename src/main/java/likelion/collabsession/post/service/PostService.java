package likelion.collabsession.post.service;

import java.util.List;
import java.util.stream.Collectors;
import likelion.collabsession.post.dto.request.CreatePostRequest;
import likelion.collabsession.post.dto.request.UpdatePostRequest;
import likelion.collabsession.post.dto.response.PostResponse;
import likelion.collabsession.post.entity.Course;
import likelion.collabsession.post.entity.Post;
import likelion.collabsession.post.repository.CourseRepository;
import likelion.collabsession.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final CourseRepository courseRepository;

  public PostResponse createPost(Long courseId, CreatePostRequest request) {
    log.info("[createPost] 수업 ID: {}, 제목: {}", courseId, request.getTitle());

    Course course = findCourse(courseId);
    Post post = Post.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .course(course)
        .writer(request.getWriter())
        .build();

    Post saved = postRepository.save(post);
    log.info("[createPost] 게시글 생성 완료 - ID: {}", saved.getId());
    return PostResponse.from(saved);
  }

  public List<PostResponse> getAllPosts(Long courseId) {
    log.info("[getAllPosts] 수업 ID: {}", courseId);
    return postRepository.findAllByCourseIdAndIsDeletedFalse(courseId).stream()
        .map(PostResponse::from)
        .collect(Collectors.toList());
  }

  public PostResponse getPostById(Long courseId, Long postId) {
    log.info("[getPostById] 수업 ID: {}, 게시글 ID: {}", courseId, postId);
    Post post = findPost(courseId, postId);
    post.viewCountUp(); // 조회수 증가
    log.info("[getPostById] 게시글 ID: {} → 조회수 +1, 현재: {}", postId, post.getViewCount());
    return PostResponse.from(post);
  }

  public PostResponse updatePost(Long courseId, Long postId, UpdatePostRequest request) {
    log.info("[updatePost] 수업 ID: {}, 게시글 ID: {}, 새로운 제목: {}", courseId, postId, request.getTitle());
    Post post = findPost(courseId, postId);
    post.update(request.getTitle(), request.getContent());
    log.info("[updatePost] 게시글 수정 완료 - ID: {}", postId);
    return PostResponse.from(post);
  }

  public Boolean deletePost(Long courseId, Long postId) {
    log.info("[deletePost] 수업 ID: {}, 게시글 ID: {}", courseId, postId);
    Post post = findPost(courseId, postId);
    post.softDelete();
    log.info("[deletePost] 게시글 soft delete 처리 완료 - ID: {}", postId);
    return true;
  }

  public List<PostResponse> getPostsOrderByCreatedAtDesc(Long courseId) {
    log.info("[getPostsOrderByCreatedAtDesc] 수업 ID: {}", courseId);
    return postRepository.findAllByCourseIdAndIsDeletedFalseOrderByCreatedAtDesc(courseId).stream()
        .map(PostResponse::from)
        .collect(Collectors.toList());
  }

  public List<PostResponse> getPostsOrderByViewCountDesc(Long courseId) {
    log.info("[getPostsOrderByViewCountDesc] 수업 ID: {}", courseId);
    return postRepository.findAllByCourseIdAndIsDeletedFalseOrderByViewCountDesc(courseId).stream()
        .map(PostResponse::from)
        .collect(Collectors.toList());
  }

  public PostResponse likePost(Long courseId, Long postId) {
    log.info("[likePost] 수업 ID: {}, 게시글 ID: {}", courseId, postId);
    Post post = findPost(courseId, postId);
    post.likeUp();
    log.info("[likePost] 게시글 ID: {} → 좋아요 +1, 현재: {}", postId, post.getLikeCount());
    return PostResponse.from(post);
  }

  public PostResponse unlikePost(Long courseId, Long postId) {
    log.info("[unlikePost] 수업 ID: {}, 게시글 ID: {}", courseId, postId);
    Post post = findPost(courseId, postId);
    post.likeDown();
    log.info("[unlikePost] 게시글 ID: {} → 좋아요 -1, 현재: {}", postId, post.getLikeCount());
    return PostResponse.from(post);
  }

  private Course findCourse(Long courseId) {
    return courseRepository.findById(courseId)
        .orElseThrow(() -> {
          log.warn("[findCourse] 수업 ID {} 없음", courseId);
          return new IllegalArgumentException("해당 수업(courseId=" + courseId + ")이 존재하지 않습니다.");
        });
  }

  private Post findPost(Long courseId, Long postId) {
    return postRepository.findByIdAndCourseIdAndIsDeletedFalse(postId, courseId)
        .orElseThrow(() -> {
          log.warn("[findPost] 수업 ID {}, 게시글 ID {} 없음", courseId, postId);
          return new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        });
  }
}