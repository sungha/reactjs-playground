package kr.sungha.user.service;


import kr.sungha.user.entity.User;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;


public interface UserService extends UserDetailsService, AuditorAware<String>, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  User loadUserByUsername(String username) throws UsernameNotFoundException;

}
