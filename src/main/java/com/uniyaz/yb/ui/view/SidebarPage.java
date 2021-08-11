package com.uniyaz.yb.ui.view;

import com.uniyaz.yb.QuestionPoolUI;
import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.service.CategoryService;
import com.uniyaz.yb.ui.components.QuestionContent;
import com.uniyaz.yb.ui.components.SidebarButton;
import com.uniyaz.yb.ui.fields.QuestionItemGrid;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class SidebarPage extends VerticalLayout {
    private QuestionContent questionContent;
    private CategoryService categoryService = new CategoryService();

    public SidebarPage() {
        QuestionPoolUI soruHavuzUI = (QuestionPoolUI) UI.getCurrent();
        this.questionContent = soruHavuzUI.getQuestionContent();
        fillSidebar();
    }

    public void fillSidebar() {
        this.removeAllComponents();
        Button sidebarAllButton = new Button();
        sidebarAllButton.setCaption("Hepsi");
        sidebarAllButton.setPrimaryStyleName("navButton");
        sidebarAllButton.setWidth(99, Sizeable.Unit.PERCENTAGE);
        sidebarAllButton.setHeight(50, Sizeable.Unit.PIXELS);
        this.addComponent(sidebarAllButton);
        sidebarAllButton.setCaption("Tüm Sorular");

        sidebarAllButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                QuestionItemGrid questionItemGrid = new QuestionItemGrid();
                questionContent.setQuestionContent(questionItemGrid);
            }
        });


        List<Category> categoryList = new ArrayList<Category>();
        categoryList = categoryService.listCategory();
        for (Category category : categoryList) {
            SidebarButton sidebarButton = new SidebarButton(category.getName());
            sidebarButton.setPrimaryStyleName("navButton");
            this.setMargin(true);
            this.setSpacing(true);
            sidebarButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    QuestionItemGrid questionItemGrid = new QuestionItemGrid(category);
                    questionContent.setQuestionContent(questionItemGrid);
                }
            });
            this.addComponent(sidebarButton);
        }
    }
}
