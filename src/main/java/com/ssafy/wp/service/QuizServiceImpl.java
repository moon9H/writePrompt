package com.ssafy.wp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.QuizDao;
import com.ssafy.wp.model.dto.quiz.Quiz;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{
	
	private final QuizDao qDao;

	@Override
	public int insert(Quiz quiz) {
		return qDao.insert(quiz);
	}

	@Override
	public int delete(int id, int userId) {
		return qDao.delete(id, userId);
	}

	@Override
	public int update(int id, String title) {
		return qDao.update(id, title);
	}

	@Override
	public List<Quiz> selectAll(int userId) {
		return qDao.selectAll(userId);
	}

	@Override
	public Quiz select(int id) {
		return qDao.select(id);
	}
}
