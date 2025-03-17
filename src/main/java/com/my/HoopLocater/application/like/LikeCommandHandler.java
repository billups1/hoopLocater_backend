package com.my.HoopLocater.application.like;

import com.my.HoopLocater.application.like.command.LikeCreateCommand;
import com.my.HoopLocater.application.like.command.LikeDeleteCommand;
import com.my.HoopLocater.common.exception.CustomBusinessException;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.like.Like;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.like.LikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LikeCommandHandler {

    final LikeJpaRepository likeJpaRepository;
    final HoopJpaRepository hoopJpaRepository;

    @Transactional
    public void handler(LikeCreateCommand command) {
        likeJpaRepository.save(command.create());

        // 농구장 like 수 +1
        Hoop hoop = hoopJpaRepository.findById(command.getHoopId()).orElseThrow(() -> {
            throw new CustomBusinessException("id로 농구장 정보를 찾을 수 없습니다.");
        });
        hoop.addLikeCount();
    }

    @Transactional
    public void handler(LikeDeleteCommand command) {
        Like like;
        if (command.getUserDto() != null) {
            like = likeJpaRepository.findByHoopAndUser(Hoop.builder().id(command.getHoopId()).build(), User.builder().id(command.getUserDto().id()).build());
        } else {
            like = likeJpaRepository.findByHoopAndAnonymousId(Hoop.builder().id(command.getHoopId()).build(), command.getAnonymousId());
        }

        if (like != null) {
            likeJpaRepository.delete(like);
            // 농구장 like 수 -1
            Hoop hoop = hoopJpaRepository.findById(command.getHoopId()).orElseThrow(() -> {
                throw new CustomBusinessException("id로 농구장 정보를 찾을 수 없습니다.");
            });
            hoop.minusLikeCount();
        } else {
            throw new CustomBusinessException("좋아요 삭제에 실패했습니다.");
        }
    }
}
