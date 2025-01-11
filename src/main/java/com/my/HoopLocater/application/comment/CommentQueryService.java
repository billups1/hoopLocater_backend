package com.my.HoopLocater.application.comment;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.comment.dto.CommentDto;
import com.my.HoopLocater.infrastructure.persistence.comment.CommentJpaQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentQueryService {
    private final CommentJpaQueryRepository commentJpaQueryRepository;

    @Transactional(readOnly = true)
    public Page<CommentDto> getHoopList(Long hoopId, UserDto userDto, Pageable pageable) {
        return commentJpaQueryRepository.getCommentListByHoopId(hoopId, userDto, pageable);
    }
}
