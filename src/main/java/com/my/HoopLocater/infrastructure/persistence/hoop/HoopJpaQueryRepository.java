package com.my.HoopLocater.infrastructure.persistence.hoop;

import com.my.HoopLocater.domain.hoop.dto.HoopDto;

import java.util.List;

public interface HoopJpaQueryRepository {
    List<HoopDto> getHoopList();

}
