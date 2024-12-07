package com.my.HoopLocater.application.sample;

import com.my.HoopLocater.application.sample.command.SubscribeCreateCommand;
import com.my.HoopLocater.application.sample.command.SubscribeDeleteCommand;
import com.my.HoopLocater.domain.sample.Subscribe;
import com.my.HoopLocater.infrastructure.persistence.sample.SubscribeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class SubscribeCommandHandler {

    final SubscribeJpaRepository subscribeJpaRepository;

    @Transactional
    public Subscribe handler(SubscribeCreateCommand command) {
        if (command.getFromUser().getId() == command.getToUser().getId()) {
            throw new RuntimeException("자신을 구독할 수 없습니다.");
        }
        return subscribeJpaRepository.save(command.create());
    }

    @Transactional
    public void handler(SubscribeDeleteCommand command) {
        Integer result = subscribeJpaRepository.deleteByFromUserAndToUser(command.getFromUser(), command.getToUser());
        if (result == 0) {
            throw new RuntimeException("구독 취소에 실패했습니다.");
        }
    }
}
