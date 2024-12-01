package com.my.HoopLocater.application.hoop;

import com.my.HoopLocater.common.exception.CustomBusinessException;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.domain.storageFile.StorageImageFile;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaQueryRepository;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.storageFile.StorageFileJpaRepository;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.ImageFileResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HoopQueryService {
    private final HoopJpaQueryRepository hoopJpaQueryRepository;
    private final HoopJpaRepository hoopJpaRepository;
    private final StorageFileJpaRepository storageFileJpaRepository;

    @Transactional(readOnly = true)
    public List<HoopDto> getHoopList() {
        return hoopJpaQueryRepository.getHoopList();
    }

    public HoopDto getHoop(Long hoopId) {
        return HoopDto.from(
                hoopJpaRepository.findById(hoopId).orElseThrow(() -> {
                    throw new CustomBusinessException("id로 엔티티를 찾을 수 없습니다.");
                })
        );
    }

    public List<ImageFileResponseDto> getHoopPictures(Long hoopId) {
        return storageFileJpaRepository.findAllByHoopOrderByIdDesc(Hoop.builder().id(hoopId).build()).stream().map(
                storageImageFile -> ImageFileResponseDto.from(storageImageFile)
        ).toList();
    }
}
