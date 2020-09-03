package kr.sungha.configuration;


import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class DefaultConfiguration {

  private final EntityManager em;

  /**
   * 기본 패스워드 인코더.
   */
  @Bean("passwordEncoder")
  public PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();

    // 개발계 데이타라서 비밀번호 암호화 안함.
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence charSequence) {
        return charSequence.toString();
      }

      @Override
      public boolean matches(CharSequence charSequence, String string) {
        return StringUtils.equals(charSequence, string);
      }
    };
  }


  /**
   * JPA 쿼리 생성기.
   */
  @Bean
  public JPAQueryFactory queryFactory() {
    return new JPAQueryFactory(em);
  }


}
