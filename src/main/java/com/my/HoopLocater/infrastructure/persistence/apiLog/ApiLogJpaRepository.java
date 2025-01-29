package com.my.HoopLocater.infrastructure.persistence.apiLog;


import com.my.HoopLocater.domain.apiLog.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogJpaRepository extends JpaRepository<ApiLog, Long> {

}