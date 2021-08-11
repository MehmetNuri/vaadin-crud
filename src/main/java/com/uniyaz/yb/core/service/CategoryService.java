package com.uniyaz.yb.core.service;

import com.uniyaz.yb.core.dao.impl.CategoryImpl;
import com.uniyaz.yb.core.domain.Category;

import java.util.List;

public class CategoryService {
    CategoryImpl categoryImpl = new CategoryImpl();

    public void addCategory(Category category) {
        categoryImpl.addCategory(category);
    }

    public List<Category> listCategory() {
        return categoryImpl.listCategory();
    }

    public void deleteCategory(Category category) {
        categoryImpl.deleteCategory(category);
    }

    public void updateCategory(Category category) {
        categoryImpl.updateCategory(category);
    }
}
