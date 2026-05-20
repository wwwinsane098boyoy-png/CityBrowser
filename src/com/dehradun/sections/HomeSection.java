// sections/HomeSection.java
package com.dehradun.sections;

import com.dehradun.components.*;
import com.dehradun.utils.StyleUtils;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class HomeSection {

    public static void load(VBox container) {
        // Hero Section
        VBox hero = new VBox(12);
        hero.setPadding(new Insets(40, 30, 40, 30));
        hero.setAlignment(Pos.CENTER_LEFT);
        hero.setStyle(
            "-fx-background-color: linear-gradient(to right, rgba(102,126,234,0.2), rgba(118,75,162,0.15));" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;" +
            "-fx-border-color: rgba(102,126,234,0.25);" +
            "-fx-border-width: 1;"
        );

        Label welcome = new Label("Welcome to");
        welcome.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 16));
        welcome.setTextFill(Color.web("#ffffff", 0.6));

        Label cityName = new Label("🏔  DEHRADUN");
        cityName.setFont(Font.font("Segoe UI", FontWeight.BLACK, 42));
        cityName.setTextFill(Color.WHITE);

        Label tagline = new Label("The Gateway to the Garhwal Himalayas  •  Capital of Uttarakhand");
        tagline.setFont(Font.font("Segoe UI", 14));
        tagline.setTextFill(Color.web("#ffffff", 0.5));

        HBox badges = new HBox(8);
        badges.setPadding(new Insets(10, 0, 0, 0));
        badges.getChildren().addAll(
            StyleUtils.badge("🌿 ECO CITY", "#6bcb77"),
            StyleUtils.badge("🎓 EDUCATION HUB", "#667eea"),
            StyleUtils.badge("⛰ HILL STATION", "#ffd93d"),
            StyleUtils.badge("🏛 HERITAGE", "#ff6b6b")
        );

        hero.getChildren().addAll(welcome, cityName, tagline, badges);

        // Stats Row
        HBox statsRow = new HBox(16);
        statsRow.getChildren().addAll(
            StyleUtils.statCard("👥", "7,14,223", "Population", "#667eea"),
            StyleUtils.statCard("🌡", "15-36°C", "Temperature Range", "#ff6b6b"),
            StyleUtils.statCard("📍", "640m", "Altitude (ASL)", "#6bcb77"),
            StyleUtils.statCard("🗣", "Hindi/Garhwali", "Languages", "#ffd93d")
        );
        HBox.setHgrow(statsRow, Priority.ALWAYS);
        for (var child : statsRow.getChildren()) {
            HBox.setHgrow(child, Priority.ALWAYS);
        }

        // Quick Overview Grid
        Label overviewTitle = new Label("🗺  Quick Overview");
        overviewTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        overviewTitle.setTextFill(Color.WHITE);
        overviewTitle.setPadding(new Insets(10, 0, 0, 0));

        FlowPane quickGrid = StyleUtils.cardGrid();
        quickGrid.getChildren().addAll(
            new AnimatedCard("📍", "Places to Visit",
                "Explore Robber's Cave, Sahastradhara, FRI, Tapkeshwar Temple & more scenic spots.",
                "#667eea", "#764ba2"),
            new AnimatedCard("🍜", "Local Cuisine",
                "Taste authentic Garhwali food, street delights, Maggi at scenic spots & famous bakeries.",
                "#f093fb", "#f5576c"),
            new AnimatedCard("🌤", "Weather & Climate",
                "Pleasant climate year-round. Best time: March-June & September-November.",
                "#4facfe", "#00f2fe"),
            new AnimatedCard("📰", "Latest News",
                "Stay updated with local development, tourism news, and city events.",
                "#43e97b", "#38f9d7"),
            new AnimatedCard("💼", "Business & Economy",
                "IT hubs, educational institutions, tourism industry & growing startup ecosystem.",
                "#fa709a", "#fee140"),
            new AnimatedCard("🚌", "Getting Around",
                "ISBT, Jolly Grant Airport, railway station. Well-connected by road and rail.",
                "#a18cd1", "#fbc2eb")
        );

        // About section
        VBox aboutBox = StyleUtils.glassCard("", 0);
        aboutBox.setMaxWidth(Double.MAX_VALUE);
        Label aboutTitle = new Label("📖  About Dehradun");
        aboutTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        aboutTitle.setTextFill(Color.WHITE);

        Label aboutText = new Label(
            "Dehradun, the capital city of Uttarakhand, is situated in the Doon Valley on the foothills " +
            "of the Himalayas. Nestled between the Song river on the east and the Asan river on the west, " +
            "the city is well known for its picturesque landscape and slightly cold and pleasant climate.\n\n" +
            "It is a gateway to popular hill stations like Mussoorie and Auli, and pilgrimage centers " +
            "like Haridwar and Rishikesh. The city houses prestigious institutions like the Indian Military " +
            "Academy (IMA), Forest Research Institute (FRI), Doon School, and the Indian Institute of " +
            "Petroleum (IIP).\n\n" +
            "With its blend of natural beauty, educational excellence, and cultural richness, Dehradun " +
            "has emerged as one of the most livable cities in India."
        );
        aboutText.setFont(Font.font("Segoe UI", 13));
        aboutText.setTextFill(Color.web("#ffffff", 0.65));
        aboutText.setWrapText(true);
        aboutText.setLineSpacing(3);
        aboutBox.getChildren().addAll(aboutTitle, aboutText);

        container.getChildren().addAll(hero, statsRow, overviewTitle, quickGrid, aboutBox);
    }
}