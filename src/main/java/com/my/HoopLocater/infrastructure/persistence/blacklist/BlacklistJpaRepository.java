package com.my.HoopLocater.infrastructure.persistence.blacklist;


import com.my.HoopLocater.domain.app.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistJpaRepository extends JpaRepository<Blacklist, Long> {

    Boolean existsByIpOrAnonymousId(String ip, String anonymousId);

}