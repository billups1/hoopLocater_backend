package com.my.HoopLocater.infrastructure.persistence.subscribe;


import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeJpaRepository extends JpaRepository<Subscribe, Long> {
    Subscribe save(Subscribe entity);

    Integer deleteByFromUserAndToUser(User fromUser, User toUser);
}