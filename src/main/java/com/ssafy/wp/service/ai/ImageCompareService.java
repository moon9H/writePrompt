package com.ssafy.wp.service.ai;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.ssafy.wp.model.dto.ai.ImageCompareRequest;
import com.ssafy.wp.model.dto.ai.ImageCompareResult;

@Service
public class ImageCompareService {

    private final ChatClient imageCompareChatClient;

    // GMS 요청 크기 제한을 고려한 비교용 이미지 설정
    private static final int COMPARE_IMAGE_MAX_SIZE = 384;
    // AI에게 보낼 압축된 이미지 1장의 최대 허용 크기 (약 40KB, GMS 한계가 100KB이므로)
    private static final long COMPARE_IMAGE_MAX_BYTES = 40 * 1024;

    public ImageCompareService(
            @Qualifier("imageCompareChatClient") ChatClient imageCompareChatClient) {
        this.imageCompareChatClient = imageCompareChatClient;
    }

    public ImageCompareResult compareImages(ImageCompareRequest request) {
    	
        // 원본 이미지를 그대로 보내지 않고, 비교용으로 압축한 이미지 전달
        Resource answerImage = getCompressedImageResource(request.getAnswerImageUrl());
        Resource stdImage = getCompressedImageResource(request.getStudentImageUrl());

        String userPrompt = """
                Compare the following two images.

                The first image is the teacher's answer image.
                The second image is the student's generated image.

                Evaluate how visually similar the student's image is to the teacher's answer image.

                Return a score from 0 to 100 and short feedback in Korean.
                """;

        ImageCompareResult result = imageCompareChatClient.prompt()
                .user(spec -> spec
                		.media(MimeTypeUtils.parseMimeType("image/jpeg"), answerImage)
                        .media(MimeTypeUtils.parseMimeType("image/jpeg"), stdImage)
                        .text(userPrompt))
                .call()
                .entity(ImageCompareResult.class);

        if (result == null) {
            throw new IllegalStateException("이미지 비교 결과가 비어 있음.");
        }

        return result;
    }

    private Resource getCompressedImageResource(String imgUrl) {
        Path originalPath = getImagePath(imgUrl);

        try {
            BufferedImage originalImage = ImageIO.read(originalPath.toFile());

            if (originalImage == null) {
                throw new IllegalArgumentException("이미지 파일을 읽을 수 없습니다.");
            }
            
            // AI 비교에는 고해상도 원본이 필요하지 않으므로, 요청 크기를 줄이기 위해 리사이즈 후 JPEG로 압축
            BufferedImage resizedImage = resizeImage(originalImage, COMPARE_IMAGE_MAX_SIZE);

            byte[] compressedBytes = compressJpeg(resizedImage);

            return new ByteArrayResource(compressedBytes);

        } catch (IOException e) {
            throw new IllegalStateException("이미지 압축 중 오류가 발생했습니다.");
        }
    }

    private Path getImagePath(String imgUrl) {

        if (imgUrl == null || !imgUrl.startsWith("/uploads/images/")) {
            throw new IllegalArgumentException("잘못된 이미지 경로입니다.");
        }

        // DB에는 /uploads/images/{fileName}.png 형태로 저장
        String fileName = imgUrl.replace("/uploads/images/", "");

        // 실제 서버 파일은 uploads/images/{fileName}.png 위치
        Path imagePath = Paths.get("uploads/images", fileName);

        if (!Files.exists(imagePath)) {
            throw new IllegalArgumentException("이미지 파일을 찾을 수 없습니다.");
        }

        return imagePath;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int maxSize) {

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        
     // 원본 비율을 유지하면서 긴 변이 maxSize를 넘지 않도록 축소
        double ratio = Math.min(
                (double) maxSize / originalWidth,
                (double) maxSize / originalHeight
        );

        int newWidth = Math.max(1, (int) (originalWidth * ratio));
        int newHeight = Math.max(1, (int) (originalHeight * ratio));

        BufferedImage resizedImage = new BufferedImage(
                newWidth,
                newHeight,
                BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );

        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return resizedImage;
    }

    private byte[] compressJpeg(BufferedImage image) throws IOException {

        float quality = 0.4f;
        byte[] compressedBytes = null;
        
     // 목표 용량을 넘으면 JPEG 품질을 낮춰가며 재압축
        while (quality >= 0.2f) {
            compressedBytes = writeJpegToBytes(image, quality);

            if (compressedBytes.length <= COMPARE_IMAGE_MAX_BYTES) {
                return compressedBytes;
            }

            quality -= 0.1f;
        }

        // 그래도 크면 최저 품질 결과 사용
        return compressedBytes;
    }

    private byte[] writeJpegToBytes(BufferedImage image, float quality) throws IOException {

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

        if (!writers.hasNext()) {
            throw new IllegalStateException("JPEG Writer를 찾을 수 없습니다.");
        }

        ImageWriter writer = writers.next();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ImageOutputStream ios = ImageIO.createImageOutputStream(baos)) {

            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);

            writer.write(null, new IIOImage(image, null, null), param);

            ios.flush();

            return baos.toByteArray();

        } finally {
            writer.dispose();
        }
    }
}