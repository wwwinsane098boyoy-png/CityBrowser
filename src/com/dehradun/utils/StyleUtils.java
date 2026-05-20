// utils/StyleUtils.java
package com.dehradun.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StyleUtils {

    public static Label sectionTitle(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        label.setTextFill(Color.WHITE);
        return label;
    }

    public static Label sectionSubtitle(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 14));
        label.setTextFill(Color.web("#ffffff", 0.5));
        return label;
    }

    public static HBox sectionHeader(String icon, String title, String subtitle) {
        VBox textBox = new VBox(4);
        Label titleLabel = sectionTitle(icon + "  " + title);
        Label subtitleLabel = sectionSubtitle(subtitle);
        textBox.getChildren().addAll(titleLabel, subtitleLabel);

        HBox header = new HBox();
        header.getChildren().add(textBox);
        header.setPadding(new Insets(0, 0, 10, 0));
        return header;
    }

    public static FlowPane cardGrid() {
        FlowPane grid = new FlowPane();
        grid.setHgap(16);
        grid.setVgap(16);
        grid.setPadding(new Insets(5));
        return grid;
    }

    public static VBox glassCard(String content, double width) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(18));
        card.setPrefWidth(width);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;" +
            "-fx-border-color: rgba(255,255,255,0.08);" +
            "-fx-border-width: 1;"
        );
        return card;
    }

    public static HBox statCard(String icon, String value, String label, String color) {
        HBox card = new HBox(12);
        card.setPadding(new Insets(16));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.05);" +
            "-fx-background-radius: 12;" +
            "-fx-border-radius: 12;" +
            "-fx-border-color: " + color + "33;" +
            "-fx-border-width: 1;"
        );

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(42, 42);
        iconBox.setStyle(
            "-fx-background-color: " + color + "22;" +
            "-fx-background-radius: 10;"
        );
        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(18));
        iconBox.getChildren().add(iconLbl);

        VBox textBox = new VBox(2);
        Label valueLbl = new Label(value);
        valueLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        valueLbl.setTextFill(Color.WHITE);
        Label labelLbl = new Label(label);
        labelLbl.setFont(Font.font("Segoe UI", 11));
        labelLbl.setTextFill(Color.web("#ffffff", 0.5));
        textBox.getChildren().addAll(valueLbl, labelLbl);

        card.getChildren().addAll(iconBox, textBox);
        HBox.setHgrow(card, Priority.SOMETIMES);
        return card;
    }

    public static Label badge(String text, String color) {
        Label badge = new Label(text);
        badge.setFont(Font.font("Segoe UI", FontWeight.BOLD, 10));
        badge.setTextFill(Color.web(color));
        badge.setPadding(new Insets(4, 10, 4, 10));
        badge.setStyle(
            "-fx-background-color: " + color + "22;" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;"
        );
        return badge;
    }

    public static Region separator() {
        Region sep = new Region();
        sep.setPrefHeight(1);
        sep.setMaxWidth(Double.MAX_VALUE);
        sep.setStyle("-fx-background-color: rgba(255,255,255,0.06);");
        VBox.setMargin(sep, new Insets(5, 0, 5, 0));
        return sep;
    }
}