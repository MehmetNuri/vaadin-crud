package com.uniyaz.yb.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;

public class Header extends VerticalLayout {

    public Header() {
        setSizeFull();
        buildHeaderLayout();
    }

    private void buildHeaderLayout() {
        Label headerLabel= new Label("Soru Bankası ");
        headerLabel.setSizeUndefined();
        addComponent(headerLabel);


        this.setSpacing(true);
        headerLabel.addStyleName(ValoTheme.LABEL_H1);
        setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
    }
}
