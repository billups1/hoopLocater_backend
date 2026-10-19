package com.my.HoopLocater.infrastructure.web.hoop;


import com.my.HoopLocater.application.hoop.HoopQueryService;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.LeagueType;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.domain.hoopDetail.dto.HoopDetailDto;
import com.my.HoopLocater.domain.hoopDetail.dto.ProHoopDto;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.ImageFileResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HoopQueryController {
    private final HoopQueryService service;

    @Operation(
            summary = "농구장 전체 조회 API",
            description = """
                    <p>
                        전체 농구장을 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoop/all")
    public List<HoopDto> getHoopList() {
        return service.getHoopList();
    }

    @Operation(
            summary = "농구장 조회 API",
            description = """
                    <p>
                        농구장 1개 정보를 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoop/{hoopId:[0-9]+}")
    public HoopDto getHoop(@PathVariable(name = "hoopId") Long hoopId, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return service.getHoop(hoopId, anonymousId, userDto);
    }

    @Operation(
            summary = "농구장 사진 조회 API",
            description = """
                    <p>
                        농구장 1개의 사진 정보를 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoop/picture/{hoopId:[0-9]+}")
    public List<ImageFileResponseDto> getHoopPictures(@PathVariable(name = "hoopId") Long hoopId, @AuthUserDto UserDto userDto) {
        return service.getHoopPictures(hoopId, userDto);
    }

    @Operation(
            summary = "프로구단 홈구장 상세정보 조회 API",
            description = """
                    <p>
                        KBL/WKBL 프로구단 홈구장의 상세정보(구단명, 체육관명, 주소 등)를 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoop/detail/{hoopId:[0-9]+}")
    public HoopDetailDto getHoopDetail(@PathVariable(name = "hoopId") Long hoopId) {
        return service.getHoopDetail(hoopId);
    }

    @Operation(
            summary = "프로구단 홈구장 목록 조회 API",
            description = """
                    <p>
                        KBL/WKBL 프로구단 홈구장 목록을 조회합니다.<br>
                        type 파라미터로 리그를 필터링할 수 있습니다. (KBL / WKBL)<br>
                        type을 생략하면 KBL + WKBL 전체를 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoop/pro")
    public List<ProHoopDto> getProHoopList(@RequestParam(name = "type", required = false) LeagueType type) {
        return service.getProHoopList(type);
    }

}
