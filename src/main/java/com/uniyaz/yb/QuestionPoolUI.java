package com.uniyaz.yb;

import javax.servlet.annotation.WebServlet;

import com.uniyaz.yb.ui.components.Main;
import com.uniyaz.yb.ui.components.Sidebar;
import com.uniyaz.yb.ui.components.QuestionContent;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;


@Theme("soruhavuztheme")
@Widgetset("com.uniyaz.yb.SoruHavuzWidgetset")
public class QuestionPoolUI extends UI {

    private QuestionContent questionContent;
    private Sidebar sidebar;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Main main = new Main();
        setContent(main);

    }

    public QuestionContent getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(QuestionContent questionContent) {
        this.questionContent = questionContent;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    public void setSidebar(Sidebar sidebar) {
        this.sidebar = sidebar;
    }

    @WebServlet(urlPatterns = "/*", name = "QuestionPoolUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = QuestionPoolUI.class, productionMode = false)
    public static class QuestionPoolUIServlet extends VaadinServlet {
    }
}
