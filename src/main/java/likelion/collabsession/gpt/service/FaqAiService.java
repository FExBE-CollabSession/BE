package likelion.collabsession.gpt.service;

import java.util.List;
import likelion.collabsession.gpt.dto.QuestionData;
import likelion.collabsession.gpt.dto.request.ChatGptRequest;
import likelion.collabsession.comment.entity.Comment;
import likelion.collabsession.post.entity.Post;
import likelion.collabsession.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FaqAiService {

  private final PostRepository postRepository;
  private final ChatGptService chatGptService;

  public String getFaqByKeyword(String keyword) {
    List<Post> posts = postRepository.findTop3ByTitleKeyword(keyword, PageRequest.of(0, 3));

    List<QuestionData> questions = posts.stream().map(post ->
        new QuestionData(
            post.getTitle(),
            post.getContent(), // ✅ getBody() → getContent()
            post.getComments().stream()
                .map(Comment::getContent) // ✅ CommentEntity → Comment
                .toList()
        )
    ).toList();

    ChatGptRequest request = new ChatGptRequest();
    request.setQuestions(questions); // 생성자 대신 setter 사용 중이면 이대로도 OK

    return chatGptService.getChatGptResponse(request).getSummary();
  }
}
