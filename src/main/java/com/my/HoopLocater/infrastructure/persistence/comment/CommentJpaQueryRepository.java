package com.my.HoopLocater.infrastructure.persistence.comment;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.comment.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentJpaQueryRepository {
    Page<CommentDto> getCommentListByHoopId(Long hoopId, UserDto userDto, Pageable pageable);

}
