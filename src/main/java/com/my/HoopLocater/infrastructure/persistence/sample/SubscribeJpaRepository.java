package com.my.HoopLocater.infrastructure.persistence.sample;


import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.sample.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeJpaRepository extends JpaRepository<Subscribe, Long> {
    Subscribe save(Subscribe entity);

    Integer deleteByFromUserAndToUser(User fromUser, User toUser);
}