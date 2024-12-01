package com.my.HoopLocater.infrastructure.web.hoop;

import com.my.HoopLocater.application.hoop.HoopCommandHandler;
import com.my.HoopLocater.application.s3.AwsS3CommandHandler;
import com.my.HoopLocater.application.s3.command.AwsS3ImageUploadCommand;
import com.my.HoopLocater.application.storageFile.StorageFileCommandHandler;
import com.my.HoopLocater.application.storageFile.command.StorageFileCreateCommand;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.web.hoop.dto.HoopCreateRequest;
import com.my.HoopLocater.infrastructure.web.hoop.dto.HoopUpdateRequest;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.ImageFileResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class HoopCommandController {
    private final HoopCommandHandler hoopCommandHandler;
    private final AwsS3CommandHandler awsS3CommandHandler;
    private final StorageFileCommandHandler storageFileCommandHandler;

    @Operation(
            summary = "농구장 생성 API",
            description = """
                    <p>
                        새로운 농구장 정보를 생성합니다.
                    </p>
                    """
    )
    @PostMapping("/api/v1/hoop/create")
    public HoopDto create(@RequestBody @Valid HoopCreateRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return hoopCommandHandler.handler(request.toCommand(anonymousId, userDto));
    }

    @Operation(
            summary = "농구장 정보 업데이트 API",
            description = """
                    <p>
                        농구장 정보를 업데이트합니다.
                    </p>
                    """
    )
    @PutMapping("/api/v1/hoop/update")
    public HoopDto update(@RequestBody @Valid HoopUpdateRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return hoopCommandHandler.handler(request.toCommand(anonymousId, userDto));
    }

    @Operation(
            summary = "농구장 사진 등록 API",
            description = """
                    <p>
                        농구장 사진을 등록합니다.
                    </p>
                    """
    )
    @PostMapping("/api/v1/hoop/picture/{hoopId}")
    public ImageFileResponseDto pictureUpload(@RequestPart(value = "pictureFile", required = false) MultipartFile pictureFile,
                                              @PathVariable(name = "hoopId") Long hoopId,
                                              @RequestHeader("anonymousId") String anonymousId,
                                              @AuthUserDto UserDto userDto) {
        var s3FileDto = awsS3CommandHandler.handler(AwsS3ImageUploadCommand.of(userDto, anonymousId, pictureFile));
        var storageImageFile = storageFileCommandHandler.handler(StorageFileCreateCommand.of(userDto, anonymousId, s3FileDto, Hoop.builder().id(hoopId).build()));

        return ImageFileResponseDto.from(storageImageFile);
    }

}
