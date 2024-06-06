package com.my.HoopLocater.infrastructure.web.subscribe;

import com.my.HoopLocater.application.subscribe.SubscribeCommandHandler;
import com.my.HoopLocater.application.subscribe.command.SubscribeCreateCommand;
import com.my.HoopLocater.application.subscribe.command.SubscribeDeleteCommand;
import com.my.HoopLocater.domain.auth.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/subscribe")
@RestController
public class SubscribeCommandController {
    private final SubscribeCommandHandler commandHandler;

    @Operation(
            summary = "유저 구독 API",
            description = """
                    <p>
                        구독할 유저의 id를 전달받아 해당 유저를 구독합니다.
                    </p>
                    """
    )
    @PostMapping("/{toUserId}")
    public void subscribe(Long userId, @PathVariable Long toUserId) {
        commandHandler.handler(SubscribeCreateCommand.of(new User(userId), new User(toUserId)));
    }

    @Operation(
            summary = "유저 구독취소 API",
            description = """
                    <p>
                        구독 취소할 유저의 id를 전달받아 해당 유저에 대한 구독을 취소합니다.
                    </p>
                    """
    )
    @DeleteMapping("/{toUserId}")
    public void unSubscribe(Long userId, @PathVariable Long toUserId) {
        commandHandler.handler(SubscribeDeleteCommand.of(new User(userId), new User(toUserId)));
    }
}
