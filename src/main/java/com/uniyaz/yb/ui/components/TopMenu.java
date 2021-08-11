package com.uniyaz.yb.ui.components;

import com.uniyaz.yb.QuestionPoolUI;
import com.uniyaz.yb.ui.fields.QuestionItemGrid;
import com.uniyaz.yb.ui.view.CategoryAddAndUpdatePage;
import com.uniyaz.yb.ui.view.CategoryListPage;
import com.uniyaz.yb.ui.view.QuestionAddPage;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class TopMenu extends HorizontalLayout {
    private QuestionContent questionContent;
    private Sidebar sidebar;

    public TopMenu( ) {
        setSizeFull();
        buildTopMenuLayout();
        this.questionContent = ((QuestionPoolUI) UI.getCurrent()).getQuestionContent();
        this.sidebar = ((QuestionPoolUI) UI.getCurrent()).getSidebar();
    }

    private void buildTopMenuLayout() {
        buildMenuItems();
    }

    private void buildMenuItems() {

        MenuBar menuBar = new MenuBar();
        menuBar.setWidth("100%");


        MenuBar.MenuItem homeButton = menuBar.addItem("Anasayfa", FontAwesome.HOME, this::goHome);

        MenuBar.MenuItem categoriesButton = menuBar.addItem("Kategori", FontAwesome.FOLDER, null);
        addCateory(categoriesButton);
        listCategory(categoriesButton);

        MenuBar.MenuItem questionsButton = menuBar.addItem("Sorular", FontAwesome.QUESTION, null);
        addQuestion(questionsButton);
        listQuestion(questionsButton);
        addComponent(menuBar);



        this.setSpacing(true);
        menuBar.addStyleName(ValoTheme.MENU_ROOT);
    }

    private void goHome(MenuBar.MenuItem homeButton) {

        QuestionItemGrid questionItemGrid = new QuestionItemGrid();
        questionContent.setQuestionContent(questionItemGrid);

    }


    private void listQuestion(MenuBar.MenuItem articleButton) {
        MenuBar.MenuItem listQuestionButton = articleButton.addItem("Soruları Listele", FontAwesome.LIST, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
            }
        });
    }

    private void addQuestion(MenuBar.MenuItem articleButton) {
        MenuBar.MenuItem addQuestionButton = articleButton.addItem("Soru Ekle", FontAwesome.PLUS, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {

                QuestionAddPage questionAddPage = new QuestionAddPage();
                questionContent.setQuestionContent(questionAddPage);

            }
        });
    }


    private void addCateory(MenuBar.MenuItem categoryButton) {
        MenuBar.MenuItem addCategoryButton = categoryButton.addItem("Kategori Ekle", FontAwesome.PLUS, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                CategoryAddAndUpdatePage categoryAddPage = new CategoryAddAndUpdatePage();
                questionContent.setQuestionContent(categoryAddPage);
            }
        });
    }

    private void listCategory(MenuBar.MenuItem categoryButton) {
        MenuBar.MenuItem listCategoryButton = categoryButton.addItem("Kategorileri Listele", FontAwesome.LIST, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                CategoryListPage categoryListPage = new CategoryListPage();
                questionContent.setQuestionContent(categoryListPage);
            }
        });
    }
}
