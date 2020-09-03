package kr.sungha.configuration;

import kr.sungha.ReactJsPlaygroundApplication;
import kr.sungha.boot.jpa.repository.BaseRepositoryFactoryBean;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackageClasses = {ReactJsPlaygroundApplication.class}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EntityScan(basePackageClasses = {ReactJsPlaygroundApplication.class})
@EnableJpaAuditing(auditorAwareRef = "userService")
@EnableTransactionManagement
public class JpaConfiguration {
}
