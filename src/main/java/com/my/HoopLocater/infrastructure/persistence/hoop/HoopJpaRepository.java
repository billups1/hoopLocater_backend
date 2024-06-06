package com.my.HoopLocater.infrastructure.persistence.hoop;


import com.my.HoopLocater.domain.hoop.Hoop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoopJpaRepository extends JpaRepository<Hoop, Long> {
    Hoop save(Hoop entity);

}