package com.my.HoopLocater.application.hoop;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaQueryImplRepository;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaQueryRepository;
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
    private final HoopJpaQueryImplRepository hoopJpaQueryImplRepository;
    private final StorageFileJpaRepository storageFileJpaRepository;

    @Transactional(readOnly = true)
    public List<HoopDto> getHoopList() {
        return hoopJpaQueryRepository.getHoopList();
    }

    public HoopDto getHoop(Long hoopId, String anonymousId, UserDto userDto) {
        return hoopJpaQueryImplRepository.getHoop(hoopId, anonymousId, userDto);
    }

    public List<ImageFileResponseDto> getHoopPictures(Long hoopId) {
        return storageFileJpaRepository.findAllByHoopOrderByIdDesc(Hoop.builder().id(hoopId).build()).stream().map(
                storageImageFile -> ImageFileResponseDto.from(storageImageFile)
        ).toList();
    }
}
