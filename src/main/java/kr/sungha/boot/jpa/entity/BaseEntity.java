package kr.sungha.boot.jpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 작성자/작성일/수정자/수정일 공통 필드.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 아이디. */
  @Id
  @Column(length = 20, nullable = false, updatable = false)
  private String id;

}
