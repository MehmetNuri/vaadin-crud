package com.uniyaz.yb.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class Main extends VerticalLayout {
    public Main() {
        setSizeFull();
        setHeight(null);
        buildMainLayout();
    }

    private void buildMainLayout() {
        Header header = new Header();
        addComponent(header);

        Body body = new Body();

        TopMenu topMenu = new TopMenu();
        addComponent(topMenu);

        createSearchBar(body);

        Footer footer = new Footer();
        addComponent(footer);

        setExpandRatio(header, 1f);
        setExpandRatio(topMenu, 1f);
        setExpandRatio(body, 7.4f);
        setExpandRatio(footer, 0.4f);
    }

    private void createSearchBar(Body body) {
        HorizontalLayout searcLayout = new HorizontalLayout();
        TextField searchBar = new TextField();
        searchBar.setWidth("600px");
        searchBar.setInputPrompt("Lütfen araman istediğiniz kelimeyi yazınız...");
        searcLayout.addComponent(searchBar);
        searcLayout.setMargin(true);
        searcLayout.setSpacing(true);

        Button button = new Button();
        button.setIcon(FontAwesome.SEARCH);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        searcLayout.addComponent(button);
        addComponent(searcLayout);
        searcLayout.setSizeUndefined();
        this.setComponentAlignment(searcLayout, Alignment.TOP_CENTER);
        addComponent(body);
    }
}
