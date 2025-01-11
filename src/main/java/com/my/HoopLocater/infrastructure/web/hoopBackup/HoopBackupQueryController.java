package com.my.HoopLocater.infrastructure.web.hoopBackup;


import com.my.HoopLocater.application.hoopBackup.HoopBackupQueryService;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.domain.hoopBackup.dto.HoopBackupDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class HoopBackupQueryController {
    private final HoopBackupQueryService service;

    @Operation(
            summary = "농구장 이력 조회 API",
            description = """
                    <p>
                        농구장 이력을 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoopBackup/{hoopId}")
    public List<HoopBackupDto> getHoopHistoryList(@PathVariable(name = "hoopId") Long hoopId, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return service.getHoopHistoryList(hoopId);
    }

    @Operation(
            summary = "농구장 이력 조회 by UserId API",
            description = """
                    <p>
                        농구장 이력을 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoopBackup/user/{userId}")
    public List<HoopBackupDto> getHoopHistoryListByUserId(@PathVariable(name = "userId") Long userId, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return service.getHoopHistoryListByUserId(userId);
    }

    @Operation(
            summary = "농구장 이력 갯수 조회 by UserId API",
            description = """
                    <p>
                        농구장 이력 갯수를 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoopBackup/count/user/{userId}")
    public Long getHoopHistoryListCountByUserId(@PathVariable(name = "userId") Long userId, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return service.getHoopHistoryListCountByUserId(userId);
    }

}
