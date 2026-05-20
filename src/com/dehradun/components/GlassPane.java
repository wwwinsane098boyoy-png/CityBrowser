// components/GlassPane.java
package com.dehradun.components;

import javafx.geometry.Insets;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GlassPane extends VBox {

    public GlassPane() {
        this(15);
    }

    public GlassPane(double radius) {
        setStyle(
            "-fx-background-color: rgba(255, 255, 255, 0.06);" +
            "-fx-background-radius: " + radius + ";" +
            "-fx-border-radius: " + radius + ";" +
            "-fx-border-color: rgba(255, 255, 255, 0.1);" +
            "-fx-border-width: 1;"
        );
        setPadding(new Insets(20));

        // Subtle shadow
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web("#000000", 0.3));
        shadow.setRadius(20);
        shadow.setOffsetY(5);
        setEffect(shadow);
    }

    public static VBox create(double radius) {
        return new GlassPane(radius);
    }
}
