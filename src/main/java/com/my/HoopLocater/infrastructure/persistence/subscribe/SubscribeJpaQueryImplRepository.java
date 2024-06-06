package com.my.HoopLocater.infrastructure.persistence.subscribe;

import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.subscribe.Subscribe;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class SubscribeJpaQueryImplRepository extends QuerydslRepositorySupport implements SubscribeJpaQueryRepository {

    public SubscribeJpaQueryImplRepository() {
        super(Subscribe.class);
    }

    @Override
    public Page<User> getSubscribeList(Pageable pageable) {
        return null;
//        QSubscribe subscribe = QSubscribe.subscribe;
//
//        var jpqlQuery = getQuerydsl().createQuery().select(
//                        Projections.constructor(
//                                UserInfoView.class,
//                                subscribe.toUser.id,
//                                subscribe.toUser.loginId,
//                                subscribe.toUser.email,
//                                subscribe.toUser.name,
//                                subscribe.toUser.nickName,
//                                subscribe.toUser.profileImageUrl,
//                                subscribe.toUser.bio,
//                                subscribe.toUser.subject,
//                                subscribe.toUser.servicePolicyAgreeState,
//                                subscribe.toUser.privacyPolicyAgreeState,
//                                subscribe.toUser.role,
//                                subscribe.toUser.provider,
//                                subscribe.toUser.withdrawalAt
//                        ))
//                .from(subscribe)
//                .where(subscribe.fromUser.id.eq(userDto.id()));
//
//        var queryResults = jpqlQuery
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();
//
//        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
