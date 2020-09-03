package kr.sungha.user.repository;


import kr.sungha.boot.jpa.repository.BaseRepository;
import kr.sungha.user.entity.User;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

  Optional<User> findByEmail(String email);

}
