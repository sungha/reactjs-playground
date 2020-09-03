package kr.sungha.boot.jpa.repository;


import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<T, K extends Serializable> extends JpaRepository<T, K>, QuerydslPredicateExecutor<T>, JpaSpecificationExecutor<T>, BaseRepositoryCustom<T, K> {


}
