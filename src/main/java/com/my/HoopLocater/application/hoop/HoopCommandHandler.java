package com.my.HoopLocater.application.hoop;

import com.my.HoopLocater.application.hoop.command.HoopCreateCommand;
import com.my.HoopLocater.application.hoop.command.HoopUpdateCommand;
import com.my.HoopLocater.common.exception.CustomHoopException;
import com.my.HoopLocater.domain.hoop.*;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class HoopCommandHandler {

    final HoopJpaRepository hoopJpaRepository;

    @Transactional
    public HoopDto handler(HoopCreateCommand command) {
        return HoopDto.from(hoopJpaRepository.save(command.create()));
    }

    @Transactional
    public HoopDto handler(HoopUpdateCommand command) {
        Hoop hoop = hoopJpaRepository.findById(command.getId()).orElseThrow(() -> {
            throw new CustomHoopException("id로 농구장 정보를 찾을 수 없습니다.");
        });

        hoop.updateContent(command.getName(), command.getHoopCount(), FloorType.valueOf(command.getFloorType()), Light.valueOf(command.getLight()),
                FreeState.valueOf(command.getFreeState()), StandardState.valueOf(command.getStandardState()));

        return HoopDto.from(hoop);
    }

}
