package likelion.collabsession.user.service;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import likelion.collabsession.global.exception.CustomException;
import likelion.collabsession.post.entity.Course;
import likelion.collabsession.post.repository.CourseRepository;
import likelion.collabsession.user.dto.request.SignUpRequest;
import likelion.collabsession.user.dto.request.UpdateEmailRequest;
import likelion.collabsession.user.dto.request.UpdatePasswordRequest;
import likelion.collabsession.user.dto.request.UpdateUsernameRequest;
import likelion.collabsession.user.dto.response.SignUpResponse;
import likelion.collabsession.user.dto.response.UpdateEmailResponse;
import likelion.collabsession.user.dto.response.UpdatePasswordResponse;
import likelion.collabsession.user.dto.response.UpdateUsernameResponse;
import likelion.collabsession.user.entity.User;
import likelion.collabsession.user.entity.UserCourse;
import likelion.collabsession.user.exception.UserErrorCode;
import likelion.collabsession.user.mapper.UserMapper;
import likelion.collabsession.user.repository.UserCourseRepository;
import likelion.collabsession.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Schema
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;
  private final CourseRepository courseRepository;
  private final UserCourseRepository userCourseRepository;

  // 회원가입
  public SignUpResponse signUp(SignUpRequest request) {

    // 이메일 중복 검사
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new CustomException(UserErrorCode.EMAIL_ALREADY_EXISTS);
    }

    // 비밀번호 인코딩
    String encodedPassword = passwordEncoder.encode(request.getPassword());

    // 유저 엔티티 생성
    User user = User.builder()
        .email(request.getEmail())
        .password(encodedPassword)
        .username(request.getUsername())
        .password(encodedPassword)
        .build();

    // 저장 및 로깅
    User savedUser = userRepository.save(user);
    log.info("New user registered: {}", savedUser.getUsername());

    return userMapper.toSignUpResponse(savedUser);
  }

  // 이메일 (아이디) 변경
  public UpdateEmailResponse updateEmail(Long userId, UpdateEmailRequest request) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    if (!user.getEmail().equals(request.getEmail()) &&
        userRepository.existsByEmail(request.getEmail())) {
      log.warn("Update email failed: Email already in use - newEmail={}", request.getEmail());
      throw new CustomException(UserErrorCode.EMAIL_ALREADY_EXISTS);
    }

    user.updateEmail(request.getEmail());
    log.info("Email updated: userId={}, newEmail={}", userId, user.getEmail());

    userRepository.save(user);

    return UpdateEmailResponse.builder()
        .username(user.getUsername())
        .newEmail(user.getEmail())
        .build();
  }

  // 비밀번호 변경
  @Transactional
  public UpdatePasswordResponse updatePassword(Long userId, UpdatePasswordRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
      throw new CustomException(UserErrorCode.INVALID_PASSWORD);
    }

    user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    log.info("Password updated successfully: userId={}", userId);

    return UpdatePasswordResponse.builder()
        .username(user.getUsername())
        .updated(true)
        .build();
  }

  // 이름(별명) 변경
  @Transactional
  public UpdateUsernameResponse updateUsername(Long userId, UpdateUsernameRequest request) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    user.updateUsername(request.getUsername());
    log.info("Username updated: userId={}, newUsername={}", userId, user.getUsername());

    return UpdateUsernameResponse.builder()
        .username(user.getUsername())
        .build();
  }

  @Transactional
  public void addCourseToUser(Long userId, Long courseId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new CustomException(UserErrorCode.COURSE_NOT_FOUND));

    // 중복 수강 체크
    boolean alreadyExists = userCourseRepository.existsByUserAndCourse(user, course);
    if (alreadyExists) {
      throw new CustomException(UserErrorCode.ALREADY_ENROLLED);
    }

    UserCourse userCourse = UserCourse.builder()
        .user(user)
        .course(course)
        .addedAt(LocalDateTime.now())
        .build();

    userCourseRepository.save(userCourse);
  }
}