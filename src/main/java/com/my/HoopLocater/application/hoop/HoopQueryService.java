package com.my.HoopLocater.application.hoop;

import com.my.HoopLocater.common.exception.CustomBusinessException;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaQueryImplRepository;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaQueryRepository;
import com.my.HoopLocater.infrastructure.persistence.storageFile.StorageFileJpaRepository;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.ImageFileResponseDto;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails;
import com.oracle.bmc.objectstorage.requests.CreatePreauthenticatedRequestRequest;
import com.oracle.bmc.objectstorage.responses.CreatePreauthenticatedRequestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HoopQueryService {
    private final HoopJpaQueryRepository hoopJpaQueryRepository;
    private final HoopJpaQueryImplRepository hoopJpaQueryImplRepository;
    private final StorageFileJpaRepository storageFileJpaRepository;
    private final ObjectStorageClient objectStorageClient;

    @Value("${oci.objectstorage.namespace}")
    private String namespaceName;

    @Value("${oci.objectstorage.bucket-name}")
    private String bucketName;

    @Value("${oci.objectstorage.region-id}")
    private String regionId;

    @Transactional(readOnly = true)
    public List<HoopDto> getHoopList() {
        return hoopJpaQueryRepository.getHoopList();
    }

    public HoopDto getHoop(Long hoopId, String anonymousId, UserDto userDto) {
        return hoopJpaQueryImplRepository.getHoop(hoopId, anonymousId, userDto);
    }

    public List<ImageFileResponseDto> getHoopPictures(Long hoopId, UserDto userDto) {
        if (userDto == null) {
            return storageFileJpaRepository.findAllByHoopOrderByIdDesc(Hoop.builder().id(hoopId).build()).stream().map(
                    storageImageFile -> ImageFileResponseDto.from(storageImageFile, false, createParUrl(storageImageFile.getSavedFullPathName()))
            ).toList();
        } else {
            return storageFileJpaRepository.findAllByHoopOrderByIdDesc(Hoop.builder().id(hoopId).build()).stream().map(
                    storageImageFile -> ImageFileResponseDto.from(
                            storageImageFile,
                            storageImageFile.getUser() != null? storageImageFile.getUser().getId() == userDto.id() : false,
                            createParUrl(storageImageFile.getSavedFullPathName())
                            )
            ).toList();
        }
    }

    // OCI objectStorage 특정 객체에 접근할 수 있는 만료 시간이 있는 PAR URL 생성
    public String createParUrl(String saved_full_path_name) {
        // 1. 만료 시간 설정 (현재 시간으로부터 expirationHours 후)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1); // 1시간
        Date expirationDate = cal.getTime();

        // 2. PAR 상세 정보 정의
        CreatePreauthenticatedRequestDetails parDetails = CreatePreauthenticatedRequestDetails.builder()
                .name("par-for-" + saved_full_path_name + "-" + System.currentTimeMillis())
                .accessType(CreatePreauthenticatedRequestDetails.AccessType.ObjectRead) // ⚠️ 읽기 권한만 부여
                .timeExpires(expirationDate)
                .objectName(saved_full_path_name) // 특정 객체만 접근 가능하도록 지정
                .build();

        // 3. OCI 클라이언트 요청 객체 생성
        CreatePreauthenticatedRequestRequest request = CreatePreauthenticatedRequestRequest.builder()
                .namespaceName(namespaceName)
                .bucketName(bucketName)
                .createPreauthenticatedRequestDetails(parDetails)
                .build();

        try {
            // 4. OCI API 호출 및 응답 받기
            CreatePreauthenticatedRequestResponse response = objectStorageClient.createPreauthenticatedRequest(request);

            // 5. PAR URL 생성 및 반환
            // accessUri는 /p/{token}/n/{namespace}/b/{bucketName}/o/ 와 같은 형식입니다.
            String accessUri = response.getPreauthenticatedRequest().getAccessUri();

            // 최종 PAR URL 형식: https://objectstorage.{region}.oraclecloud.com{accessUri}{saved_full_path_name}
            String parUrlFormat = "https://objectstorage.%s.oraclecloud.com%s";

            // 객체 이름은 URL 인코딩하여 결합
            String finalUrl = String.format(
                    parUrlFormat,
                    regionId,
                    accessUri
            );

            return finalUrl;

        } catch (Exception e) {
            throw new CustomBusinessException("이미지 파일 PAR URL 생성 오류", e);
        }
    }
}
