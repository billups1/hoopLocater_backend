package com.my.HoopLocater.infrastructure.web.hoop;


import com.my.HoopLocater.application.hoop.HoopQueryService;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.ImageFileResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
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
    @GetMapping("/api/v1/hoop/{hoopId}")
    public HoopDto getHoop(@PathVariable(name = "hoopId") Long hoopId) {
        return service.getHoop(hoopId);
    }

    @Operation(
            summary = "농구장 사진 조회 API",
            description = """
                    <p>
                        농구장 1개의 사진 정보를 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/hoop/picture/{hoopId}")
    public List<ImageFileResponseDto> getHoopPictures(@PathVariable(name = "hoopId") Long hoopId) {
        return service.getHoopPictures(hoopId);
    }

}
