package kr.sungha.user.service;


import kr.sungha.user.entity.QUser;
import kr.sungha.user.entity.User;
import kr.sungha.user.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final QUser qu = QUser.user;

  private final UserRepository userRepository;
  private final HttpSession httpSession;


  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findOne(qu.username.eq(username));
    if (!user.isPresent()) {
      throw new UsernameNotFoundException(username);
    }
    return user.get();
  }

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (null == authentication || !authentication.isAuthenticated()) {
      return Optional.empty();
    }
    User auditor = (User) authentication.getPrincipal();
    if (auditor != null) {
      return Optional.of(auditor.getId());
    }
    return Optional.empty();
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oauth2User = delegate.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oauth2User.getAttributes());

    User user = saveOrUpdate(attributes);
    httpSession.setAttribute("user", user);

    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
        attributes.getAttributes(),
        attributes.getNameAttributeKey());
  }

  private User saveOrUpdate(OAuthAttributes attributes) {
    User user = userRepository.findByEmail(attributes.getEmail())
        .map(entity -> entity.update(attributes.getRealname()))
        .orElse(attributes.toEntity());

    return userRepository.save(user);
  }
}
