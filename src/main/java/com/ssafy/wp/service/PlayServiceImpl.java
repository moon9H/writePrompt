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

	@Override
	public PlayAnswerResponse submitFinalAnswer(PlayAnswerRequest request) {
		
		// request 검증
		if (request == null || request.getResults() == null || request.getResults().isEmpty()) {
			throw new IllegalArgumentException("최종 제출 결과가 비어 있습니다.");
		}
		
		List<PlayAnswerItem> results = request.getResults();
		
		// 평균 점수는 직접 계산, AI한테 맡기면 오류 가능성 높음
		double avgScore = 0;
		for (PlayAnswerItem answer : results) {
			if (answer.getScore() < 0 || answer.getScore() > 100) {
				throw new IllegalArgumentException("점수는 0점 이상 100점 이하이어야 합니다.");
			}
			
			avgScore += answer.getScore();
		}
		avgScore /= results.size();
		
		// AI 최종 피드백 생성
		String finalFeedback = ffService.generateFinalFeedback(results, avgScore);
		
		return new PlayAnswerResponse(avgScore, finalFeedback);
	}

	@Transactional
	@Override
	// 저장 결과 및 풀이 수 동시 실행
	public int insertResult(int userId, int quizRoomId, PlayAnswerResponse response) {
		
		QuizResult result = new QuizResult();
		
		result.setUserId(userId);
		result.setQuizRoomId(quizRoomId);
		result.setScore(response.getAvgScore());
		result.setFeedback(response.getFinalFeedback());
		
		// 풀이 내역 테이블에 저장
		int insertResult = playDao.insertResult(result);
		// 퀴즈룸 풀이 수 +1
		int solvedResult = playDao.increaseSolvedCnt(quizRoomId);
		
		if (insertResult <= 0 || solvedResult <= 0) {
			throw new IllegalStateException("최종 결과 저장에 실패했습니다.");
		}
		
		return insertResult;
	}
}
