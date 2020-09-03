package kr.sungha.boot.jpa.repository;


import java.io.Serializable;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 기본 리파지토리 생성 빈.
 *
 * @param <R> 리파지토리 인터페이스
 * @param <T> 엔티티타입
 * @param <K> PK 타입
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, K>, T, K extends Serializable> extends JpaRepositoryFactoryBean<R, T, K> {

  public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
    super(repositoryInterface);
  }

  @Override
  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new BaseRepositoryFactory<T, K>(entityManager);
  }

}
