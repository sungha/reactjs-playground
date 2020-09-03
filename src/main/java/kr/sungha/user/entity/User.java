package kr.sungha.user.entity;


import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 사용자 테이블.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"relTargetType", "passwd", "authorities", "dptId", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
//@ToString(exclude = {"passwd", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  /** 아이디. */
  @Id
  @Column(name = "ID", length = 20, nullable = false, updatable = false)
  private String id;

  /** 사용자아이디. */
  @Column(name = "USRRNAME", length = 20, nullable = false, updatable = false)
  private String username;

  /** 암호. */
  @JsonIgnore
  @Column(name = "PASSWORD", length = 40, nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  /** 실명. */
  @Column(name = "REALNAME", length = 50, nullable = false)
  private String realname;

  @Transient
  private boolean enabled = true;
  @Transient
  private boolean accountNonExpired = true;
  @Transient
  private boolean accountNonLocked = true;
  @Transient
  private boolean credentialsNonExpired = true;


  @Enumerated(EnumType.STRING)
  @JsonIgnore
  @Column(nullable = false)
  private Role role;

  public User(String realname, String email, Role role) {
    this.realname = realname;
    this.email = email;
    this.role = role;
  }

  public User update(String realname) {
    this.realname = realname;
    return this;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }
}
