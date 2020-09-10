package kr.sungha.boot.jpa.entity;

import kr.sungha.boot.jpa.converter.YmdhmsWithDashConverter;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
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
  @Column
  private String creator;

  /** 작성일. */
  @CreatedDate
  @Column(length = 20)
  @Convert(converter = YmdhmsWithDashConverter.class)
  private LocalDateTime created;

  /** 수정자. */
  @LastModifiedBy
  @Column
  private String modifier;

  /** 수정일. */
  @LastModifiedDate
  @Column(length = 20)
  @Convert(converter = YmdhmsWithDashConverter.class)
  private LocalDateTime modified;

}
