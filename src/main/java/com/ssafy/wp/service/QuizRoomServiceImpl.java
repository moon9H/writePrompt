package com.ssafy.wp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.wp.model.dao.QuizRoomDao;
import com.ssafy.wp.model.dto.QuizInRoomDTO;
import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.model.dto.QuizRoomCreateRequest;
import com.ssafy.wp.model.dto.QuizRoomDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizRoomServiceImpl implements QuizRoomService {
	
	private final QuizRoomDao qrDao;
	
	@Override
	public List<QuizRoom> selectAllByUserId(int userId) {
		return qrDao.selectAllByUserId(userId);
	}
	
	@Override
	public QuizRoomDetailResponse select(int id) {
		
		QuizRoomDetailResponse quizRoom = qrDao.select(id);
		
		if (quizRoom == null) return null;
		
		// 2. 해당 퀴즈룸에 포함된 퀴즈 목록 조회
		List<QuizInRoomDTO> quizList = qrDao.selectQuizListByRoomId(id);

		quizRoom.setQuizList(quizList);
		
		return quizRoom;
	}

	@Override
	@Transactional
	public int insert(int userId, QuizRoomCreateRequest request) {
		
		QuizRoom quizRoom = new QuizRoom();
		quizRoom.setUserId(userId);
		quizRoom.setTitle(request.getTitle());
		quizRoom.setState(request.getState());
		quizRoom.setLevel(request.getLevel());
		quizRoom.setDescription(request.getDescription());
		// UUID 기반 랜덤으로 RoomCode 설정
		quizRoom.setRoomCode(UUID.randomUUID().toString().substring(0, 8));
		
		
		int result = qrDao.insert(quizRoom);
		
		if (result == 0) return 0;
		
		int quizRoomId = quizRoom.getId();
		
		for (QuizInRoomDTO quiz : request.getQuizList()) {
			qrDao.insertQuizIntoQuizRoom(quizRoomId, quiz.getQuizId(), quiz.getQuizOrder());
		}
		
		return result;
	}

	@Override
	@Transactional
	public int update(int id, int userId, QuizRoomCreateRequest request) {
		
		// 퀴즈룸 기본 정보 수정
		QuizRoom quizRoom = new QuizRoom();
		quizRoom.setId(id);
		quizRoom.setUserId(userId);
		quizRoom.setTitle(request.getTitle());
		quizRoom.setState(request.getState());
		quizRoom.setLevel(request.getLevel());
		quizRoom.setDescription(request.getDescription());
		
		int result = qrDao.update(quizRoom);
		
		if (result == 0) {
			return 0;
		}
		
		// 기존 연결된 퀴즈 목록 삭제
		qrDao.deleteQuizListByRoomId(id, userId);
		
		// 요청으로 받은 퀴즈 목록 다시 연결
		for (QuizInRoomDTO quiz : request.getQuizList()) {
			qrDao.insertQuizIntoQuizRoom(
					id,
					quiz.getQuizId(),
					quiz.getQuizOrder()
			);
		}
		return result;
	}

	@Override
	@Transactional
	public int delete(int id, int userId) {
		
		// 1. 연결 테이블 삭제
		qrDao.deleteQuizListByRoomId(id, userId);
		
		// 2. 퀴즈룸 삭제
		return qrDao.delete(id, userId);
	}
}
