// sections/PlacesSection.java
package com.dehradun.sections;

import com.dehradun.components.*;
import com.dehradun.utils.StyleUtils;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlacesSection {

    public static void load(VBox container) {
        container.getChildren().add(
            StyleUtils.sectionHeader("📍", "Places to Visit", "Discover the best tourist attractions in Dehradun")
        );

        // Categories
        HBox categories = new HBox(8);
        categories.getChildren().addAll(
            StyleUtils.badge("ALL", "#667eea"),
            StyleUtils.badge("NATURE", "#6bcb77"),
            StyleUtils.badge("TEMPLES", "#ffd93d"),
            StyleUtils.badge("HERITAGE", "#ff6b6b"),
            StyleUtils.badge("ADVENTURE", "#4facfe")
        );
        container.getChildren().add(categories);

        FlowPane grid = StyleUtils.cardGrid();

        // Place cards
        grid.getChildren().addAll(
            createPlaceCard("🏞", "Robber's Cave (Guchhupani)",
                "A natural cave formation with a river stream running through it. The water disappears underground and reappears downstream. A must-visit for nature lovers.",
                "📍 8 km from city center", "⏰ 7 AM - 6 PM", "💰 ₹25 entry",
                "#667eea", "#764ba2"),

            createPlaceCard("💧", "Sahastradhara",
                "Literally meaning 'Thousand-fold spring', famous for its sulphur water springs believed to have medicinal properties. Surrounded by limestone stalactites.",
                "📍 14 km from city", "⏰ 8 AM - 7 PM", "💰 Free entry",
                "#4facfe", "#00f2fe"),

            createPlaceCard("🏛", "Forest Research Institute (FRI)",
                "A premier institute housed in a magnificent colonial-era building spread over 450 hectares. The Greco-Roman architecture is breathtaking. Houses 6 museums.",
                "📍 City center", "⏰ 9:30 AM - 5 PM", "💰 ₹40 entry",
                "#43e97b", "#38f9d7"),

            createPlaceCard("🕉", "Tapkeshwar Temple",
                "An ancient cave temple dedicated to Lord Shiva. Natural water drops continuously fall on the Shiva Lingam from the cave ceiling. Serene atmosphere.",
                "📍 5 km from city", "⏰ 6 AM - 7 PM", "💰 Free",
                "#ffd93d", "#ff9a00"),

            createPlaceCard("⛰", "Mussoorie (Queen of Hills)",
                "Just 35 km from Dehradun, this charming hill station offers Mall Road shopping, Gun Hill views, Kempty Falls, and stunning Himalayan panoramas.",
                "📍 35 km from Dehradun", "⏰ Year-round", "💰 Varies",
                "#f093fb", "#f5576c"),

            createPlaceCard("🦁", "Rajaji National Park",
                "A wildlife sanctuary spread over 820 sq km, home to elephants, tigers, leopards, and diverse bird species. Safari tours available.",
                "📍 20 km from city", "⏰ 6 AM - 6 PM", "💰 ₹150-600",
                "#a18cd1", "#fbc2eb"),

            createPlaceCard("🌊", "Lacchiwala",
                "A nature spot with a man-made pool surrounded by dense Sal forest. Perfect for family picnics and nature walks. Crystal clear water.",
                "📍 22 km from city", "⏰ 8 AM - 5 PM", "💰 ₹30",
                "#6bcb77", "#38f9d7"),

            createPlaceCard("🎡", "Mindrolling Monastery",
                "One of the largest Buddhist centers in India. Features a stunning 220 ft Great Stupa, beautiful Japanese garden, and intricate wall paintings.",
                "📍 Clement Town", "⏰ 9 AM - 7 PM", "💰 Free",
                "#ff6b6b", "#ffd93d"),

            createPlaceCard("🏔", "Malsi Deer Park",
                "A mini zoo and park at the foothills, home to deer, peacocks, and other animals. Great for families with kids. Beautiful garden area.",
                "📍 10 km from city", "⏰ 10 AM - 5 PM", "💰 ₹20",
                "#4facfe", "#667eea"),

            createPlaceCard("🌅", "Tiger Falls",
                "The highest direct waterfall in Uttarakhand at 312 feet. Located near Chakrata, requires a scenic trek through lush forests.",
                "📍 98 km (Chakrata)", "⏰ Best: Apr-Nov", "💰 ₹50",
                "#43e97b", "#4facfe"),

            createPlaceCard("📚", "Wadia Institute",
                "Wadia Institute of Himalayan Geology houses a fascinating museum with rock samples, fossils, and geological specimens from the Himalayas.",
                "📍 City center", "⏰ 10 AM - 5 PM", "💰 Free",
                "#ffd93d", "#f093fb"),

            createPlaceCard("⛪", "Clock Tower",
                "The iconic hexagonal clock tower in the heart of Dehradun. A landmark since British era, surrounded by the bustling Paltan Bazaar market.",
                "📍 Paltan Bazaar", "⏰ Always", "💰 Free",
                "#ff6b6b", "#764ba2")
        );

        container.getChildren().add(grid);

        // Pro tip
        VBox tipBox = StyleUtils.glassCard("", 0);
        tipBox.setMaxWidth(Double.MAX_VALUE);
        Label tipTitle = new Label("💡  Pro Tips for Visitors");
        tipTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        tipTitle.setTextFill(Color.web("#ffd93d"));

        Label tipText = new Label(
            "• Best time to visit: March to June for pleasant weather, September to November for clear mountain views\n" +
            "• Carry comfortable walking shoes for cave and waterfall visits\n" +
            "• Start early to cover multiple spots in a day\n" +
            "• Weekends are crowded at popular spots — prefer weekdays\n" +
            "• Don't miss the sunset view from Mussoorie's Gun Hill"
        );
        tipText.setFont(Font.font("Segoe UI", 12));
        tipText.setTextFill(Color.web("#ffffff", 0.6));
        tipText.setWrapText(true);
        tipText.setLineSpacing(4);
        tipBox.getChildren().addAll(tipTitle, tipText);

        container.getChildren().add(tipBox);
    }

    private static VBox createPlaceCard(String icon, String name, String desc,
                                         String location, String timing, String price,
                                         String colorStart, String colorEnd) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setPrefWidth(320);
        card.setMinHeight(220);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 16;" +
            "-fx-border-radius: 16;" +
            "-fx-border-color: rgba(255,255,255,0.08);" +
            "-fx-border-width: 1;" +
            "-fx-cursor: hand;"
        );

        // Header
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(44, 44);
        iconBox.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, " + colorStart + ", " + colorEnd + ");" +
            "-fx-background-radius: 12;"
        );
        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(20));
        iconBox.getChildren().add(iconLbl);

        Label nameLbl = new Label(name);
        nameLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        nameLbl.setTextFill(Color.WHITE);
        nameLbl.setWrapText(true);
        nameLbl.setMaxWidth(230);

        header.getChildren().addAll(iconBox, nameLbl);

        // Description
        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font("Segoe UI", 11.5));
        descLbl.setTextFill(Color.web("#ffffff", 0.55));
        descLbl.setWrapText(true);
        descLbl.setLineSpacing(2);

        // Info chips
        HBox info = new HBox(6);
        info.setAlignment(Pos.CENTER_LEFT);
        info.setPadding(new Insets(5, 0, 0, 0));

        Label locLbl = createChip(location);
        Label timeLbl = createChip(timing);
        Label priceLbl = createChip(price);
        info.getChildren().addAll(locLbl, timeLbl, priceLbl);

        card.getChildren().addAll(header, descLbl, info);

        // Hover effect
        card.setOnMouseEntered(e -> card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.1);" +
            "-fx-background-radius: 16;" +
            "-fx-border-radius: 16;" +
            "-fx-border-color: " + colorStart + "44;" +
            "-fx-border-width: 1;" +
            "-fx-cursor: hand;"
        ));
        card.setOnMouseExited(e -> card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 16;" +
            "-fx-border-radius: 16;" +
            "-fx-border-color: rgba(255,255,255,0.08);" +
            "-fx-border-width: 1;" +
            "-fx-cursor: hand;"
        ));

        return card;
    }

    private static Label createChip(String text) {
        Label chip = new Label(text);
        chip.setFont(Font.font("Segoe UI", 9.5));
        chip.setTextFill(Color.web("#ffffff", 0.5));
        chip.setPadding(new Insets(3, 8, 3, 8));
        chip.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;"
        );
        return chip;
    }
}