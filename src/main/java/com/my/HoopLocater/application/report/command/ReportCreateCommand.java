package com.my.HoopLocater.application.report.command;

import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.report.Report;
import lombok.Getter;

@Getter
public class ReportCreateCommand {

    private Long hoopId;
    private String reason;

    public ReportCreateCommand(Long hoopId,
                               String reason) {
        this.hoopId = hoopId;
        this.reason = reason;
    }

    public static ReportCreateCommand of(Long hoopId,
                                         String reason) {
        return new ReportCreateCommand(hoopId, reason);
    }

    public Report create() {
        return Report.builder()
                .hoop(new Hoop(hoopId))
                .reason(reason)
                .build();
    }

}
