package likelion.collabsession.user.mapper;

import likelion.collabsession.user.dto.response.SignUpResponse;
import likelion.collabsession.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public SignUpResponse toSignUpResponse(User user) {
    return SignUpResponse.builder()
        .userId(user.getId())
        .username(user.getUsername())
        .build();
  }
}
