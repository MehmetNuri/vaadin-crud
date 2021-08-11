package com.uniyaz.yb.ui.view;

import com.uniyaz.yb.core.domain.Category;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CategoryViewPage extends VerticalLayout {

    @PropertyId("id")
    TextField idTextField;

    @PropertyId("name")
    TextField nameTextField;


    Category categoryBindObject;

    Window categoryDisplayWindow;

    private BeanFieldGroup<Category> binder;

    public CategoryViewPage(Category category) {
        categoryBindObject = new Category();
        buildMainLayout();
    }

    private void buildMainLayout() {
        setWidth("95%");
        setHeight("160px");
        formBuild();
        buildBinder();
    }


    private void formBuild() {
        idTextField = new TextField();
        idTextField.setCaption("Id");
        idTextField.setVisible(false);
        addComponent(idTextField);

        nameTextField = new TextField();
        nameTextField.setCaption("Kategori Adı");
        addComponent(nameTextField);


    }

    private void buildBinder() {

        binder = new BeanFieldGroup<Category>(Category.class);
        binder.setItemDataSource(categoryBindObject);
        binder.bindMemberFields(this);
    }

    public Window getCategoryDisplayWindow() {
        return categoryDisplayWindow;
    }

    public void setCategoryDisplayWindow(Window categoryDisplayWindow) {
        this.categoryDisplayWindow = categoryDisplayWindow;
    }
}
