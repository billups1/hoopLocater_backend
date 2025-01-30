package com.my.HoopLocater.infrastructure.persistence.like;


import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {

    Like findByHoopAndUser(Hoop hoop, User user);
    Like findByHoopAndAnonymousId(Hoop hoop, String anonymousId);

    List<Like> findAllByUser(User user);
}