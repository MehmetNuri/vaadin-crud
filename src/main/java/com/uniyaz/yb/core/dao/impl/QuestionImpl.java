package com.uniyaz.yb.core.dao.impl;

import com.uniyaz.yb.core.dao.QuestionDao;
import com.uniyaz.yb.core.dao.uils.HibernateUtil;
import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.domain.Question;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QuestionImpl implements QuestionDao {
    @Override
    public void addQuestion(Question question, String categoryName) {

        Category category = new Category();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Category q where q.name= :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", categoryName);
            category = (Category) query.getSingleResult();
            question.setCategory(category);
            session.save(question);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateQuestion(Question question) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();


            Question selectedQuestion = (Question) session.get(Question.class, question.getId());

            selectedQuestion.setTitle(question.getTitle());
            selectedQuestion.setDescription(question.getDescription());
            selectedQuestion.setCategory(question.getCategory());

            session.update(selectedQuestion);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteQuestion(Question question) {

        Question deletedQuestion = new Question();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            deletedQuestion = (Question) session.get(Question.class, question.getId());
            session.delete(deletedQuestion);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Question> listQuestions() {
        List<Question> questionList = new ArrayList<Question>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "Select questions From Question questions";
            Query query = session.createQuery(hql);
            questionList = query.list();
            session.close();
        } catch (Exception hata) {
            throw new RuntimeException(hata);
        }
        return questionList;
    }

    @Override
    public List<Question> getAllQuestionsWithCategory(Long categoryId) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Question q where q.category.id= :category";
            Query query = session.createQuery(hql);
            query.setParameter("category", categoryId);
            questionList = query.list();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return questionList;
    }
}
