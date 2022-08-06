package com.hrndzl.musikverwaltung.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class HBoxWithLabelAndTextArea {
    private HBox hBox = new HBox();
    private Label label = new Label();
    private TextArea textArea = new TextArea();

    public HBoxWithLabelAndTextArea() {}

    public HBoxWithLabelAndTextArea(String labelText) {
        label.setText(labelText);
        this.hBox.getChildren().addAll(label, textArea);
    }

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public String getText() {
        return this.textArea.getText();
    }
}
