// sections/WeatherSection.java
package com.dehradun.sections;

import com.dehradun.utils.StyleUtils;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class WeatherSection {

    public static void load(VBox container) {
        container.getChildren().add(
            StyleUtils.sectionHeader("🌤", "Weather & Climate", "Current conditions and seasonal guide for Dehradun")
        );

        // Current Weather Card
        HBox currentWeather = new HBox(30);
        currentWeather.setPadding(new Insets(30));
        currentWeather.setAlignment(Pos.CENTER_LEFT);
        currentWeather.setStyle(
            "-fx-background-color: linear-gradient(to right, rgba(79,172,254,0.2), rgba(0,242,254,0.1));" +
            "-fx-background-radius: 20;-fx-border-radius: 20;" +
            "-fx-border-color: rgba(79,172,254,0.25);-fx-border-width: 1;"
        );

        // Temperature display
        VBox tempBox = new VBox(4);
        Label tempLabel = new Label("☀ 28°C");
        tempLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 52));
        tempLabel.setTextFill(Color.WHITE);
        // Pulse animation on temperature
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(2), tempLabel);
        pulse.setFromX(1.0); pulse.setFromY(1.0);
        pulse.setToX(1.03); pulse.setToY(1.03);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();

        Label condLabel = new Label("Partly Cloudy");
        condLabel.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 18));
        condLabel.setTextFill(Color.web("#ffffff", 0.7));
        Label locLabel = new Label("📍 Dehradun, Uttarakhand");
        locLabel.setFont(Font.font("Segoe UI", 12));
        locLabel.setTextFill(Color.web("#ffffff", 0.4));
        tempBox.getChildren().addAll(tempLabel, condLabel, locLabel);

        // Details grid
        GridPane detailGrid = new GridPane();
        detailGrid.setHgap(20);
        detailGrid.setVgap(12);

        String[][] details = {
            {"💨", "Wind", "12 km/h"},
            {"💧", "Humidity", "65%"},
            {"🌡", "Feels Like", "30°C"},
            {"👁", "Visibility", "8 km"},
            {"🔆", "UV Index", "6 (High)"},
            {"🌅", "Sunset", "6:45 PM"}
        };

        int row = 0, col = 0;
        for (String[] d : details) {
            VBox detailItem = new VBox(2);
            Label detailIcon = new Label(d[0] + " " + d[1]);
            detailIcon.setFont(Font.font("Segoe UI", 11));
            detailIcon.setTextFill(Color.web("#ffffff", 0.4));
            Label detailVal = new Label(d[2]);
            detailVal.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
            detailVal.setTextFill(Color.WHITE);
            detailItem.getChildren().addAll(detailIcon, detailVal);
            detailGrid.add(detailItem, col, row);
            col++;
            if (col > 2) { col = 0; row++; }
        }

        currentWeather.getChildren().addAll(tempBox, detailGrid);
        container.getChildren().add(currentWeather);

        // Weekly Forecast
        Label forecastTitle = new Label("📅  7-Day Forecast");
        forecastTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        forecastTitle.setTextFill(Color.WHITE);
        forecastTitle.setPadding(new Insets(10, 0, 0, 0));
        container.getChildren().add(forecastTitle);

        HBox forecastRow = new HBox(12);

        String[][] forecast = {
            {"Mon", "☀", "29°", "18°"},
            {"Tue", "⛅", "27°", "17°"},
            {"Wed", "🌤", "28°", "19°"},
            {"Thu", "🌧", "24°", "16°"},
            {"Fri", "⛈", "22°", "15°"},
            {"Sat", "🌤", "26°", "17°"},
            {"Sun", "☀", "30°", "19°"}
        };

        for (String[] f : forecast) {
            VBox dayCard = new VBox(8);
            dayCard.setAlignment(Pos.CENTER);
            dayCard.setPadding(new Insets(16, 20, 16, 20));
            dayCard.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05);" +
                "-fx-background-radius: 14;-fx-border-radius: 14;" +
                "-fx-border-color: rgba(255,255,255,0.06);-fx-border-width: 1;"
            );
            HBox.setHgrow(dayCard, Priority.ALWAYS);

            Label day = new Label(f[0]);
            day.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
            day.setTextFill(Color.web("#ffffff", 0.6));

            Label icon = new Label(f[1]);
            icon.setFont(Font.font(28));

            Label high = new Label(f[2]);
            high.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
            high.setTextFill(Color.WHITE);

            Label low = new Label(f[3]);
            low.setFont(Font.font("Segoe UI", 12));
            low.setTextFill(Color.web("#ffffff", 0.4));

            dayCard.getChildren().addAll(day, icon, high, low);
            forecastRow.getChildren().add(dayCard);
        }

        container.getChildren().add(forecastRow);

        // Seasonal Guide
        Label seasonTitle = new Label("🗓  Seasonal Guide");
        seasonTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        seasonTitle.setTextFill(Color.WHITE);
        seasonTitle.setPadding(new Insets(10, 0, 0, 0));
        container.getChildren().add(seasonTitle);

        FlowPane seasonGrid = StyleUtils.cardGrid();
        seasonGrid.getChildren().addAll(
            createSeasonCard("🌸", "Spring (Mar-Apr)", "20-30°C",
                "Pleasant weather, blooming flowers. Ideal for sightseeing. Light layers recommended.",
                "#6bcb77", "BEST TIME"),
            createSeasonCard("☀", "Summer (May-Jun)", "25-36°C",
                "Warm days, cool evenings. Peak tourist season. Great time for Mussoorie trip.",
                "#ffd93d", "POPULAR"),
            createSeasonCard("🌧", "Monsoon (Jul-Sep)", "20-30°C",
                "Heavy rainfall, lush greenery. Waterfalls at their best. Carry rain gear. Landslide alerts.",
                "#4facfe", "ADVENTUROUS"),
            createSeasonCard("🍂", "Autumn (Oct-Nov)", "15-28°C",
                "Clear skies, crisp air. Best for mountain views and photography. Very pleasant.",
                "#f093fb", "BEST TIME"),
            createSeasonCard("❄", "Winter (Dec-Feb)", "3-18°C",
                "Cold but sunny days. Snowfall in nearby hills. Carry warm woolens. Fog possible.",
                "#a18cd1", "COLD")
        );

        container.getChildren().add(seasonGrid);
    }

    private static VBox createSeasonCard(String icon, String season, String temp, String desc, String color, String badge) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(18));
        card.setPrefWidth(210);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: rgba(255,255,255,0.08);-fx-border-width: 1;"
        );

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);
        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(22));
        Label seasonLbl = new Label(season);
        seasonLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        seasonLbl.setTextFill(Color.WHITE);
        header.getChildren().addAll(iconLbl, seasonLbl);

        HBox tags = new HBox(6);
        Label tempLbl = StyleUtils.badge(temp, "#4facfe");
        Label badgeLbl = StyleUtils.badge(badge, color);
        tags.getChildren().addAll(tempLbl, badgeLbl);

        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font("Segoe UI", 11));
        descLbl.setTextFill(Color.web("#ffffff", 0.5));
        descLbl.setWrapText(true);
        descLbl.setLineSpacing(2);

        card.getChildren().addAll(header, tags, descLbl);
        return card;
    }
}