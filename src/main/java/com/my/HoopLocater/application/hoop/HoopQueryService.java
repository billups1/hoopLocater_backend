package com.my.HoopLocater.application.hoop;

import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaQueryRepository;
import com.my.HoopLocater.infrastructure.persistence.subscribe.SubscribeJpaQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HoopQueryService {
    private final HoopJpaQueryRepository hoopJpaQueryRepository;

    @Transactional(readOnly = true)
    public List<HoopDto> getHoopList() {
        return hoopJpaQueryRepository.getHoopList();
    }
}
