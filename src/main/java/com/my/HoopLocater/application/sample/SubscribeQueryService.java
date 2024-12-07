package com.my.HoopLocater.application.sample;

import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.infrastructure.persistence.sample.SubscribeJpaQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscribeQueryService {
    private final SubscribeJpaQueryRepository subscribeJpaQueryRepository;

    @Transactional(readOnly = true)
    public Page<User> getSubscribeList(Pageable pageable) {
        return subscribeJpaQueryRepository.getSubscribeList(pageable);
    }
}
