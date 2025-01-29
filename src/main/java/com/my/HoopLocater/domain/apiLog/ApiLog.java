package com.my.HoopLocater.domain.apiLog;

import com.my.HoopLocater.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "apiLogs")
@Builder
@AllArgsConstructor
public class ApiLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "request_uri")
    private String requestUri;

    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "response_status")
    private String responseStatus;

    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "anonymous_id")
    private String anonymousId;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    @Column(name = "err_class")
    private String errClass;

    @Column(name = "err_method")
    private String errMethod;

    @Column(name = "err_message")
    private String errMessage;

}