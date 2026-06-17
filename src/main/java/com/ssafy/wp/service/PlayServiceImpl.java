package com.ssafy.wp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.PlayDao;
import com.ssafy.wp.model.dto.QuizRoom;

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
}
