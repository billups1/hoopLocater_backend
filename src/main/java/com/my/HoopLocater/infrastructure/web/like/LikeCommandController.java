package com.my.HoopLocater.infrastructure.web.like;

import com.my.HoopLocater.application.like.LikeCommandHandler;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.web.like.dto.LikeCreateRequest;
import com.my.HoopLocater.infrastructure.web.like.dto.LikeDeleteRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class LikeCommandController {
    private final LikeCommandHandler commandHandler;

    @Operation(
            summary = "좋아요 생성 API",
            description = """
                    <p>
                        좋아요를 생성합니다.
                    </p>
                    """
    )
    @PostMapping("/api/v1/like")
    public void likeCreate(@RequestBody @Valid LikeCreateRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        commandHandler.handler(request.toCommand(anonymousId, userDto));
    }

    @Operation(
            summary = "좋아요 취소 API",
            description = """
                    <p>
                        좋아요를 취소합니다.
                    </p>
                    """
    )
    @PostMapping("/api/v1/like/delete")
    public void likeDelete(@RequestBody @Valid LikeDeleteRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        commandHandler.handler(request.toCommand(anonymousId, userDto));
    }

}
