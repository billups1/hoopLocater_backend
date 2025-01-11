package com.my.HoopLocater.infrastructure.persistence.hoopBackup;

import com.my.HoopLocater.domain.hoopBackup.dto.HoopBackupDto;

import java.util.List;

public interface HoopBackupJpaQueryRepository {
    List<HoopBackupDto> getHoopHistoryList(Long hoopId);

    List<HoopBackupDto> getHoopHistoryListByUserId(Long userId);

    Long getHoopHistoryListCountByUserId(Long userId);
}
