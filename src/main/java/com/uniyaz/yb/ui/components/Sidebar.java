package com.uniyaz.yb.ui.components;

import com.uniyaz.yb.QuestionPoolUI;
import com.uniyaz.yb.ui.view.SidebarPage;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Sidebar extends VerticalLayout {

    private SidebarPage sidebarPage;

    public Sidebar() {
        setSizeFull();
        QuestionPoolUI soruHavuzUI = (QuestionPoolUI) UI.getCurrent();
        soruHavuzUI.setSidebar(this);
        Label label = new Label();
        label.setValue("Kategoriler");
        label.addStyleName(ValoTheme.LABEL_H3);
        label.addStyleName(ValoTheme.LABEL_BOLD);
        addComponent(label);
        sidebarPage = new SidebarPage();
        addComponent(sidebarPage);
        this.setMargin(true);

        this.addStyleName(ValoTheme.SPLITPANEL_LARGE);
    }

    public SidebarPage getSidebarPage() {
        return sidebarPage;
    }


}
