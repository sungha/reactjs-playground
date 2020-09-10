package kr.sungha.boot.jpa.entity;


import kr.sungha.boot.jpa.converter.YmdhmsConverter;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@JsonAutoDetect
public abstract class AbstractAuditingEntity<U> {

  @CreatedDate
  @Column(name = "created", length = 14)
  @Convert(converter = YmdhmsConverter.class)
  //  @JsonSerialize(using = DateTimeSerializer.class)
  //  @JsonDeserialize(using = DateTimeDeserializer.class)
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmssSSS", timezone = "Asia/Seoul")
  private LocalDateTime created;

  @CreatedBy
  @ManyToOne
  @JoinColumn(name = "creator", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private U creator;

  @LastModifiedDate
  @Column(name = "modified", length = 14)
  @Convert(converter = YmdhmsConverter.class)
  @JsonIgnore
  private LocalDateTime modified;

  @LastModifiedBy
  @ManyToOne
  @JoinColumn(name = "modifier", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @JsonIgnore
  private U modifier;

}
