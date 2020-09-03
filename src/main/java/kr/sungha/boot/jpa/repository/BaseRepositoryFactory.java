package kr.sungha.boot.jpa.repository;


import java.io.Serializable;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFragment;


/**
 * 기본 리파지토리 빈 생성.
 *
 * @param <T> 엔티티 클래스
 * @param <K> PK 타입
 */
public class BaseRepositoryFactory<T, K extends Serializable> extends JpaRepositoryFactory {

  private final EntityManager em;


  public BaseRepositoryFactory(EntityManager em) {
    super(em);
    this.em = em;
  }


  @Override
  protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
    RepositoryComposition.RepositoryFragments fragments = super.getRepositoryFragments(metadata);

    if (BaseRepositoryCustom.class.isAssignableFrom(metadata.getRepositoryInterface())) {
      JpaEntityInformation<?, Serializable> infomation = getEntityInformation(metadata.getDomainType());
      Object fragment = getTargetRepositoryViaReflection(BaseRepositoryImpl.class, infomation, em);
      fragments = fragments.append(RepositoryFragment.implemented(fragment));
    }

    return fragments;
  }

}
