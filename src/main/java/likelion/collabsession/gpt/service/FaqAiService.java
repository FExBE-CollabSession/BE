//package likelion.collabsession.gpt.service;
//
//import java.util.List;
//import likelion.collabsession.gpt.dto.QuestionData;
//import likelion.collabsession.gpt.dto.request.ChatGptRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class FaqAiService {
//
//  private final QuestionRepository questionRepository;
//  private final ChatGptService chatGptService;
//
//  public String getFaqByKeyword(String keyword) {
//    List<QuestionEntity> entities = questionRepository
//        .findTopByTitleKeyword(keyword, PageRequest.of(0, 3));
//
//    List<QuestionData> questions = entities.stream().map(entity ->
//        new QuestionData(
//            entity.getTitle(),
//            entity.getBody(),
//            entity.getComments().stream()
//                .map(CommentEntity::getContent)
//                .toList()
//        )
//    ).toList();
//
//    ChatGptRequest request = new ChatGptRequest();
//    request.setQuestions(questions);
//
//    return chatGptService.getChatGptResponse(request).getAnswer(); // GPT 응답 텍스트
//  }
//}
