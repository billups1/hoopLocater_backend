package com.my.HoopLocater.infrastructure.persistence.comment;

import com.my.HoopLocater.domain.comment.Comment;
import com.my.HoopLocater.domain.comment.QComment;
import com.my.HoopLocater.domain.comment.dto.CommentDto;
import com.my.HoopLocater.domain.comment.dto.QCommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CommentJpaQueryImplRepository extends QuerydslRepositorySupport implements CommentJpaQueryRepository {

    public CommentJpaQueryImplRepository() {
        super(Comment.class);
    }

    @Override
    public Page<CommentDto> getCommentListByHoopId(Long hoopId, Pageable pageable) {
        QComment comment = QComment.comment;

        var jpqlQuery = getQuerydsl().createQuery().select(
                    new QCommentDto(comment)
                ).from(comment)
                .where(comment.hoop.id.eq(hoopId))
                .orderBy(comment.id.desc());

        var queryResults = jpqlQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
