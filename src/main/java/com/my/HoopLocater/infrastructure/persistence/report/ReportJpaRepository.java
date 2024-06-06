package com.my.HoopLocater.infrastructure.persistence.report;


import com.my.HoopLocater.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportJpaRepository extends JpaRepository<Report, Long> {
    Report save(Report entity);

}