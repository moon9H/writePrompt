package com.ssafy.wp.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileImageUploadService {

    private static final Map<String, String> ALLOWED_IMAGE_EXTENSIONS = Map.of(
            "image/png", "png",
            "image/jpeg", "jpg",
            "image/webp", "webp"
    );

    @Value("${app.upload.profile-dir:uploads/profiles}")
    private String profileUploadDir;

    @Value("${app.upload.profile-url-prefix:/uploads/profiles}")
    private String profileUrlPrefix;

    @Value("${app.upload.profile-max-size:5MB}")
    private String profileMaxSize;

    public String upload(MultipartFile file) throws IOException {
        validate(file);

        String extension = ALLOWED_IMAGE_EXTENSIONS.get(file.getContentType());
        String fileName = UUID.randomUUID() + "." + extension;

        Path uploadDir = Paths.get(profileUploadDir);
        Files.createDirectories(uploadDir);

        Path savePath = uploadDir.resolve(fileName).normalize();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return profileUrlPrefix + "/" + fileName;
    }

    private void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 이미지 파일이 없습니다.");
        }

        if (!ALLOWED_IMAGE_EXTENSIONS.containsKey(file.getContentType())) {
            throw new IllegalArgumentException("png, jpeg, webp 이미지 파일만 업로드할 수 있습니다.");
        }

        long maxSizeBytes = DataSize.parse(profileMaxSize).toBytes();
        if (file.getSize() > maxSizeBytes) {
            throw new IllegalArgumentException("프로필 이미지는 " + profileMaxSize + " 이하만 업로드할 수 있습니다.");
        }
    }
}
