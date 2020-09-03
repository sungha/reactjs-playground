package kr.sungha.user.service;

import kr.sungha.user.entity.Role;
import kr.sungha.user.entity.User;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthAttributes {
  private final Map<String, Object> attributes;
  private final String nameAttributeKey;
  private final String realname;
  private final String email;

  public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String realname, String email) {
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.realname = realname;
    this.email = email;
  }

  public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
    if ("naver".equals(registrationId)) {
      return ofNaver("id", attributes);
    }

    return ofGoogle(userNameAttributeName, attributes);
  }

  private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
    return OAuthAttributes.builder()
        .realname((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  @SuppressWarnings("unchecked")
  private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");

    return OAuthAttributes.builder()
        .realname((String) response.get("name"))
        .email((String) response.get("email"))
        // .picture((String) response.get("profile_image"))
        .attributes(response)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  public User toEntity() {
    return User.builder()
        .realname(realname)
        .email(email)
        .role(Role.GUSET)
        .build();
  }
}
