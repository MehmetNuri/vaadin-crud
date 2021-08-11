package com.uniyaz.yb.ui.components;

import com.uniyaz.yb.QuestionPoolUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class Body extends HorizontalLayout  {
    private QuestionContent questionContent;
    private Sidebar sidebar;

    public Body() {
        setSizeFull();
        buildBodyLayout();
    }

    private void buildBodyLayout() {

        questionContent = new QuestionContent();

        sidebar = new Sidebar();
        ((QuestionPoolUI) QuestionPoolUI.getCurrent()).setSidebar(sidebar);
        addComponent(sidebar);
        addComponent(questionContent);

        setExpandRatio(sidebar, 1.8f);
        setExpandRatio(questionContent, 8f);
    }
}
