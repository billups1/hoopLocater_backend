package com.my.HoopLocater.application.report.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.report.Report;
import lombok.Getter;

@Getter
public class ReportCreateCommand {

    private Long hoopId;
    private String reason;
    private String anonymousId;
    private UserDto userDto;

    public ReportCreateCommand(Long hoopId,
                               String reason,
                               String anonymousId,
                               UserDto userDto) {
        this.hoopId = hoopId;
        this.reason = reason;
        this.anonymousId = anonymousId;
        this.userDto = userDto;
    }

    public static ReportCreateCommand of(Long hoopId,
                                         String reason,
                                         String anonymousId,
                                         UserDto userDto) {
        return new ReportCreateCommand(hoopId, reason, anonymousId, userDto);
    }

    public Report create() {
        return Report.builder()
                .hoop(new Hoop(hoopId))
                .reason(reason)
                .writer(userDto == null ? anonymousId : userDto.nickName()) // UserDto가 없으면 익명 아이디로 저장
                .build();
    }

}
