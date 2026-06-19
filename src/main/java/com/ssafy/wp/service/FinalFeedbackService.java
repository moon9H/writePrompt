package com.ssafy.wp.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dto.PlayAnswerItem;

@Service
public class FinalFeedbackService {
	
	private final ChatClient finalFeedbackChatClient;
	
	public FinalFeedbackService(@Qualifier("finalFeedbackChatClient") ChatClient finalFeedbackChatClient) {
		this.finalFeedbackChatClient = finalFeedbackChatClient;
	}
	
	public String generateFinalFeedback(List<PlayAnswerItem> results, double avgScore) {
		String userPrompt = buildUserPrompt(results, avgScore);

		String finalFeedback = finalFeedbackChatClient.prompt()
				.user(userPrompt)
				.call()
				.content();
		
		if (finalFeedback == null || finalFeedback.isBlank()) {
			throw new IllegalStateException("최종 피드백 생성 결과가 비어 있습니다.");
		}
		
		return finalFeedback.trim();
	}

	private String buildUserPrompt(List<PlayAnswerItem> results, double avgScore) {
		
		List<PlayAnswerItem> sortedResults = results.stream()
				.sorted((a, b) -> Integer.compare(a.getQuizOrder(), b.getQuizOrder()))
				.toList();
		
		StringBuilder sb = new StringBuilder();

		sb.append("The following is the user's image-generation quiz result.\n\n");
		
		sb.append("Average score: ")
				.append(String.format("%.2f", avgScore))
				.append("\n\n");
		
		sb.append("Per-question results:\n");
		
		for (PlayAnswerItem item : sortedResults) {

			sb.append(item.getQuizOrder()).append(".\n");
			sb.append("- Score: ").append(item.getScore()).append("\n");
			sb.append("- Feedback: ").append(item.getFeedback()).append("\n\n");
		}
		
		sb.append("Write the final overall feedback for the user.");
		
		return sb.toString();
	}
}
