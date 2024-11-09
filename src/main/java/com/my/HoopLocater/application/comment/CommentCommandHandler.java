package com.my.HoopLocater.application.comment;

import com.my.HoopLocater.application.comment.command.CommentCreateCommand;
import com.my.HoopLocater.application.comment.command.CommentDeleteCommand;
import com.my.HoopLocater.common.exception.CustomBusinessException;
import com.my.HoopLocater.domain.comment.dto.CommentDto;
import com.my.HoopLocater.infrastructure.persistence.comment.CommentJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentCommandHandler {

    private final CommentJpaRepository commentJpaRepository;
    private final HoopJpaRepository hoopJpaRepository;

    @Transactional
    public CommentDto handler(CommentCreateCommand command) {
        var commentDto = CommentDto.from(commentJpaRepository.save(command.create()));
        hoopJpaRepository.findById(command.getHoopId()).orElseThrow(() -> {
            throw new CustomBusinessException("id로 엔티티를 찾을 수 없습니다.");
        }).addCommentCount();
        return commentDto;
    }

    @Transactional
    public void handler(CommentDeleteCommand command) {
        var comment = commentJpaRepository.findById(command.getId()).orElseThrow(() -> {
            throw new CustomBusinessException("id로 엔티티를 찾을 수 없습니다.");
        });
        var hoop = comment.getHoop();

        if (command.getUserDto() != null) {
            if (!comment.getWriter().equals(command.getUserDto().nickName())) {
                throw new CustomBusinessException("댓글 작성자가 아니므로 댓글을 삭제할 수 없습니다.");
            }
        } else {
            throw new CustomBusinessException("댓글 작성자가 아니므로 댓글을 삭제할 수 없습니다.");
        }

        commentJpaRepository.delete(comment);
        hoop.minusCommentCount();
    }

}
