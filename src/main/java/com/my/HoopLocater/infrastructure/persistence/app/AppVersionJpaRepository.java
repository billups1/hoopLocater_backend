package com.my.HoopLocater.infrastructure.persistence.app;


import com.my.HoopLocater.domain.app.AppUpdateRequiredState;
import com.my.HoopLocater.domain.app.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionJpaRepository extends JpaRepository<AppVersion, Long> {

    AppVersion findTop1ByAppUpdateRequiredStateOrderByVersionCodeDesc(AppUpdateRequiredState appUpdateRequiredState);

}