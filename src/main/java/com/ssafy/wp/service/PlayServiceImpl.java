package com.ssafy.wp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.wp.model.dao.PlayDao;
import com.ssafy.wp.model.dto.play.PlayAnswerItem;
import com.ssafy.wp.model.dto.play.PlayAnswerRequest;
import com.ssafy.wp.model.dto.play.PlayAnswerResponse;
import com.ssafy.wp.model.dto.play.QuizResult;
import com.ssafy.wp.model.dto.quizroom.QuizInRoomDTO;
import com.ssafy.wp.model.dto.quizroom.QuizRoom;
import com.ssafy.wp.model.dto.quizroom.QuizRoomDetailResponse;
import com.ssafy.wp.service.ai.FinalFeedbackService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayServiceImpl implements PlayService {

	private final PlayDao playDao;
	private final FinalFeedbackService ffService;
	
	@Override
	public List<QuizRoom> selectAll() {
		return playDao.selectAll();
	}

	@Override
	public List<QuizRoom> searchByTitle(String title) {
		return playDao.searchByTitle(title);
	}

	@Override
	public int increaseLike(int quizRoomId) {
		return playDao.increaseLike(quizRoomId);
	}

	@Override
	public QuizRoomDetailResponse selectDetail(int quizRoomId) {
		
		QuizRoomDetailResponse quizRoom = playDao.selectDetail(quizRoomId);
		
		if (quizRoom == null) return null;
		
		List<QuizInRoomDTO> quizList = playDao.selectQuizList(quizRoomId);
		
		quizRoom.setQuizList(quizList);
		
		return quizRoom;
	}
	
	@Transactional
	@Override
	public PlayAnswerResponse submitFinalAnswer(int userId, int quizRoomId, PlayAnswerRequest request) {
		if (request == null || request.getResults() == null || request.getResults().isEmpty()) {
			throw new IllegalArgumentException("최종 제출 결과가 비어 있습니다.");
		}
		
		List<PlayAnswerItem> results = request.getResults();
		
		double avgScore = calculateAverageScore(results);
		
		String finalFeedback = ffService.generateFinalFeedback(results, avgScore);
		
		PlayAnswerResponse response = new PlayAnswerResponse(avgScore, finalFeedback);
		
		saveFinalResult(userId, quizRoomId, response);
		
		return response;
	}
	
	private double calculateAverageScore(List<PlayAnswerItem> results) {
		double avgScore = 0;
		
		for (PlayAnswerItem answer : results) {
			if (answer.getScore() < 0 || answer.getScore() > 100) {
				throw new IllegalArgumentException("점수는 0점 이상 100점 이하이어야 합니다.");
			}
			
			avgScore += answer.getScore();
		}
		
		return avgScore / results.size();
	}
	
	private void saveFinalResult(int userId, int quizRoomId, PlayAnswerResponse response) {
		QuizResult result = new QuizResult();
		
		result.setUserId(userId);
		result.setQuizRoomId(quizRoomId);
		result.setScore(response.getAvgScore());
		result.setFeedback(response.getFinalFeedback());
		
		int insertResult = playDao.insertResult(result);
		int solvedResult = playDao.increaseSolvedCnt(quizRoomId);
		
		if (insertResult <= 0 || solvedResult <= 0) {
			throw new IllegalStateException("최종 결과 저장에 실패했습니다.");
		}
	}
}
