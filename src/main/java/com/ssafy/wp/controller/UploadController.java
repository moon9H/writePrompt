package com.ssafy.wp.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.wp.common.response.ApiResponse;
import com.ssafy.wp.model.dto.upload.ProfileImageUploadResponse;
import com.ssafy.wp.service.ProfileImageUploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Tag(name = "Upload", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uploads")
public class UploadController {

    private final ProfileImageUploadService profileImageUploadService;

    @Operation(
            summary = "프로필 이미지 업로드",
            description = "회원가입 또는 회원 정보 수정 전에 프로필 이미지를 업로드하고 접근 가능한 이미지 URL을 반환합니다.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = ProfileImageUploadRequest.class)
                    )
            )
    )
    @PostMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProfileImageUploadResponse>> uploadProfileImage(
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        String imageUrl = profileImageUploadService.upload(file);

        return ResponseEntity.ok(
                ApiResponse.ok("프로필 이미지 업로드 성공", new ProfileImageUploadResponse(imageUrl))
        );
    }

    @Getter
    public static class ProfileImageUploadRequest {

        @Schema(description = "업로드할 프로필 이미지 파일", type = "string", format = "binary")
        private MultipartFile file;
    }
}
