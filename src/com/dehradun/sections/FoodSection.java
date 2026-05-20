// sections/FoodSection.java
package com.dehradun.sections;

import com.dehradun.utils.StyleUtils;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FoodSection {

    public static void load(VBox container) {
        container.getChildren().add(
            StyleUtils.sectionHeader("🍜", "Food & Cuisine", "Taste the authentic flavors of Dehradun & Garhwal region")
        );

        // Must-try dishes
        Label mustTry = new Label("🔥  Must-Try Dishes");
        mustTry.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        mustTry.setTextFill(Color.WHITE);
        container.getChildren().add(mustTry);

        FlowPane dishGrid = StyleUtils.cardGrid();

        dishGrid.getChildren().addAll(
            createFoodCard("🥘", "Kafuli", "Traditional Garhwali spinach curry made with spinach and fenugreek leaves, slow-cooked with minimal spices. Rich in iron and incredibly flavorful.", "GARHWALI", "#6bcb77"),
            createFoodCard("🫓", "Mandua Ki Roti", "Roti made from finger millet flour (Mandua). A staple Garhwali bread, highly nutritious, usually served with dal or chutney.", "TRADITIONAL", "#ffd93d"),
            createFoodCard("🍛", "Chainsoo", "Black gram (urad dal) roasted and slow-cooked to a thick paste. A protein-rich authentic Garhwali delicacy.", "GARHWALI", "#667eea"),
            createFoodCard("🥟", "Bal Mithai", "Iconic Kumaoni sweet made from roasted khoya coated with white sugar balls. A signature gift item from the region.", "SWEET", "#f093fb"),
            createFoodCard("🧈", "Singodi", "Sweet made from grated coconut and khoya, wrapped in maalu leaf. A traditional Kumaoni delicacy.", "SWEET", "#ff6b6b"),
            createFoodCard("🍲", "Phaanu", "A hearty lentil stew made by slow-cooking mixed dals. Comfort food of the hills, served with steamed rice.", "GARHWALI", "#4facfe"),
            createFoodCard("🌮", "Aloo Ke Gutke", "Spiced pan-fried potatoes with local herbs and a tangy twist. A popular side dish and street food item.", "STREET FOOD", "#43e97b"),
            createFoodCard("🫕", "Dubuk", "Thick curry made from soaked and ground whole grains. A traditional dish that is slowly disappearing but still found in local homes.", "RARE FIND", "#a18cd1")
        );
        container.getChildren().add(dishGrid);

        container.getChildren().add(StyleUtils.separator());

        // Street Food
        Label streetFood = new Label("🛒  Famous Street Food Spots");
        streetFood.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        streetFood.setTextFill(Color.WHITE);
        container.getChildren().add(streetFood);

        FlowPane streetGrid = StyleUtils.cardGrid();
        streetGrid.getChildren().addAll(
            createSpotCard("🏪", "Paltan Bazaar", "The heart of street food — chaat, momos, tikki, chole bhature. Must visit for foodies.", "₹20-100"),
            createSpotCard("🍕", "Rajpur Road", "Premium restaurants, cafes, bakeries. Known for continental cuisine and pizza outlets.", "₹200-800"),
            createSpotCard("☕", "Kumar's Bakery (Ellora)", "Legendary bakery since decades. Famous for pastries, bread, and baked goodies.", "₹30-200"),
            createSpotCard("🍦", "Doon Darbar / Quality", "Famous for biryani, kebabs, and Mughlai cuisine. Iconic restaurants of old Dehradun.", "₹150-500"),
            createSpotCard("🫖", "Chai at Landour", "Drive up to Landour (Mussoorie) for chai with Himalayan views at iconic chai shops.", "₹20-50"),
            createSpotCard("🍜", "Tibetan Market", "Authentic momos, thukpa, and Tibetan delicacies at Clement Town's Tibetan settlement.", "₹40-150")
        );
        container.getChildren().add(streetGrid);

        container.getChildren().add(StyleUtils.separator());

        // Popular restaurants
        VBox restBox = StyleUtils.glassCard("", 0);
        restBox.setMaxWidth(Double.MAX_VALUE);
        restBox.setSpacing(12);

        Label restTitle = new Label("⭐  Popular Restaurants & Cafes");
        restTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        restTitle.setTextFill(Color.WHITE);
        restBox.getChildren().add(restTitle);

        String[][] restaurants = {
            {"🍽", "Orchard", "Rajpur Road — Multi-cuisine, great ambience", "⭐ 4.5"},
            {"🥩", "Black Pepper", "Rajpur Road — Best steaks & continental", "⭐ 4.3"},
            {"🍕", "Town Table", "Rajpur Road — Trendy cafe, wood-fired pizza", "⭐ 4.4"},
            {"☕", "Café de Piccolo", "Rajpur Road — Italian, cozy cafe vibes", "⭐ 4.2"},
            {"🍛", "Angan", "Clock Tower — Pure veg North Indian thali", "⭐ 4.1"},
            {"🫕", "The Great Indian Dhaba", "EC Road — Authentic Punjabi food", "⭐ 4.0"},
            {"🍰", "Ellora's Melting Moments", "Rajpur Road — Iconic bakery since 1942", "⭐ 4.6"},
            {"🍜", "Momoman", "Multiple outlets — Best momos in town", "⭐ 4.3"}
        };

        for (String[] r : restaurants) {
            HBox row = createRestaurantRow(r[0], r[1], r[2], r[3]);
            restBox.getChildren().add(row);
        }

        container.getChildren().add(restBox);
    }

    private static VBox createFoodCard(String icon, String name, String desc, String tag, String color) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(18));
        card.setPrefWidth(250);
        card.setMinHeight(170);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;" +
            "-fx-border-color: rgba(255,255,255,0.08);" +
            "-fx-border-width: 1;"
        );

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);
        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(24));
        Label nameLbl = new Label(name);
        nameLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        nameLbl.setTextFill(Color.WHITE);
        header.getChildren().addAll(iconLbl, nameLbl);

        Label tagLbl = StyleUtils.badge(tag, color);

        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font("Segoe UI", 11));
        descLbl.setTextFill(Color.web("#ffffff", 0.55));
        descLbl.setWrapText(true);
        descLbl.setLineSpacing(2);

        card.getChildren().addAll(header, tagLbl, descLbl);

        card.setOnMouseEntered(e -> card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.1);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: " + color + "44;-fx-border-width: 1;"
        ));
        card.setOnMouseExited(e -> card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: rgba(255,255,255,0.08);-fx-border-width: 1;"
        ));

        return card;
    }

    private static VBox createSpotCard(String icon, String name, String desc, String price) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(16));
        card.setPrefWidth(220);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.05);" +
            "-fx-background-radius: 12;-fx-border-radius: 12;" +
            "-fx-border-color: rgba(255,255,255,0.06);-fx-border-width: 1;"
        );

        Label iconLbl = new Label(icon + " " + name);
        iconLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        iconLbl.setTextFill(Color.WHITE);

        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font("Segoe UI", 10.5));
        descLbl.setTextFill(Color.web("#ffffff", 0.5));
        descLbl.setWrapText(true);

        Label priceLbl = new Label("💰 " + price);
        priceLbl.setFont(Font.font("Segoe UI", 10));
        priceLbl.setTextFill(Color.web("#6bcb77"));

        card.getChildren().addAll(iconLbl, descLbl, priceLbl);
        return card;
    }

    private static HBox createRestaurantRow(String icon, String name, String desc, String rating) {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10, 14, 10, 14));
        row.setStyle(
            "-fx-background-color: rgba(255,255,255,0.03);" +
            "-fx-background-radius: 10;"
        );

        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(18));

        VBox textBox = new VBox(2);
        Label nameLbl = new Label(name);
        nameLbl.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
        nameLbl.setTextFill(Color.WHITE);
        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font("Segoe UI", 10.5));
        descLbl.setTextFill(Color.web("#ffffff", 0.45));
        textBox.getChildren().addAll(nameLbl, descLbl);
        HBox.setHgrow(textBox, Priority.ALWAYS);

        Label ratingLbl = new Label(rating);
        ratingLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        ratingLbl.setTextFill(Color.web("#ffd93d"));

        row.getChildren().addAll(iconLbl, textBox, ratingLbl);

        row.setOnMouseEntered(e -> row.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 10;"
        ));
        row.setOnMouseExited(e -> row.setStyle(
            "-fx-background-color: rgba(255,255,255,0.03);" +
            "-fx-background-radius: 10;"
        ));

        return row;
    }
}