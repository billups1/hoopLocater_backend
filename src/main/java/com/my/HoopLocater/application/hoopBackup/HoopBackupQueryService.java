package com.my.HoopLocater.application.hoopBackup;

import com.my.HoopLocater.domain.hoopBackup.dto.HoopBackupDto;
import com.my.HoopLocater.infrastructure.persistence.hoopBackup.HoopBackupJpaQueryImplRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HoopBackupQueryService {

    private final HoopBackupJpaQueryImplRepository hoopBackupJpaQueryImplRepository;

    public List<HoopBackupDto> getHoopHistoryList(Long hoopId) {
        return hoopBackupJpaQueryImplRepository.getHoopHistoryList(hoopId);
    }

    public List<HoopBackupDto> getHoopHistoryListByUserId(Long userId) {
        return hoopBackupJpaQueryImplRepository.getHoopHistoryListByUserId(userId);
    }

    public Long getHoopHistoryListCountByUserId(Long userId) {
        return hoopBackupJpaQueryImplRepository.getHoopHistoryListCountByUserId(userId);
    }
}
