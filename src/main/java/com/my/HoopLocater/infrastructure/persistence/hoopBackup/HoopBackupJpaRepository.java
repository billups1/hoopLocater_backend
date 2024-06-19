package com.my.HoopLocater.infrastructure.persistence.hoopBackup;


import com.my.HoopLocater.domain.hoopBackup.HoopBackup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoopBackupJpaRepository extends JpaRepository<HoopBackup, Long> {
    HoopBackup save(HoopBackup entity);

}