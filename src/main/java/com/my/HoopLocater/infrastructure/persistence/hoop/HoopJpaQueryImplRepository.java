package com.my.HoopLocater.infrastructure.persistence.hoop;

import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.QHoop;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.domain.hoop.dto.QHoopDto;
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
}
