package com.ssafy.wp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.PlayDao;
import com.ssafy.wp.model.dto.QuizInRoomDTO;
import com.ssafy.wp.model.dto.QuizRoom;
import com.ssafy.wp.model.dto.QuizRoomDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayServiceImpl implements PlayService {

	private final PlayDao playDao;
	
	@Override
	public List<QuizRoom> selectAll() {
		return playDao.selectAll();
	}

	@Override
	public List<QuizRoom> searchByTitle(String title) {
		return playDao.searchByTitle(title);
	}

	@Override
	public int increaseLike(int id) {
		return playDao.increaseLike(id);
	}

	@Override
	public QuizRoomDetailResponse selectDetail(int id) {
		
		QuizRoomDetailResponse quizRoom = playDao.selectDetail(id);
		
		if (quizRoom == null) return null;
		
		List<QuizInRoomDTO> quizList = playDao.selectQuizList(id);
		
		quizRoom.setQuizList(quizList);
		
		return quizRoom;
	}
}
