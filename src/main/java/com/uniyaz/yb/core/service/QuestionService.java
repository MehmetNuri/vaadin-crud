package com.uniyaz.yb.core.service;

import com.uniyaz.yb.core.dao.impl.QuestionImpl;
import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.domain.Question;

import java.util.List;

public class QuestionService {
    QuestionImpl questionImpl = new QuestionImpl();

    public void addQuestion(Question question, String categoryName) {
        questionImpl.addQuestion(question,categoryName);
    }

    public List<Question> listQuestions() {
        return questionImpl.listQuestions();
    }

    public void updateQuestion(Question question) {
        questionImpl.updateQuestion(question);
    }

    public void deleteQuestion(Question question) {
        questionImpl.deleteQuestion(question);
    }

    public List<Question> getAllQuestionsWithCategory(Long categoryId) {
        return questionImpl.getAllQuestionsWithCategory(categoryId);
    }
}
