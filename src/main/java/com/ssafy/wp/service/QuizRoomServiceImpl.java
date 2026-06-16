package com.ssafy.wp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.QuizRoomDao;
import com.ssafy.wp.model.dto.QuizRoom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizRoomServiceImpl implements QuizRoomService {
	
	private final QuizRoomDao qrDao;
	
	@Override
	public QuizRoom select(int id) {
		return qrDao.select(id);
	}

	@Override
	public List<QuizRoom> selectAllByUserId(int userId) {
		return qrDao.selectAllByUserId(userId);
	}

	@Override
	public int insert(QuizRoom quizRoom) {
		
		// UUID 기반 랜덤 방 코드 생성
		quizRoom.setRoomCode(UUID.randomUUID().toString().substring(0, 8));
		
		// 최초 퀴즈 룸 생성 시 오픈 상태로 설정
		quizRoom.setState("OPEN");
		
		return qrDao.insert(quizRoom);
	}
}
