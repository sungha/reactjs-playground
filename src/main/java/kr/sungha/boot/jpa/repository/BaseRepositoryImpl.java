package kr.sungha.boot.jpa.repository;


import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;


/**
 * BaseRepository 구현체.
 *
 * @param <T> 엔티티 타입
 * @param <K> PK 타입
 */
@SuppressWarnings("all")
@Slf4j
public class BaseRepositoryImpl<T, K extends Serializable> extends QuerydslJpaPredicateExecutor<T> implements BaseRepositoryCustom<T, K> {

  private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

  private final EntityManager em;
  private final EntityPath<T> root;
  private final EntityPath<T> sub;
  private final PathBuilder<T> builder;
  private final Querydsl querydsl;
  private final JPAQueryFactory qf;


  public BaseRepositoryImpl(JpaEntityInformation<T, K> information, EntityManager em) {
    this(information, em, DEFAULT_ENTITY_PATH_RESOLVER);
  }


  /**
   * 팩토리 및 리파지토리 초기설정.
   */
  public BaseRepositoryImpl(JpaEntityInformation<T, K> information, EntityManager em, EntityPathResolver resolver) {
    super(information, em, resolver, null);

    this.em = em;

    this.root = resolver.createPath(information.getJavaType());
    this.builder = new PathBuilder<T>(root.getType(), root.getMetadata());
    this.querydsl = new Querydsl(em, builder);

    this.qf = new JPAQueryFactory(em);
    this.sub = resolver.createPath(information.getJavaType());
  }

//  @Override
//  public String nextId(StringPath field) {
//    String max = qf.from(this.root).where(field.eq(JPAExpressions.select(field.max()).from(sub))).select(field).fetchOne();
//
//    return NumberUtil.increase(max);
//  }
//
//  @Override
//  public String nextId(StringPath field, Predicate predicate) {
//    String max = qf.from(this.root).where(field.eq(JPAExpressions.select(field.max()).from(sub).where(predicate))).select(field).fetchOne();
//
//    return NumberUtil.increase(max);
//  }

  @Override
  public <N extends Number & Comparable<?>> N next(NumberExpression<N> field) {
    return qf.from(this.root).where(field.eq(JPAExpressions.select(field.max()).from(sub))).select(field.add(1)).fetchOne();
  }


  @Override
  public String max(StringPath field) {
    return qf.from(this.root).select(field.max()).fetchOne();
  }

  @Override
  public String max(StringPath field, Predicate predicate) {
    return qf.from(this.root).where(field.eq(JPAExpressions.select(field.max()).from(sub))).select(field).fetchOne();
  }


  @Override
  public <X> X top(Path<X> field, Predicate predicate, OrderSpecifier<?>... orders) {
    return qf.from(this.root).where(predicate).select(field).orderBy(orders).fetchFirst();
  }

  @Override
  public <X> X findOne(Path<X> field, Predicate predicate) {
    return qf.from(this.root).where(predicate).select(field).fetchOne();
  }

  @Override
  public T findOne(Predicate predicate, Path<?>... fields) {
    return qf.from(this.root).where(predicate).select(Projections.bean(this.root, fields)).fetchOne();
  }

  @Override
  public <X> List<X> findAll(Path<X> path, Predicate predicate) {
    return qf.from(this.root).where(predicate).select(path).fetch();
  }

  @Override
  public List<T> findAll(FactoryExpression<T> expression, Predicate predicate) {
    return qf.from(this.root).where(predicate).select(expression).fetch();
  }

  @Override
  public List<T> distinct(FactoryExpression<T> expression, Predicate predicate) {
    return qf.from(this.root).distinct().where(predicate).select(expression).fetch();
  }

  @Override
  public <X> long update(Path<X> field, X value, Predicate predicate) {
    return qf.update(this.root).set(field, value).where(predicate).execute();
  }

  @Override
  public <X> long update(Path<X> field, Expression<X> expression, Predicate predicate) {
    return qf.update(this.root).set(field, expression).where(predicate).execute();
  }

  @Override
  public long deleteAll(Predicate predicate) {
    return qf.delete(this.root).where(predicate).execute();
  }

}
