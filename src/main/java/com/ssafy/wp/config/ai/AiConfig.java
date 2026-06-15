package com.ssafy.wp.config.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class AiConfig {
	
	@Value("classpath:/prompts/prompt_generation_system_prompt.txt")
    private Resource promptGenerationSystemPrompt;
	
	@Bean
    RestClient.Builder restClientBuilder() {
        // SSAFY GMS 특징: streaming 방식의 transfer-encoding: chunked 비활성화로 인해 buffering 필요(length 속성이 필요하기 때문?)
        return RestClient.builder().requestFactory(

                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    @Bean("promptGenerationChatClient")
    ChatClient promptGenerationChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultSystem(promptGenerationSystemPrompt)
                .build();
    }
}
