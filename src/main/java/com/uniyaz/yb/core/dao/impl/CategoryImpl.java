package com.uniyaz.yb.core.dao.impl;

import com.uniyaz.yb.core.dao.CategoryDao;
import com.uniyaz.yb.core.dao.uils.HibernateUtil;
import com.uniyaz.yb.core.domain.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryImpl implements CategoryDao {
    @Override
    public void addCategory(Category category) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
            session.close();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categories = new ArrayList<Category>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "Select name From Category name";
            Query query = session.createQuery(hql);
            categories = query.list();
            session.close();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return categories;
    }

    @Override
    public void deleteCategory(Category category) {
        Category deletedCategory = new Category();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            deletedCategory = (Category) session.get(Category.class, category.getId());
            session.delete(deletedCategory);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Category selectedCategory = (Category) session.get(Category.class, category.getId());
            selectedCategory.setName(category.getName());
            session.update(selectedCategory);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
