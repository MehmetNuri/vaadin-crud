package com.uniyaz.yb.ui.view;

import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.domain.Question;
import com.uniyaz.yb.core.service.QuestionService;
import com.uniyaz.yb.ui.fields.CategoryComboBoxField;
import com.vaadin.data.util.filter.Not;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class QuestionAddPage extends VerticalLayout {

    private Question question = new Question();
    CategoryComboBoxField categoryComboBoxField;
    TextField questionTitle;
    RichTextArea detail;
    Button addQuestionButton;

    public QuestionAddPage() {
        buildQuestionAndView();
    }

    private void buildQuestionAndView() {
        FormLayout questionFormLayout = new FormLayout();

        questionTitle = new TextField();
        questionTitle.setCaption("Soru Başlığı");
        questionTitle.setRequired(true);
        questionFormLayout.addComponent(questionTitle);

        categoryComboBoxField = new CategoryComboBoxField();
        categoryComboBoxField.setCaption("Kategori");
        questionFormLayout.addComponent(categoryComboBoxField);

        detail = new RichTextArea();
        detail.setCaption("Soru İçeriği");
        detail.setSizeFull();
        detail.setHeight("410px");
        questionFormLayout.addComponent(detail);

        addQuestionButton = new Button();
        addQuestionButton.setCaption("Kaydet");
        addQuestionButton.setIcon(FontAwesome.SAVE);
        addQuestionButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        questionFormLayout.addComponent(addQuestionButton);
        addComponent(questionFormLayout);
        addQuestionButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        addQuestionButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                String category = (String) categoryComboBoxField.getValue();
                question.setTitle(questionTitle.getValue());

                question.setDescription(detail.getValue());
                QuestionService questionService = new QuestionService();
                try {

                    if (question.getTitle() == null || question.getTitle().equals("")) {

                        Notification notification = new Notification("✖", "Lütfen soru başlığını giriniz", Notification.TYPE_ERROR_MESSAGE);
                        notification.setDelayMsec(2000);
                        notification.show(Page.getCurrent());


                    } else if ((String) categoryComboBoxField.getValue() == null) {

                        Notification notification = new Notification("✖", "Lütfen soru kategorisi seçiniz", Notification.TYPE_ERROR_MESSAGE);
                        notification.setDelayMsec(2000);
                        notification.show(Page.getCurrent());
                    } else {
                        questionService.addQuestion((Question) question, (String) categoryComboBoxField.getValue());
                        Notification notification = new Notification(" ✔️", question.getTitle() + " adlı soru eklendi  ! ", Notification.TYPE_HUMANIZED_MESSAGE);
                        notification.setDelayMsec(2000);
                        notification.show(Page.getCurrent());

                        clearForm();
                    }
                } catch (Exception e) {
                    Notification.show("Hata!", "SORU EKLENEMEDİ", Notification.TYPE_ERROR_MESSAGE);
                }
            }
        });

    }

    private void clearForm() {

        questionTitle.clear();
        categoryComboBoxField.clear();
        detail.clear();
    }
}
