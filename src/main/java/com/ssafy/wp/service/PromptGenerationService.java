package com.ssafy.wp.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dto.ai.PromptGenerateRequest;

@Service
public class PromptGenerationService {
	
	private final ChatClient promptGenerationChatClient;
	
	public PromptGenerationService(
					@Qualifier("promptGenerationChatClient") ChatClient promptGenerationChatClient ) {
		this.promptGenerationChatClient = promptGenerationChatClient;
	}
	
	public String generatePrompt(PromptGenerateRequest request) {
		
		String userPrompt = """

		        [사용자 요청]

		        아래의 퀴즈 이미지 키워드를 바탕으로 이미지 생성을 위한 프롬프트를 작성해줘.

		        난이도: {level}
		        난이도 반영 방식: {levelGuide}
		        배경: {background}
		        주제: {theme}
		        감정/분위기: {emotion}
		        캐릭터: {character}
		        그림체/매체: {artMedia}
		        색감: {color}
		        카메라 구도: {cameraShot}
		        """;

        String result = promptGenerationChatClient.prompt()
                .user(spec -> spec.text(userPrompt)
                        .param("level", request.getLevel())
                        .param("levelGuide", getLevelGuide(request.getLevel()))
                        .param("background", request.getBackground())
                        .param("theme", request.getTheme())
                        .param("emotion", request.getEmotion())
                        .param("character", request.getCharacter())
                        .param("artMedia", request.getArtMedia())
                        .param("color", request.getColor())
                        .param("cameraShot", request.getCameraShot()))
                .call()
                .content();
        
        if (result == null || result.isBlank()) {
            throw new IllegalStateException("프롬프트 생성 결과가 비어 있음.");
        }
        
        return result.trim();
	}
	
	// 난이도 반영이 잘 되도록 유도
	private String getLevelGuide(String level) {
	
	    if ("초급".equals(level)) {
	        return "학생이 단순한 프롬프트를 작성해도 비슷한 이미지가 나올 수 있도록, 핵심 주제와 가장 중요한 시각 요소만 포함한 짧고 단순한 프롬프트로 작성한다. 세부 조건은 최소화한다.";
	    }
	    if ("중급".equals(level)) {
	        return "학생이 주요 조건을 어느 정도 맞춰야 비슷한 이미지가 나오도록, 주제, 배경, 캐릭터, 분위기, 그림체를 적당히 포함한 중간 수준의 구체적인 프롬프트로 작성한다.";
	    }
	    if ("고급".equals(level)) {
	        return "학생이 세부 조건까지 정확히 맞춰야 비슷한 이미지가 나오도록, 배경, 캐릭터의 행동, 구도, 색감, 그림체, 카메라 시점, 장면의 세부 요소를 구체적으로 포함한 프롬프트로 작성한다.";
	    }

	    return "난이도에 따라 프롬프트의 구체성, 조건의 수, 장면의 세부 묘사 정도를 조절한다.";
	}
}
