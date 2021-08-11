package com.uniyaz.yb.ui.fields;

import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.service.CategoryService;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;

import java.util.List;

public class CategoryComboBoxField extends ComboBox {

    private CategoryService categoryService;

    public CategoryComboBoxField() {
        this.categoryService = new CategoryService();
        this.setDescription("Lütfen kategori seçiniz...");
        fillComboBox();
    }

    private void fillComboBox() {
        this.removeAllItems();
        List<Category> categoryList = categoryService.listCategory();
        this.setRequired(true);
        for (Category category : categoryList) {
            this.addItem(category.getName());
        }
    }
}
