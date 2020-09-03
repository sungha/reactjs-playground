package kr.sungha.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 웹 설정.
 */
@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

  @Bean
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }

}
