package likelion.collabsession.post.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Schema(description = "수업(게시판 단위) 엔티티")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "수업 ID", example = "1")
  private Long id;

  @Column(nullable = false)
  @Schema(description = "수업 이름", example = "객체지향프로그래밍")
  private String name;

  @Column(nullable = false)
  @Schema(description = "교수명", example = "김교수")
  private String professor;

  @Column(nullable = false)
  @Schema(description = "수업 요일", example = "MONDAY")
  private String lectureDay;

  @Column(nullable = false)
  @Schema(description = "수업 시작 시간", example = "14:00")
  private LocalTime startTime;

  @Column(nullable = false)
  @Schema(description = "수업 종료 시간", example = "15:15")
  private LocalTime endTime;

  @Column(nullable = false)
  @Schema(description = "강의실", example = "북악관 608호")
  private String classroom;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  @Schema(description = "수업에 속한 게시글 목록")
  private List<Post> posts = new ArrayList<>();

  public void addPost(Post post) {
    posts.add(post);
    post.setCourse(this);
  }
}