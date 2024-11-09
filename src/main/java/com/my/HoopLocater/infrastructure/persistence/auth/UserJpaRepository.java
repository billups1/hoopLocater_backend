package com.my.HoopLocater.infrastructure.persistence.auth;


import com.my.HoopLocater.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User save(User entity);

    boolean existsByLoginId(String loginId);
    boolean existsByNickName(String nickName);

    Optional<User> findByLoginId(String name);
}