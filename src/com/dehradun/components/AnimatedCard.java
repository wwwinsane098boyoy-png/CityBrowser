// components/AnimatedCard.java
package com.dehradun.components;

import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class AnimatedCard extends VBox {

    public AnimatedCard(String icon, String title, String description, String gradientStart, String gradientEnd) {
        setSpacing(10);
        setPadding(new Insets(22));
        setPrefWidth(280);
        setMinHeight(180);
        setCursor(Cursor.HAND);

        setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 16;" +
            "-fx-border-radius: 16;" +
            "-fx-border-color: rgba(255,255,255,0.1);" +
            "-fx-border-width: 1;"
        );

        // Icon container
        StackPane iconContainer = new StackPane();
        iconContainer.setPrefSize(48, 48);
        iconContainer.setMaxSize(48, 48);
        iconContainer.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, " + gradientStart + ", " + gradientEnd + ");" +
            "-fx-background-radius: 12;"
        );
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Segoe UI Emoji", 22));
        iconContainer.getChildren().add(iconLabel);

        // Title
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        titleLabel.setTextFill(Color.WHITE);

        // Description
        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Segoe UI", 12));
        descLabel.setTextFill(Color.web("#ffffff", 0.6));
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(240);

        getChildren().addAll(iconContainer, titleLabel, descLabel);
        setAlignment(Pos.TOP_LEFT);

        // Hover animations
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(gradientStart, 0.0));
        shadow.setRadius(0);
        setEffect(shadow);

        setOnMouseEntered(e -> {
            Timeline tl = new Timeline(
                new KeyFrame(Duration.millis(250),
                    new KeyValue(shadow.colorProperty(), Color.web(gradientStart, 0.3)),
                    new KeyValue(shadow.radiusProperty(), 25),
                    new KeyValue(translateYProperty(), -5, Interpolator.EASE_OUT)
                )
            );
            tl.play();
            setStyle(
                "-fx-background-color: rgba(255,255,255,0.1);" +
                "-fx-background-radius: 16;" +
                "-fx-border-radius: 16;" +
                "-fx-border-color: " + gradientStart + "55;" +
                "-fx-border-width: 1;"
            );
        });

        setOnMouseExited(e -> {
            Timeline tl = new Timeline(
                new KeyFrame(Duration.millis(250),
                    new KeyValue(shadow.colorProperty(), Color.web(gradientStart, 0.0)),
                    new KeyValue(shadow.radiusProperty(), 0),
                    new KeyValue(translateYProperty(), 0, Interpolator.EASE_OUT)
                )
            );
            tl.play();
            setStyle(
                "-fx-background-color: rgba(255,255,255,0.06);" +
                "-fx-background-radius: 16;" +
                "-fx-border-radius: 16;" +
                "-fx-border-color: rgba(255,255,255,0.1);" +
                "-fx-border-width: 1;"
            );
        });
    }
}
