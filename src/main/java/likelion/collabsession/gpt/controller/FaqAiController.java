//package likelion.collabsession.gpt.controller;
//
//import likelion.collabsession.gpt.service.FaqAiService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/faq")
//public class FaqAiController {
//
//  private final FaqAiService faqAiService;
//
//  @GetMapping
//  public ResponseEntity<String> getFaq(@RequestParam String keyword) {
//    String faq = faqAiService.getFaqByKeyword(keyword);
//    return ResponseEntity.ok(faq);
//  }
//}
