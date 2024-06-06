package com.my.HoopLocater.infrastructure.persistence.subscribe;

import com.my.HoopLocater.domain.auth.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscribeJpaQueryRepository {
    Page<User> getSubscribeList(Pageable pageable);

}
