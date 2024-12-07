package com.my.HoopLocater.infrastructure.persistence.storageFile;


import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.storageFile.StorageImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageFileJpaRepository extends JpaRepository<StorageImageFile, Long> {

    List<StorageImageFile> findAllByHoopOrderByIdDesc(Hoop hoop);

    List<StorageImageFile> findAllByUser(User build);
}