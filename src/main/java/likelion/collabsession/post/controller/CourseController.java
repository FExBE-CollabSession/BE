package likelion.collabsession.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import likelion.collabsession.global.response.BaseResponse;
import likelion.collabsession.global.security.CustomUserDetails;
import likelion.collabsession.post.dto.response.CourseDetailResponse;
import likelion.collabsession.post.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Course", description = "수업 관련 API")
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @Operation(summary = "전체 수업 목록 조회", description = "DB에 있는 모든 수업 반환")
  @GetMapping
  public ResponseEntity<BaseResponse<List<CourseDetailResponse>>> getAllCourses() {
    List<CourseDetailResponse> courses = courseService.getAllCourses();
    return ResponseEntity.ok(BaseResponse.success("수업 목록 조회 성공", courses));
  }

  @Operation(summary = "사용자가 등록한 수업 목록 조회", description = "현재 로그인한 사용자가 등록한 수업 목록을 반환")
  @GetMapping("/my")
  public ResponseEntity<BaseResponse<List<CourseDetailResponse>>> getMyCourses(
      @AuthenticationPrincipal CustomUserDetails userDetails) {

    Long userId = userDetails.getUser().getId();
    List<CourseDetailResponse> myCourses = courseService.getCoursesByUser(userId);
    return ResponseEntity.ok(BaseResponse.success("나의 수업 목록 조회 성공", myCourses));
  }
}