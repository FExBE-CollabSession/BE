package likelion.collabsession.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import likelion.collabsession.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "PostResponse: 게시글 응답 DTO")
public class PostResponse {

  @Schema(description = "게시글 ID", example = "1")
  private Long id;

  @Schema(description = "게시글 제목", example = "5주차 세션")
  private String title;

  @Schema(description = "게시글 내용", example = "이번주 세션도 화이팅!")
  private String content;

  @Schema(description = "작성자", example = "홍길동")
  private String writer;

  @Schema(description = "좋아요 수", example = "5")
  private int likeCount;

  @Schema(description = "조회수", example = "42")
  private int viewCount;

  @Schema(description = "게시글 생성 시각", example = "2025-07-07T13:45:00")
  private LocalDateTime createdAt;

  @Schema(description = "게시글이 속한 수업 ID", example = "101")
  private Long courseId;

  public static PostResponse from(Post post) {
    return PostResponse.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .writer(post.getWriter())
        .viewCount(post.getViewCount())
        .createdAt(post.getCreatedAt())
        .likeCount(post.getLikeCount())
        .courseId(post.getCourse().getId())
        .build();
  }
}