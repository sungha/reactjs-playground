package kr.sungha.user.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApi {

  private final OAuth2AuthorizedClientService authorizedClientService;

  @GetMapping("/users/me/token")
  public ResponseEntity<Object> currentUserToken(@AuthenticationPrincipal Principal principal) {
    if (principal instanceof OAuth2AuthenticationToken) {
      Map<String, Object> attributes = new HashMap<>();
      OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) principal;

      OAuth2AuthorizedClient oauth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(oauth2AuthenticationToken.getAuthorizedClientRegistrationId(), oauth2AuthenticationToken.getName());
      OAuth2AccessToken accessToken = oauth2AuthorizedClient.getAccessToken();
      OAuth2RefreshToken refreshToken = oauth2AuthorizedClient.getRefreshToken();

      attributes.put("name", oauth2AuthenticationToken.getName());
      attributes.put("accessToken", accessToken);
      attributes.put("refreshToken", refreshToken);
      return ResponseEntity.ok(attributes);
    }

    return ResponseEntity.ok(principal);
  }
}
