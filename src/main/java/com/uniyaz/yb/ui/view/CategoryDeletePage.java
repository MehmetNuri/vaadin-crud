package com.uniyaz.yb.ui.view;

import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.service.CategoryService;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class CategoryDeletePage extends VerticalLayout {

    Category category;
    Button yes;
    Button no;
    Window myDeleteWindow;

    public CategoryDeletePage(Category category) {
        this.category = category;
        buildMainLayout();
    }

    private void buildMainLayout() {

        setWidth("98%");
        setHeight("95%");

        VerticalLayout message = new VerticalLayout();

        Label lbl = new Label("");
        lbl.setValue("<p><strong>" + category.getName() + "</strong>" + " adlı kategoriyi silmek istediğinize emin misiniz? </p> </br></br></br></br>");
        lbl.setContentMode(ContentMode.HTML);
        lbl.setHeight("30%");
        message.addComponent(lbl);
        addComponent(message);

        HorizontalLayout buttons = new HorizontalLayout();
        yes = new Button("Sil");
        yes.setIcon(FontAwesome.TRASH);
        yes.addStyleName(ValoTheme.BUTTON_DANGER);
        yes.setWidth("130px");
        buttons.addComponent(yes);

        no = new Button("Vazgeç");
        no.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        no.setWidth("130px");
        buttons.addComponent(no);

        buttons.setSpacing(true);

        addComponent(buttons);
        message.setSpacing(true);
        this.setMargin(true);
        this.setSpacing(true);

        deleteCategory(this.category);
    }

    private void deleteCategory(Category category) {
        yes.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    CategoryService categoryService = new CategoryService();
                    categoryService.deleteCategory(category);
                    Notification.show(" ✔️",category.getName() + " adlı categori silindi !", Notification.TYPE_HUMANIZED_MESSAGE);
                    UI.getCurrent().getUI().removeWindow(getDeleteWindow());
                } catch (Exception exception) {
                    Notification.show(exception.getMessage());
                }
            }
        });

        no.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getUI().removeWindow(getDeleteWindow());
            }
        });

    }

    public Window getDeleteWindow() {
        return myDeleteWindow;
    }

    public void setDeleteWindow(Window myDeleteWindow) {
        this.myDeleteWindow = myDeleteWindow;
    }
}
