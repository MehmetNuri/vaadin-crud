package com.uniyaz.yb.core.dao;

import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.domain.Question;

import java.util.List;

public interface QuestionDao  {

    public void addQuestion(Question question, String categoryName);

    public void updateQuestion(Question question);

    public void deleteQuestion(Question question);

    public List<Question> listQuestions();

    public List<Question> getAllQuestionsWithCategory(Long categoryId);
}
