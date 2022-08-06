package com.hrndzl.musikverwaltung.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class HBoxWithLabelAndTextField {
    private HBox hBox = new HBox();
    private Label label = new Label();
    private TextField textField = new TextField();

    public HBoxWithLabelAndTextField() {}

    public HBoxWithLabelAndTextField(String labelText) {
        label.setText(labelText);
        this.hBox.getChildren().addAll(label, textField);
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

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public String getText() {
        return this.textField.getText();
    }
}
