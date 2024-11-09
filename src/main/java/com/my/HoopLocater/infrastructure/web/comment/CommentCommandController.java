package com.my.HoopLocater.infrastructure.web.comment;

import com.my.HoopLocater.application.comment.CommentCommandHandler;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.comment.dto.CommentDto;
import com.my.HoopLocater.infrastructure.web.comment.dto.CommentCreateRequest;
import com.my.HoopLocater.infrastructure.web.comment.dto.CommentDeleteRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
@RestController
public class CommentCommandController {
    private final CommentCommandHandler commandHandler;

    @Operation(
            summary = "댓글 생성 API",
            description = """
                    <p>
                        새로운 댓글을 생성합니다.
                    </p>
                    """
    )
    @PostMapping
    public CommentDto create(@RequestBody @Valid CommentCreateRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return commandHandler.handler(request.toCommand(anonymousId, userDto));
    }

    @Operation(
            summary = "댓글 삭제 API",
            description = """
                    <p>
                        댓글을 삭제합니다.
                    </p>
                    """
    )
    @DeleteMapping
    public void delete(@RequestBody @Valid CommentDeleteRequest request, @AuthUserDto UserDto userDto) {
        commandHandler.handler(request.toCommand(userDto));
    }

}
