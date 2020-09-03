package kr.sungha.boot.jpa.repository;

import java.util.List;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepositoryCustom<T, K> extends Repository<T, K> {

  //  /**
//   * [A-Za-z_-]-?[0-9]+ 형식의 일련번호 타입일 때 다음 시퀀스값을 구한다.
//   *
//   * @param field 컬럼 정의
//   * @return 해당 필드의 최대값
//   */
//  String nextId(StringPath field);
//
//  /**
//   * [A-Za-z_-]-?[0-9]+ 형식의 일련번호 타입일 때 다음 시퀀스값을 구한다.
//   *
//   * @param field     컬럼 정의
//   * @param predicate 조건
//   * @return 해당 필드의 최대값
//   */
//  String nextId(StringPath field, Predicate predicate);

  /**
   * 숫자 형식 필드의 최대값+1을 구한다.
   *
   * @param field 컬럼 정의
   * @return 해당 필드의 최대값
   */
  <N extends Number & Comparable<?>> N next(NumberExpression<N> field);


  /**
   * 필드의 최대값 조회.
   *
   * @param field 컬럼 정의
   * @return 해당 필드의 최대값
   */
  String max(StringPath field);

  /**
   * 필드의 최대값 조회.
   *
   * @param field     컬럼 정의
   * @param predicate 조건
   * @return 조건에 맞는 값
   */
  String max(StringPath field, Predicate predicate);

  /**
   * 단일 컬럼만 셀렉트해서 조회.
   *
   * @param field     컬럼 정의
   * @param predicate 조건
   * @return 조건에 맞는 값
   */
  <X> X top(Path<X> field, Predicate predicate, OrderSpecifier<?>... orders);

  /**
   * 단일 컬럼만 셀렉트해서 조회.
   *
   * @param field     컬럼 정의
   * @param predicate 조건
   * @return 조건에 맞는 값
   */
  <X> X findOne(Path<X> field, Predicate predicate);

  /**
   * 특정 컬럼만 셀렉트해서 조회.
   *
   * @param fields    컬럼 정의
   * @param predicate 조건
   * @return 조건에 맞는 값
   */
  T findOne(Predicate predicate, Path<?>... fields);

  /**
   * 단일 컬럼만 셀렉트해서 조회.
   *
   * @param field     컬럼 정의
   * @param predicate 조건
   * @return 조건에 맞는 값 리스트
   */
  <X> List<X> findAll(Path<X> field, Predicate predicate);

  /**
   * 특정 컬럼만 셀렉트해서 조회.
   *
   * @param expression 컬럼 정의
   * @param predicate  조건
   * @return 조건에 맞는 행
   */
  List<T> findAll(FactoryExpression<T> expression, Predicate predicate);

  /**
   * 특정 컬럼만 셀렉트해서 조회.
   *
   * @param expression 컬럼 정의
   * @param predicate  조건
   * @return 조건에 맞는 행
   */
  List<T> distinct(FactoryExpression<T> expression, Predicate predicate);

  /**
   * 필드 단일항목 업데이트.
   *
   * @param field     업데이트할 필드
   * @param value     업데이트할 값
   * @param predicate 업데이트 조건
   * @param <X>       업데이트 필드 타입
   */
  @Modifying
  <X> long update(Path<X> field, X value, Predicate predicate);

  /**
   * 필드 항목을 다른 필드의 값으로 대체.
   *
   * @param field      업데이트할 필드
   * @param expression 업데이트할 값
   * @param predicate  업데이트 조건
   * @param <X>        업데이트 필드 타입
   */
  @Modifying
  <X> long update(Path<X> field, Expression<X> expression, Predicate predicate);

  /**
   * 조건에 맞는 모든 행 삭제.
   *
   * @param predicate 삭제 조건
   */
  @Modifying
  long deleteAll(Predicate predicate);


}
