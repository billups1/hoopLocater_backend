package com.my.HoopLocater.infrastructure.persistence.hoopDetail;

import com.my.HoopLocater.domain.hoopDetail.HoopDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoopDetailJpaRepository extends JpaRepository<HoopDetail, Long> {

    Optional<HoopDetail> findByHoopId(Long hoopId);

}
