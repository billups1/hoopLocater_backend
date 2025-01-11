package com.my.HoopLocater.infrastructure.persistence.hoopBackup;

import com.my.HoopLocater.domain.hoopBackup.HoopBackup;
import com.my.HoopLocater.domain.hoopBackup.QHoopBackup;
import com.my.HoopLocater.domain.hoopBackup.dto.HoopBackupDto;
import com.my.HoopLocater.domain.hoopBackup.dto.QHoopBackupDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoopBackupJpaQueryImplRepository extends QuerydslRepositorySupport implements HoopBackupJpaQueryRepository {

    public HoopBackupJpaQueryImplRepository() {
        super(HoopBackup.class);
    }

    @Override
    public List<HoopBackupDto> getHoopHistoryList(Long hoopId) {
        QHoopBackup hoopBackup = QHoopBackup.hoopBackup;

        return getQuerydsl().createQuery().select(
                        new QHoopBackupDto(hoopBackup)
                )
                .from(hoopBackup)
                .where(hoopBackup.hoopId.eq(hoopId))
                .orderBy(hoopBackup.id.desc())
                .fetch();
    }

    @Override
    public List<HoopBackupDto> getHoopHistoryListByUserId(Long userId) {
        QHoopBackup hoopBackup = QHoopBackup.hoopBackup;

        return getQuerydsl().createQuery().select(
                        new QHoopBackupDto(hoopBackup)
                )
                .from(hoopBackup)
                .where(hoopBackup.user.id.eq(userId))
                .orderBy(hoopBackup.id.desc())
                .fetch();
    }

    @Override
    public Long getHoopHistoryListCountByUserId(Long userId) {
        QHoopBackup hoopBackup = QHoopBackup.hoopBackup;

        return getQuerydsl().createQuery().select(hoopBackup.count())
                .from(hoopBackup)
                .where(hoopBackup.user.id.eq(userId))
                .fetchOne();
    }

}
