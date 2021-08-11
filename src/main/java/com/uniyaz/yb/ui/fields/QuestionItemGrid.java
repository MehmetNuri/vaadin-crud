package com.uniyaz.yb.ui.fields;

import com.uniyaz.yb.QuestionPoolUI;
import com.uniyaz.yb.core.domain.Category;
import com.uniyaz.yb.core.domain.Question;
import com.uniyaz.yb.core.service.QuestionService;
import com.uniyaz.yb.ui.components.QuestionContent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;


public class QuestionItemGrid extends GridLayout {

    private Category category;
    private double rowCount;
    private QuestionContent questionContent;
    private QuestionService questionService = new QuestionService();
    private List<Question> allQuestionsWithCategory;

    public QuestionItemGrid(Category category) {
        QuestionPoolUI questionPoolUI = (QuestionPoolUI) UI.getCurrent();
        this.questionContent = questionPoolUI.getQuestionContent();
        this.category = category;
        allQuestionsWithCategory = questionService.getAllQuestionsWithCategory(category.getId());
        rowCount = Math.ceil(Double.valueOf(allQuestionsWithCategory.size()) / 3.0);
        if (allQuestionsWithCategory.size() > 0) {
            buildGrid(rowCount);
        } else {
            Label questionEmpyt = new Label();

            questionEmpyt.setValue(category.getName() +" Katgorisine Ait Hiç Bir Sonuç Bulunamadı !");
            questionEmpyt.setPrimaryStyleName("notFoundTitle");
            addComponent(questionEmpyt);

            Notification notification = new Notification("✖", category.getName() +" Katgorisine Ait Hiç Bir Sonuç Bulunamadı !", Notification.TYPE_ERROR_MESSAGE);
            notification.setDelayMsec(2000);
            notification.show(Page.getCurrent());
        }

    }

    public QuestionItemGrid( ) {
        QuestionPoolUI questionPoolUI = (QuestionPoolUI) UI.getCurrent();
        this.questionContent = questionPoolUI.getQuestionContent();
        this.category = category;
        allQuestionsWithCategory = questionService.listQuestions();
        rowCount = Math.ceil(Double.valueOf(allQuestionsWithCategory.size()) / 3.0);
        if (allQuestionsWithCategory.size() > 0) {
            buildGrid(rowCount);
        } else {
            Label questionEmpyt = new Label();

            questionEmpyt.setValue("Hiç Soru  Bulanamadı");
            questionEmpyt.setPrimaryStyleName("notFoundTitle");
            addComponent(questionEmpyt);

            Notification notification = new Notification("✖", "Hiç Soru Bulunamadı !", Notification.TYPE_ERROR_MESSAGE);
            notification.setDelayMsec(2000);
            notification.show(Page.getCurrent());

        }

    }



    private void buildGrid(double rowCount) {
        addStyleName("outlined");
        setSizeFull();
        fillItemsGrid((int) rowCount, 3);
        setMargin(true);
        setSpacing(true);
    }

    private void fillItemsGrid(final int rows, final int columns) {

        this.setMargin(true);
        removeAllComponents();
        setRows(rows);
        setColumns(columns);
        setWidth(100.0f, Unit.PERCENTAGE);

        int count = 0;

        for (Question question : allQuestionsWithCategory) {
            VerticalLayout questionItem = new VerticalLayout();
            setRowExpandRatio(count, 32f);
            questionItem.setHeight("500");
            questionItem.setMargin(true);
            count += 1;

            Label labelQuestionTitle = new Label();
            Label labelQuestionCount = new Label();
            labelQuestionCount.setValue(String.valueOf(count));

            labelQuestionTitle.setValue(question.getTitle());
            labelQuestionTitle.addStyleName(ValoTheme.LABEL_H3);


            Label labelQuestionCategory = new Label();
            labelQuestionCategory.setValue( question.getCategory().getName());

            Label labelQuestonShort = new Label();
            labelQuestonShort.setPrimaryStyleName("shorDesc");
            if (question.getDescription().length() > 100) {
                labelQuestonShort.setValue(question.getDescription().substring(0, 100) + " ...");
            } else {
                labelQuestonShort.setContentMode(ContentMode.HTML);
                labelQuestonShort.setValue("<p>"+question.getDescription() +"<p/>" + " ...");
                this.setMargin(true);
            }

            Button showDetailButton = new Button("Devamı");
            showDetailButton.setPrimaryStyleName("questionButton");
            showDetailButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    questionItem.removeComponent(labelQuestonShort);
                    questionItem.removeComponent(showDetailButton);

                    Label labelQuestionDetail = new Label();
                    labelQuestionDetail.setContentMode(ContentMode.HTML);

                    labelQuestionDetail.setPrimaryStyleName("shorDesc");
                    labelQuestionDetail.setValue(question.getDescription() );

                    questionItem.addComponent(labelQuestionDetail);

                    questionItem.setSizeFull();
                    questionItem.setHeight(null);

                    questionContent.setQuestionContent(questionItem);
                }
            });

            showDetailButton.setIcon(FontAwesome.ARROW_CIRCLE_O_RIGHT);

            questionItem.addComponent(labelQuestionCount);
            questionItem.addComponent(labelQuestionTitle);
            questionItem.addComponent(labelQuestionCategory);
            questionItem.addComponent(labelQuestonShort);
            questionItem.addComponent(showDetailButton);
            questionItem.setPrimaryStyleName("questions");

            addComponent(questionItem);

            Page.getCurrent().setTitle("Sorular");

        }
    }
}
