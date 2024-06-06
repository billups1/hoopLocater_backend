package com.my.HoopLocater.application.report;

import com.my.HoopLocater.application.report.command.ReportCreateCommand;
import com.my.HoopLocater.infrastructure.persistence.report.ReportJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ReportCommandHandler {

    final ReportJpaRepository reportJpaRepository;

    @Transactional
    public int handler(ReportCreateCommand command) {
        reportJpaRepository.save(command.create());
        return 1;
    }


}
