package com.uniyaz.yb.ui.view;

import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.domain.Question;
import com.uniyaz.yb.core.service.CategoryService;
import com.uniyaz.yb.core.service.QuestionService;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

public class CategoryListPage extends VerticalLayout {

    Table table;
    IndexedContainer indexedContainer;

    public CategoryListPage() {
        buildTableIndexedContainer();
        buildTable();
        fillTable();
        Label lblTableTitle = new Label();

        lblTableTitle.setValue("Kategoriler");
        lblTableTitle.addStyleName(ValoTheme.LABEL_H3);
        addComponent(lblTableTitle);
        addComponent(table);


        QuestionService questionService = new QuestionService();
        List<Question>  nedir = questionService.getAllQuestionsWithCategory(8L);
        System.out.println(nedir);

        table.addListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 2068314108919135281L;

            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Notification.show("\uD83E\uDD37\uD83C\uDFFD\u200D♂️","Henüz bu bölüm eklenmedi :)", Notification.TYPE_WARNING_MESSAGE);
                }
            }
        });
        
    }

    private void fillTable() {


        indexedContainer.removeAllItems();
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.listCategory();
        table.addStyleName(ValoTheme.TABLE_COMPACT);

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

            SidebarPage sidebarPage = new SidebarPage();
            sidebarPage.fillSidebar();
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
