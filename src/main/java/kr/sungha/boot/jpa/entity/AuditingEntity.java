package kr.sungha.boot.jpa.entity;

import kr.sungha.boot.jpa.converter.YmdhmsWithDashConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 작성자/작성일/수정자/수정일 공통 필드.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /** 작성자. */
  @CreatedBy
  @Column(length = 8)
  private String frtuser;

  /** 작성일. */
  @CreatedDate
  @Column(length = 20)
  @Convert(converter = YmdhmsWithDashConverter.class)
  private DateTime frtdt;

  /** 수정자. */
  @LastModifiedBy
  @Column(length = 8)
  private String lstuser;

  /** 수정일. */
  @LastModifiedDate
  @Column(length = 20)
  @Convert(converter = YmdhmsWithDashConverter.class)
  private DateTime lstdt;

}
