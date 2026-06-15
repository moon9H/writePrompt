package com.ssafy.wp.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageGenerationService {
	
	private final OpenAiImageModel iModel;
	
	// 이미지 생성 요청마다 공통 적용되는 프롬프트 (시스템 프롬프트같은 역할)
    @Value("classpath:/prompts/image_generation_system_prompt.txt")
    private Resource imageSystemPrompt;
    
    public String generateImage(String userPrompt) throws IOException{
    	
    	// 이미지 모델은 defaultSystem 같은 구조가 없어서 한번에 프롬프트를 만들어서 넘겨야 됨.
    	String systemPrompt = imageSystemPrompt.getContentAsString(StandardCharsets.UTF_8);
    	
    	String finalPrompt = systemPrompt + "\n\n [User request]\n" + userPrompt;
    	
    	ImageMessage imageMessage = new ImageMessage(finalPrompt);
    	
    	// image 생성 옵션
    	// 요청 하나당 이미지 1장 (크기 : 1024 x 1024)
    	// 토큰 비용 생각하면 나중에 조정 필요
    	ImageOptions imageOptions = ImageOptionsBuilder.builder()
    									.model("gpt-image-1-mini")
    									.N(1)
    									.height(1024)
    									.width(1024)
    									.build();
    	
    	ImagePrompt iPrompt = new ImagePrompt(imageMessage, imageOptions);
    	
    	String base64 = iModel.call(iPrompt)
    					.getResult()
    					.getOutput()
    					.getB64Json();

    	if (base64 == null || base64.isBlank()) {
    		throw new IllegalStateException("이미지 생성 결과가 비어 있음.");
    	}
    	
    	return saveBase64Image(base64);
    }
    
    // base64 이미지 데이터를 png로 서버에 저장 후, 프론트에서 접근 가능한 이미지 URL 반환
    private String saveBase64Image(String base64) throws IOException{
    	
    	// data:image/png;base64, 같은 prefix가 붙어 오는 경우 제거
        if (base64.contains(",")) {
            base64 = base64.substring(base64.indexOf(",") + 1);
        }
    	
    	// 파일명 중복 회피
    	String fileName = UUID.randomUUID() + ".png";
    	
    	Path uploadDir = Paths.get("uploads/images");
    	
    	Files.createDirectories(uploadDir);
    	
    	Path savePath = uploadDir.resolve(fileName);
    	

        byte[] imageBytes = Base64.getDecoder().decode(base64);

        Files.write(savePath, imageBytes);
        
        // 실제 저장 경로가 아니라 프론트에서 접근할 URL 반환
        // WebConfig같은 파일에서 /uploads/images/** 경로를 실제 uploads/images 디렉토리와 매핑 필요
        return "/uploads/images/" + fileName;
    }
}