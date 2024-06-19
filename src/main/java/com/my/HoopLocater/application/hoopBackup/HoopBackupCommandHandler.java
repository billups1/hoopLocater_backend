package com.my.HoopLocater.application.hoopBackup;

import com.my.HoopLocater.application.hoopBackup.command.HoopBackupCreateCommand;
import com.my.HoopLocater.infrastructure.persistence.hoopBackup.HoopBackupJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class HoopBackupCommandHandler {

    final HoopBackupJpaRepository hoopBackupJpaRepository;

    @Transactional
    public void handler(HoopBackupCreateCommand command) {
        hoopBackupJpaRepository.save(command.create());
    }

}
