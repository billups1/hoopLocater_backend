package com.my.HoopLocater.application.storageFile;

import com.my.HoopLocater.application.storageFile.command.StorageFileCreateCommand;
import com.my.HoopLocater.domain.storageFile.StorageImageFile;
import com.my.HoopLocater.infrastructure.persistence.storageFile.StorageFileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class StorageFileCommandHandler {

    final StorageFileJpaRepository storageFileJpaRepository;

    @Transactional
    public StorageImageFile handler(StorageFileCreateCommand command) {
        return storageFileJpaRepository.save(command.create());
    }

}
