package com.my.HoopLocater.application.hoop;

import com.my.HoopLocater.application.hoop.command.HoopCreateCommand;
import com.my.HoopLocater.application.hoop.command.HoopUpdateCommand;
import com.my.HoopLocater.common.exception.CustomBusinessException;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.*;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.domain.hoopBackup.HoopBackup;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaQueryImplRepository;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.hoopBackup.HoopBackupJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class HoopCommandHandler {

    final HoopJpaRepository hoopJpaRepository;
    final HoopJpaQueryImplRepository hoopJpaQueryImplRepository;
    final HoopBackupJpaRepository hoopBackupJpaRepository;

    @Transactional
    public HoopDto handler(HoopCreateCommand command) {
        return HoopDto.from(hoopJpaRepository.save(command.create()), null);
    }

    @Transactional
    public HoopDto handler(HoopUpdateCommand command) {
        var hoop = hoopJpaRepository.findById(command.getId()).orElseThrow(() -> {
            throw new CustomBusinessException("id로 농구장 정보를 찾을 수 없습니다.");
        });

        hoop.updateContent(
                command.getName(),
                command.getHoopCount(),
                FloorType.valueOf(command.getFloorType()),
                Light.valueOf(command.getLight()),
                FreeState.valueOf(command.getFreeState()),
                StandardState.valueOf(command.getStandardState()),
                command.getUserDto() != null ? User.builder().id(command.getUserDto().id()).build() : null,
                command.getUserDto() == null ? command.getAnonymousId() : null
        );

        // 농구장 히스토리 데이터 저장
        hoopBackupJpaRepository.save(HoopBackup.builder()
                        .hoopId(hoop.getId())
                        .name(hoop.getName())
                        .latitude(hoop.getLatitude())
                        .longitude(hoop.getLongitude())
                        .hoopCount(hoop.getHoopCount())
                        .floorType(hoop.getFloorType())
                        .light(hoop.getLight())
                        .freeState(hoop.getFreeState())
                        .standardState(hoop.getStandardState())
                        .user(command.getUserDto() != null ? User.builder().id(command.getUserDto().id()).build() : null)
                        .anonymousId(command.getUserDto() == null ? command.getAnonymousId() : null)
                .build());

        return hoopJpaQueryImplRepository.getHoop(command.getId(), command.getAnonymousId(), command.getUserDto());
    }

}
