package com.my.HoopLocater.infrastructure.persistence.hoop;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.QHoop;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.domain.hoop.dto.QHoopDto;
import com.my.HoopLocater.domain.like.Like;
import com.my.HoopLocater.domain.like.QLike;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoopJpaQueryImplRepository extends QuerydslRepositorySupport implements HoopJpaQueryRepository {

    public HoopJpaQueryImplRepository() {
        super(Hoop.class);
    }

    @Override
    public List<HoopDto> getHoopList() {
        QHoop hoop = QHoop.hoop;

        return getQuerydsl().createQuery().select(
                        new QHoopDto(hoop)
                )
                .from(hoop)
                .fetch();
    }

    @Override
    public HoopDto getHoop(Long hoopId, String anonymousId, UserDto userDto) {
        QHoop hoop = QHoop.hoop;
        QLike like = QLike.like;

        Hoop hoopEntity = getQuerydsl().createQuery().select(hoop)
                .from(hoop)
                .where(hoop.id.eq(hoopId))
                .fetchOne();

        List<Like> likeList = getQuerydsl().createQuery().select(like)
                .from(like)
                .where(like.hoop.id.eq(hoopId))
                .fetch();

        if (likeList.size() == 0) {
            return HoopDto.from(hoopEntity, false);
        } else {
            if (userDto == null) { // 비회원일때
                for (Like like1 : likeList) {
                    if (anonymousId.equals(like1.getAnonymousId())) {
                        return HoopDto.from(hoopEntity, true);
                    }
                }
            } else { // 회원일때
                for (Like like1 : likeList) {
                    if (like1.getUser() != null && userDto.id().equals(like1.getUser().getId())) {
                        return HoopDto.from(hoopEntity, true);
                    }
                }
            }
        }

        return HoopDto.from(hoopEntity, false);
    }
}
