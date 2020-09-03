package kr.sungha.boot.jpa.repository;


import org.springframework.data.repository.NoRepositoryBean;

/**
 * 자동 UUID PK 생성 Repository.
 *
 * @param <T> 엔티티 타입
 */
@NoRepositoryBean
public interface BaseUuidRepository<T> extends BaseRepository<T, String> {

}
