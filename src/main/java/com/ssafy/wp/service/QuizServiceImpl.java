package com.ssafy.wp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.wp.model.dao.QuizDao;
import com.ssafy.wp.model.dto.quiz.Quiz;
import com.ssafy.wp.model.dto.quiz.QuizCreateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{
	
	private final QuizDao qDao;

	@Override
	public Quiz insert(int userId, QuizCreateRequest request) {
		Quiz quiz = new Quiz();

	    quiz.setUserId(userId);
	    quiz.setTitle(request.getTitle());
	    quiz.setImage(request.getImage());
	    quiz.setLevel(request.getLevel());
	    
	    int result = qDao.insert(quiz);

	    if (result <= 0) {
	        return null;
	    }

	    return qDao.select(quiz.getId());
	}

	@Override
	public int delete(int id, int userId) {
		return qDao.delete(id, userId);
	}

	@Override
	public int update(int id, int userId, String title) {
		return qDao.update(id, userId, title);
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
