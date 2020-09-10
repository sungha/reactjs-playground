package kr.sungha.boot.jpa.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@JsonPropertyOrder({"id"})
@MappedSuperclass
public abstract class AbstractBaseEntity<U> extends AbstractAuditingEntity<U> {

  @Id
  @GeneratedValue(generator = "base64-uuid")
  @GenericGenerator(name = "base64-uuid", strategy = "kr.sungha.quasar.hibernate.Base64UuidGenerator")
  @Column(name = "id", length = 22, updatable = false, nullable = false, unique = true)
  protected String id;


  @Transient
  @JsonIgnore
  private Object tag;

  public AbstractBaseEntity(String id) {
    this.id = id;
  }

}
