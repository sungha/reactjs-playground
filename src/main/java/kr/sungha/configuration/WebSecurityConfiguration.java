package kr.sungha.configuration;


import kr.sungha.user.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 웹 보안 설정.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  /**
   * @param passwordEncoder 패스워드 인코더
   * @param userService     사용자 식별 서비스
   */
  @Autowired
  public WebSecurityConfiguration(PasswordEncoder passwordEncoder, UserService userService) {
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Bean
  public HttpFirewall defaultHttpFirewall() {
    return new DefaultHttpFirewall();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public static ServletListenerRegistrationBean<HttpSessionListener> httpSessionEventPublisher() {
    return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
  }

  @Autowired
  public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }


  @Override
  public void configure(WebSecurity web) {
    //@formatter:off
    web.ignoring()
        .antMatchers("/favicon.ico", "/static/**") // 정적파일들.
        .antMatchers("/*.ico", "/*.js", "/*.css") // 정적파일들.
        .antMatchers("/api/**") // 외부 API.
    ;
    //@formatter:on
  }


  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    //    http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

//    http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

    http.csrf().disable();

    http.headers()
        .frameOptions().disable()
        .addHeaderWriter(new StaticHeadersWriter("X-UA-Compatible", "IE=edge,chrome=1"));


//    http.oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint(
//        userInfoEndpoint -> userInfoEndpoint.customUserType(KakaoOAuth2User.class, "kakao")
//    ).loginPage("/login"));

    http.oauth2Login().userInfoEndpoint().userService(userService);

    http.anonymous()
        .principal("anonymous");

    http.authorizeRequests()
        .antMatchers("/", "/login", "/logout").permitAll()
        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll() // permit all '/management' (spring boot actuator)
        .antMatchers("/api/**").permitAll()
//        .anyRequest().fullyAuthenticated();
        .anyRequest().permitAll();

    http.formLogin()
        .loginPage("/")
        .loginProcessingUrl("/login")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/", true)
        .failureUrl("/?error")
        .permitAll();

    http.sessionManagement()
        .sessionFixation()
        .none()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true)
        .sessionRegistry(sessionRegistry())
        .expiredUrl("/login?error");

    http.logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .permitAll();

    // @formatter:on
  }

  /**
   * CORS 허용 정의.
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    // configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }


}
