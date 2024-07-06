package com.my.HoopLocater.infrastructure.persistence.comment;


import com.my.HoopLocater.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {



}