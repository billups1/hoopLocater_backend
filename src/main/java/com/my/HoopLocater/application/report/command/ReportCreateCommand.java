package com.my.HoopLocater.application.report.command;

import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.report.Report;
import lombok.Getter;

@Getter
public class ReportCreateCommand {

    private Long hoopId;
    private String reason;
    private String loginId;

    public ReportCreateCommand(Long hoopId,
                               String reason,
                               String loginId) {
        this.hoopId = hoopId;
        this.reason = reason;
        this.loginId = loginId;
    }

    public static ReportCreateCommand of(Long hoopId,
                                         String reason,
                                         String loginId) {
        return new ReportCreateCommand(hoopId, reason, loginId);
    }

    public Report create() {
        return Report.builder()
                .hoop(new Hoop(hoopId))
                .reason(reason)
                .loginId(loginId)
                .build();
    }

}
