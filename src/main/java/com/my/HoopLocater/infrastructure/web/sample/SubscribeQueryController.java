package com.my.HoopLocater.infrastructure.web.sample;


import com.my.HoopLocater.application.sample.SubscribeQueryService;
import com.my.HoopLocater.domain.auth.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class SubscribeQueryController {
    private final SubscribeQueryService service;

    @Operation(
            summary = "구독 리스트 조회 API",
            description = """
                    <p>
                        내가 구독한 사용자 목록을 조회합니다. 페이지네이션 처리하여, 한 페이지당 데이터 9개씩 반환합니다.
                    </p>
                    """
    )
    @GetMapping("/api/v1/subscribe/list")
    public Page<User> getSubscribeList(@PageableDefault(size = 9) Pageable pageable) {
        return service.getSubscribeList(pageable);
    }

}
