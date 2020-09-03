package kr.sungha;


import kr.sungha.config.App;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;


@SpringBootApplication
@EnableConfigurationProperties({App.class})
public class ReactJsPlaygroundApplication {

  private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
  private static final String DEFAULT_PROFILE = "local";


  public static void main(String[] args) {
    String profiles = System.getProperty(SPRING_PROFILES_ACTIVE);
    if (StringUtils.isEmpty(profiles)) {
      System.setProperty(SPRING_PROFILES_ACTIVE, DEFAULT_PROFILE);
    }

    new SpringApplicationBuilder().beanNameGenerator(new AnnotationBeanNameGenerator() {
      @Override
      protected String buildDefaultBeanName(BeanDefinition definition) {
        return definition.getBeanClassName();
      }
    }).sources(ReactJsPlaygroundApplication.class).run(args);
  }


}
