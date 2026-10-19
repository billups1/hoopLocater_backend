package com.my.HoopLocater.infrastructure.persistence.hoop;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.LeagueType;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.domain.hoopDetail.dto.ProHoopDto;

import java.util.List;

public interface HoopJpaQueryRepository {
    List<HoopDto> getHoopList();

    HoopDto getHoop(Long hoopId, String anonymousId, UserDto userDto);

    List<ProHoopDto> getProHoopList(LeagueType type);

}
