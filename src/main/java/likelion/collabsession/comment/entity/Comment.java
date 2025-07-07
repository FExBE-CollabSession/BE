package likelion.collabsession.comment.entity;

import jakarta.persistence.*;
import likelion.collabsession.global.common.BaseTimeEntity;
import likelion.collabsession.post.entity.Post;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Column(nullable = false)
  private String writer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Comment parent;

  @Builder.Default
  @Column(nullable = false)
  private boolean isDeleted = false;

  public void softDelete() {
    this.isDeleted = true;
  }
}
