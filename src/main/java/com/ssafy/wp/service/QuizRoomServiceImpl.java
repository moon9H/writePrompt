package com.ssafy.wp.service;

import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.QuizRoomDao;
import com.ssafy.wp.model.dto.QuizRoom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizRoomServiceImpl implements QuizRoomService {
	
	private final QuizRoomDao qrDao;
	
	@Override
	public QuizRoom select(Long id) {
		return qrDao.select(id);
	}
}
