package com.my.HoopLocater.infrastructure.persistence.hoop;


import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoopJpaRepository extends JpaRepository<Hoop, Long> {
    List<Hoop> findAllByUser(User user);

    Hoop save(Hoop entity);

}