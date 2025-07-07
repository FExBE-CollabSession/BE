package likelion.collabsession.post.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import likelion.collabsession.comment.entity.Comment;
import likelion.collabsession.global.common.BaseTimeEntity;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(nullable = false)
  @Builder.Default
  private int viewCount = 0;

  @Column(nullable = false)
  @Builder.Default
  private boolean isDeleted = false;

  @Column(nullable = false)
  private String writer;

  @Column(nullable = false)
  @Builder.Default
  private int likeCount = 0;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void viewCountUp() {
    this.viewCount += 1;
  }

  public void softDelete() {
    this.isDeleted = true;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public void likeUp() {
    this.likeCount += 1;
  }

  public void likeDown() {
    if (this.likeCount > 0) this.likeCount -= 1;
  }
}