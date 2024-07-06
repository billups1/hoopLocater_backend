package com.my.HoopLocater.infrastructure.web.comment;


import com.my.HoopLocater.application.comment.CommentQueryService;
import com.my.HoopLocater.domain.comment.dto.CommentDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentQueryController {
    private final CommentQueryService service;

    @Operation(
            summary = "댓글 조회 API",
            description = """
                    <p>
                        특정 Hoop의 댓글을 조회합니다.
                    </p>
                    """
    )
    @GetMapping("/{hoopId}")
    public Page<CommentDto> getCommentList(@PathVariable(name = "hoopId") Long hoopId, @PageableDefault(size = 100) Pageable pageable) {
        return service.getHoopList(hoopId, pageable);
    }

}
