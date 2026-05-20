// sections/NewsSection.java
package com.dehradun.sections;

import com.dehradun.utils.StyleUtils;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NewsSection {

    public static void load(VBox container) {
        container.getChildren().add(
            StyleUtils.sectionHeader("📰", "Local News & Updates", "Stay informed about Dehradun's latest happenings")
        );

        // Breaking news banner
        HBox breaking = new HBox(10);
        breaking.setPadding(new Insets(14, 20, 14, 20));
        breaking.setAlignment(Pos.CENTER_LEFT);
        breaking.setStyle(
            "-fx-background-color: rgba(255,107,107,0.15);" +
            "-fx-background-radius: 12;-fx-border-radius: 12;" +
            "-fx-border-color: rgba(255,107,107,0.3);-fx-border-width: 1;"
        );
        Label breakingIcon = StyleUtils.badge("🔴 BREAKING", "#ff6b6b");
        Label breakingText = new Label("  Smart City project phase-2 inaugurated: ₹500 Cr allocated for infrastructure development");
        breakingText.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        breakingText.setTextFill(Color.WHITE);
        breaking.getChildren().addAll(breakingIcon, breakingText);
        container.getChildren().add(breaking);

        // News categories
        HBox cats = new HBox(8);
        cats.getChildren().addAll(
            StyleUtils.badge("🔥 TRENDING", "#ff6b6b"),
            StyleUtils.badge("🏗 DEVELOPMENT", "#667eea"),
            StyleUtils.badge("🎭 CULTURE", "#f093fb"),
            StyleUtils.badge("🏫 EDUCATION", "#ffd93d"),
            StyleUtils.badge("🏥 HEALTH", "#6bcb77")
        );
        container.getChildren().add(cats);

        // News items
        VBox newsContainer = new VBox(12);

        String[][] newsItems = {
            {"🏗", "DEVELOPMENT", "Dehradun Metro Rail Project Gets Green Signal",
                "The Uttarakhand government has approved the Dehradun Metro Rail project covering 28.8 km with 27 stations. Phase-1 will connect ISBT to Survey Chowk.",
                "2 hours ago", "#667eea"},
            {"✈", "TRANSPORT", "Jolly Grant Airport Expansion Plan Announced",
                "International terminal expansion approved. New runway and terminal building to handle 5 million passengers annually by 2027.",
                "5 hours ago", "#4facfe"},
            {"🎓", "EDUCATION", "IIT Roorkee to Open Dehradun Extension Campus",
                "A satellite campus of IIT Roorkee to be established in Dehradun focusing on AI and Data Science programs. Operations to begin in 2025.",
                "8 hours ago", "#ffd93d"},
            {"🌿", "ENVIRONMENT", "Doon Valley Air Quality Improves by 30%",
                "Recent green initiatives including the ban on commercial vehicles in city center have significantly improved air quality index.",
                "1 day ago", "#6bcb77"},
            {"🏛", "TOURISM", "Mussoorie Ropeway Reopens After Renovation",
                "The iconic Gun Hill ropeway in Mussoorie has reopened after a ₹15 Cr renovation with modern cabins and safety features.",
                "1 day ago", "#f093fb"},
            {"🏥", "HEALTH", "AIIMS Rishikesh Opens Satellite OPD in Dehradun",
                "AIIMS Rishikesh has inaugurated an outpatient department center in Dehradun to serve patients without the need to travel.",
                "2 days ago", "#ff6b6b"},
            {"💻", "TECHNOLOGY", "IT Park Phase-2 to Create 10,000 Jobs",
                "The new IT Park at Sahastradhara Road expansion will house major tech companies and is expected to create 10,000 new employment opportunities.",
                "2 days ago", "#a18cd1"},
            {"🎭", "CULTURE", "Annual Jhanda Mela Preparations Begin",
                "Preparations for the annual Jhanda Mela (Flag Festival) at Guru Ram Rai Darbar Sahib have begun. Expected to draw 100,000+ visitors.",
                "3 days ago", "#43e97b"}
        };

        for (String[] news : newsItems) {
            newsContainer.getChildren().add(createNewsCard(
                news[0], news[1], news[2], news[3], news[4], news[5]
            ));
        }

        container.getChildren().add(newsContainer);

        // Local Events
        Label eventsTitle = new Label("🎉  Upcoming Events");
        eventsTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        eventsTitle.setTextFill(Color.WHITE);
        eventsTitle.setPadding(new Insets(10, 0, 0, 0));
        container.getChildren().add(eventsTitle);

        FlowPane eventGrid = StyleUtils.cardGrid();
        eventGrid.getChildren().addAll(
            createEventCard("🏳", "Jhanda Mela", "Mar 25-29", "Guru Ram Rai Darbar", "#ffd93d"),
            createEventCard("🎵", "Dehradun Music Fest", "Apr 12-14", "Parade Ground", "#f093fb"),
            createEventCard("📚", "Doon Book Fair", "Apr 20-25", "Clock Tower", "#667eea"),
            createEventCard("🏃", "Doon Marathon", "May 5", "Rajpur Road", "#6bcb77"),
            createEventCard("🎨", "Art Walk Dehradun", "May 15", "Various Galleries", "#ff6b6b"),
            createEventCard("🍜", "Food Festival", "Jun 1-3", "Pacific Mall", "#4facfe")
        );
        container.getChildren().add(eventGrid);
    }

    private static VBox createNewsCard(String icon, String category, String title, String desc, String time, String color) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(18, 22, 18, 22));
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.05);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: rgba(255,255,255,0.06);-fx-border-width: 1;" +
            "-fx-cursor: hand;"
        );

        HBox topRow = new HBox(8);
        topRow.setAlignment(Pos.CENTER_LEFT);
        Label catBadge = StyleUtils.badge(category, color);
        Label timeLbl = new Label("•  " + time);
        timeLbl.setFont(Font.font("Segoe UI", 10));
        timeLbl.setTextFill(Color.web("#ffffff", 0.3));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topRow.getChildren().addAll(catBadge, timeLbl, spacer);

        Label titleLbl = new Label(icon + "  " + title);
        titleLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        titleLbl.setTextFill(Color.WHITE);

        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font("Segoe UI", 12));
        descLbl.setTextFill(Color.web("#ffffff", 0.5));
        descLbl.setWrapText(true);
        descLbl.setLineSpacing(2);

        card.getChildren().addAll(topRow, titleLbl, descLbl);

        card.setOnMouseEntered(e -> card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.08);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: " + color + "33;-fx-border-width: 1;-fx-cursor: hand;"
        ));
        card.setOnMouseExited(e -> card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.05);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: rgba(255,255,255,0.06);-fx-border-width: 1;-fx-cursor: hand;"
        ));

        return card;
    }

    private static VBox createEventCard(String icon, String name, String date, String venue, String color) {
        VBox card = new VBox(6);
        card.setPadding(new Insets(16));
        card.setPrefWidth(180);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.05);" +
            "-fx-background-radius: 12;-fx-border-radius: 12;" +
            "-fx-border-color: " + color + "22;-fx-border-width: 1;"
        );

        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(28));

        Label nameLbl = new Label(name);
        nameLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        nameLbl.setTextFill(Color.WHITE);

        Label dateLbl = new Label("📅 " + date);
        dateLbl.setFont(Font.font("Segoe UI", 10));
        dateLbl.setTextFill(Color.web(color));

        Label venueLbl = new Label("📍 " + venue);
        venueLbl.setFont(Font.font("Segoe UI", 10));
        venueLbl.setTextFill(Color.web("#ffffff", 0.4));

        card.getChildren().addAll(iconLbl, nameLbl, dateLbl, venueLbl);
        return card;
    }
}