package likelion.collabsession.gpt.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import likelion.collabsession.gpt.dto.QuestionData;
import likelion.collabsession.gpt.dto.request.ChatGptRequest;

public class GptSystemPromptFormatter {

  private static final ObjectMapper MAPPER = new ObjectMapper()
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

  public static String buildPrompt(ChatGptRequest request) {
    List<QuestionData> questions = request.getQuestions();

    StringBuilder sb = new StringBuilder();

    sb.append("아래는 특정 수업 게시판에 학생들이 올린 질문들입니다.\n")
        .append("각 질문은 제목, 본문, 댓글로 이루어져 있습니다.\n")
        .append("비슷한 질문은 묶고, 전체적으로 학생들이 궁금해하는 핵심 내용을 요약해 주세요.\n")
        .append("답변은 3줄 이내의 간결한 문장으로 작성해 주세요.\n")
        .append("반드시 아래 JSON 형식으로 응답해 주세요. 설명이나 QnA 형식 없이 JSON만 출력하세요.\n\n");

    // 질문 목록 구성
    for (int i = 0; i < questions.size(); i++) {
      QuestionData q = questions.get(i);
      sb.append("[질문 ").append(i + 1).append("]\n")
          .append("제목: ").append(q.getTitle()).append("\n")
          .append("본문: ").append(q.getBody()).append("\n")
          .append("댓글:\n");

      if (q.getComments() != null && !q.getComments().isEmpty()) {
        for (String comment : q.getComments()) {
          sb.append("- ").append(comment).append("\n");
        }
      } else {
        sb.append("- (댓글 없음)\n");
      }

      sb.append("\n");
    }

    // JSON 예시 명시
    sb.append("응답 형식 예시:\n")
        .append("{\n")
        .append("  \"summary\": \"- 중간고사는 1~5강이며 10월 10일입니다.\\n- 시험은 객관식+주관식 혼합 예정입니다.\\n- 정확한 일정은 메일 공지를 참고하세요.\"\n")
        .append("}");

    return sb.toString();
  }
}