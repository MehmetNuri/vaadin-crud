package com.uniyaz.yb.ui.view;

import com.uniyaz.yb.QuestionPoolUI;
import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.service.CategoryService;
import com.uniyaz.yb.ui.components.Sidebar;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;


public class CategoryAddAndUpdatePage extends VerticalLayout {


    @PropertyId("id")
    TextField idTextField;

    @PropertyId("name")
    TextField nameTextField;

    Table table;
    IndexedContainer indexedContainer;

    private BeanFieldGroup<Category> binder;

    Button categoryOperationsButton;
    CategoryService categoryService;
    Window categoryUpdateWindow;
    Category categoryBindObject;

    public CategoryAddAndUpdatePage(Category category) {

        categoryBindObject = category;
        builMainLayout();
        setWidth("350");
        setHeight("300px");

        categoryOperationsButton.setIcon(FontAwesome.PENCIL);
        categoryOperationsButton.setCaption("Güncelle");
        categoryOperationsButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        editUpdateForm(category);
        this.setMargin(true);
        Page.getCurrent().setTitle("Kategori Güncelle | "+category.getName() );


    }

    public CategoryAddAndUpdatePage() {
        Page.getCurrent().setTitle("Kategori Ekle");
        categoryBindObject = new Category();
        builMainLayout();

        categoryOperationsButton.setIcon(FontAwesome.SAVE);
        categoryOperationsButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        categoryOperationsButton.setCaption("Ekle");

        nameTextField.setValue("");

        this.setMargin(true);
        this.setSpacing(true);

        buildTableIndexedContainer();
        buildTable();
        fillTable();
        Label lblTableTitle = new Label();

        lblTableTitle.setValue("Kategoriler");
        lblTableTitle.addStyleName(ValoTheme.LABEL_H3);
        addComponent(lblTableTitle);
        addComponent(table);

        table.addListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 2068314108919135281L;

            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Notification.show("\uD83E\uDD37\uD83C\uDFFD\u200D♂️","Henüz bu bölüm eklenmedi :)", Notification.TYPE_WARNING_MESSAGE);
                }
            }
        });



    }

    private void builMainLayout() {
        formBuild();
        buildBinder();
    }

    private void buildBinder() {

        binder = new BeanFieldGroup<Category>(Category.class);
        binder.setItemDataSource(categoryBindObject);
        binder.bindMemberFields(this);
    }

    private void formBuild() {

        idTextField = new TextField("Categori Id");
        idTextField.setValue("0");
        addComponent(idTextField);
        idTextField.setVisible(false);

        nameTextField = new TextField("Kategori Adı");
        addComponent(nameTextField);

        createCategoryOperationsButton();
        this.setMargin(true);

    }

    private void createCategoryOperationsButton() {

        categoryOperationsButton = new Button();
        addComponent(categoryOperationsButton);

        categoryOperationsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                try {
                    runBinder();
                    categoryService = new CategoryService();
                    if (categoryBindObject.getId() != null) {
                        Notification.show(" ✔️",categoryBindObject.getName() + "  adlı kategori  güncellendi !", Notification.TYPE_HUMANIZED_MESSAGE);
                        categoryService.updateCategory(categoryBindObject);
                        Page.getCurrent().setTitle("Kategori Güncelle | "+categoryBindObject.getName() + " güncellendi");

                    } else {


                        if (categoryBindObject.getName() == null || categoryBindObject.getName().equals("")) {
                            Notification.show("✖","Lütfen kategori adı giriniz !", Notification.TYPE_ERROR_MESSAGE);
                            nameTextField.clear();
                        } else {

                            Notification.show(" ✔️",categoryBindObject.getName() + " adlı kategori eklendi  ! ",Notification.TYPE_HUMANIZED_MESSAGE);
                            categoryService.addCategory(categoryBindObject);
                            fillTable();
                            nameTextField.clear();
                            Sidebar sidebar =   ((QuestionPoolUI) QuestionPoolUI.getCurrent()).getSidebar();
                            SidebarPage sidebarPage = sidebar.getSidebarPage();
                            sidebarPage.fillSidebar();

                        }
                    }

                    categoryBindObject = new Category();
                    binder.setItemDataSource(categoryBindObject);

                    UI.getCurrent().getUI().removeWindow(getCategoryUpdateWindow());
                } catch (Exception e) {
                    Notification.show(e.getMessage());
                }
            }
        });
    }

    private void runBinder() {

        try {
            binder.commit();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Lütfen form alanlarını eksiksiz doldurun");
        }
    }

    public Window getCategoryUpdateWindow() {
        return categoryUpdateWindow;
    }

    public void setCategoryUpdateWindow(Window categoryUpdateWindow) {
        this.categoryUpdateWindow = categoryUpdateWindow;
    }

    private void editUpdateForm(Category category) {

        idTextField.setVisible(true);
        idTextField.setEnabled(false);
        this.setMargin(true);
    }


    private void fillTable() {

        indexedContainer.removeAllItems();
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.listCategory();

        for (Category category : categories) {
            Item item = indexedContainer.addItem(category);
            Button editButton = new Button();
            editButton.setIcon(FontAwesome.PENCIL);
            editButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
            editCategory(category, editButton);


            Button deleteButton = new Button();
            deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
            deleteButton.setIcon(FontAwesome.TRASH);
            deleteCategory(category, deleteButton);

            fillColumns(category, item, editButton, deleteButton);
        }
    }

    private void editCategory(Category category, Button editButton) {
        editButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        editButton.setIcon(FontAwesome.PENCIL);
        editButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Window updateWindow = new Window("Kategori Güncelleme");

                CategoryAddAndUpdatePage selectedCategoryUpdateWindow = new CategoryAddAndUpdatePage(category);
                selectedCategoryUpdateWindow.setCategoryUpdateWindow(updateWindow);
                selectedCategoryUpdateWindow.setMargin(true);

                updateWindow.setContent(selectedCategoryUpdateWindow);
                selectedCategoryUpdateWindow.setMargin(true);
                updateWindow.center();
                updateWindow.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent closeEvent) {
                        fillTable();

                        Sidebar sidebar =   ((QuestionPoolUI) QuestionPoolUI.getCurrent()).getSidebar();
                        SidebarPage sidebarPage = sidebar.getSidebarPage();
                        sidebarPage.fillSidebar();
                    }
                });
                UI.getCurrent().addWindow(updateWindow);
            }
        });
    }


    private void deleteCategory(Category category, Button deleteButton) {

        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Window deleteWindow = new Window("Kategori Silme");
                deleteWindow.setWidth("25%");
                deleteWindow.setHeight("170px");

                CategoryDeletePage categoryDeletePage = new CategoryDeletePage(category);
                categoryDeletePage.setDeleteWindow(deleteWindow);
                categoryDeletePage.setMargin(true);
                categoryDeletePage.setSpacing(true);
                deleteWindow.setContent(categoryDeletePage);
                deleteWindow.center();

                deleteWindow.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent closeEvent) {
                        fillTable();
                    }
                });
                UI.getCurrent().addWindow(deleteWindow);
            }
        });
    }

    private void fillColumns(Category category, Item item, Button editButton, Button deleteButton) {
        item.getItemProperty("update").setValue(editButton);
        item.getItemProperty("delete").setValue(deleteButton);

        item.getItemProperty("id").setValue(category.getId());
        item.getItemProperty("name").setValue(category.getName());

    }

    private void buildTable() {
        table = new Table();
        table.setContainerDataSource(indexedContainer);

        table.setWidth("100%");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.setColumnHeaders( "Id", "Ad","Güncelle", "Sil");


        table.setColumnWidth("id",30);
        table.setColumnWidth("name",300);

        table.setColumnWidth("update", 80);
        table.setColumnAlignment("update", Table.Align.LEFT);
        table.setColumnWidth("delete", 80);
        table.setColumnAlignment("delete", Table.Align.LEFT);

    }

    private void buildTableIndexedContainer() {

        indexedContainer = new IndexedContainer();

        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("name", String.class, null);

        indexedContainer.addContainerProperty("update", Button.class, null);
        indexedContainer.addContainerProperty("delete",  Button.class, null);







    }

}





