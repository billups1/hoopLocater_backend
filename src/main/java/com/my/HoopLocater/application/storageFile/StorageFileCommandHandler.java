package com.my.HoopLocater.application.storageFile;

import com.my.HoopLocater.application.storageFile.command.StorageFileCreateCommand;
import com.my.HoopLocater.application.storageFile.command.StorageFileDeleteCommand;
import com.my.HoopLocater.common.exception.CustomBusinessException;
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

    public void handler(StorageFileDeleteCommand command) {
        StorageImageFile storageImageFile = storageFileJpaRepository.findById(command.getImageId()).orElseThrow(() -> {
            throw new CustomBusinessException("id로 엔티티를 찾을 수 없습니다.");
        });
        if (storageImageFile.getUser().getId() != command.getUserDto().id()) {
            throw new CustomBusinessException("사진 업로더가 아니므로 댓글을 삭제할 수 없습니다.");
        }
        storageFileJpaRepository.deleteById(command.getImageId());
    }
}
