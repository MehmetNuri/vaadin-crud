package com.uniyaz.yb.ui.components;


import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;

public class SidebarButton extends Button {

    public SidebarButton(String caption) {
        super();
        setCaption(caption);
        setWidth(99, Sizeable.Unit.PERCENTAGE);
        setHeight(50, Sizeable.Unit.PIXELS);
    }
}
