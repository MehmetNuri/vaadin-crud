package com.uniyaz.yb.core.dao;

import com.uniyaz.yb.core.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    public void addCategory(Category category);

    public List<Category> listCategory();

    public void deleteCategory(Category category);

    public void updateCategory(Category category);




}
