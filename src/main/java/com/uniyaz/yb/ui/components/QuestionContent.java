package com.uniyaz.yb.ui.components;

import com.uniyaz.yb.QuestionPoolUI;
import com.uniyaz.yb.ui.fields.QuestionItemGrid;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class QuestionContent extends VerticalLayout {

    public QuestionContent() {
        QuestionPoolUI soruHavuzUI = (QuestionPoolUI) UI.getCurrent();
        soruHavuzUI.setQuestionContent(this);
        setSizeFull();
        buildContenteLayout();
    }

    private void buildContenteLayout() {
        Label lbl = new Label("Sorular");

        addComponent(lbl);

        lbl.addStyleName(ValoTheme.LABEL_H2);

        QuestionItemGrid questionItemGrid = new QuestionItemGrid();
        this.setQuestionContent(questionItemGrid);
        this.setSpacing(true);
        this.setMargin(true);


    }

    public void setQuestionContent(Component component) {
        this.removeAllComponents();
        addComponent(component);
    }
}
