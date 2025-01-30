package com.my.HoopLocater.infrastructure.persistence.comment;


import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.comment.Comment;
import com.my.HoopLocater.domain.comment.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    Page<CommentDto> getCommentListByHoopId(Long hoopId, Pageable pageable);

    List<Comment> findAllByUser(User user);
}