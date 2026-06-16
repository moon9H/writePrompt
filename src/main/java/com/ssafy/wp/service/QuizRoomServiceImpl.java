package com.ssafy.wp.service;

import java.util.List;

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
}
