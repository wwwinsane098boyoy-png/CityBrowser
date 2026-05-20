// sections/EssentialsSection.java
package com.dehradun.sections;

import com.dehradun.utils.StyleUtils;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EssentialsSection {

    public static void load(VBox container) {
        container.getChildren().add(
            StyleUtils.sectionHeader("📋", "Travel Essentials", "Everything you need to know before visiting Dehradun")
        );

        // Getting There
        Label howToReach = new Label("🚗  How to Reach Dehradun");
        howToReach.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        howToReach.setTextFill(Color.WHITE);
        container.getChildren().add(howToReach);

        FlowPane transportGrid = StyleUtils.cardGrid();
        transportGrid.getChildren().addAll(
            createTransportCard("✈", "By Air",
                "Jolly Grant Airport (DED)\n• 25 km from city center\n• Flights from Delhi, Mumbai, Bangalore\n• Airlines: IndiGo, SpiceJet, Air India\n• Taxi to city: ₹500-800",
                "#4facfe"),
            createTransportCard("🚂", "By Train",
                "Dehradun Railway Station (DDN)\n• City center location\n• Shatabdi from Delhi (5.5 hrs)\n• Nanda Devi Express, Mussoorie Express\n• Well connected to major cities",
                "#6bcb77"),
            createTransportCard("🚌", "By Bus",
                "ISBT Dehradun\n• Volvo/AC buses from Delhi (6-7 hrs)\n• UPSRTC & UKRTC services\n• Private operators available\n• Fare: ₹400-1200 from Delhi",
                "#ffd93d"),
            createTransportCard("🚗", "By Road",
                "Well connected by NH\n• Delhi: 250 km (5-6 hrs)\n• Haridwar: 52 km (1.5 hrs)\n• Mussoorie: 35 km (1 hr)\n• Rishikesh: 43 km (1 hr)",
                "#f093fb")
        );
        container.getChildren().add(transportGrid);

        container.getChildren().add(StyleUtils.separator());

        // Local Transport
        Label localTitle = new Label("🚌  Getting Around the City");
        localTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        localTitle.setTextFill(Color.WHITE);
        container.getChildren().add(localTitle);

        VBox localBox = StyleUtils.glassCard("", 0);
        localBox.setMaxWidth(Double.MAX_VALUE);
        localBox.setSpacing(10);

        String[][] localTransport = {
            {"🛺", "Auto Rickshaw", "Most common mode. Shared & private available. Negotiate fare beforehand.", "₹20-150"},
            {"🚖", "Ola / Uber", "App-based cabs available throughout the city. Reliable and metered.", "₹100-500"},
            {"🚌", "City Bus", "Uttarakhand Roadways local buses. Limited routes but very affordable.", "₹10-30"},
            {"🏍", "Rental Bikes/Scooters", "Available on Rajpur Road. Great for exploring nearby areas. ID required.", "₹300-800/day"},
            {"🚲", "Cycling", "Growing cycling culture. Some rental services available. Best for short distances.", "₹100-200/day"},
            {"🚗", "Self-Drive Cars", "Zoomcar and local rental agencies available. Useful for outstation trips.", "₹1000-3000/day"}
        };

        for (String[] lt : localTransport) {
            HBox row = new HBox(12);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(10, 14, 10, 14));
            row.setStyle("-fx-background-color: rgba(255,255,255,0.03);-fx-background-radius: 10;");

            Label icon = new Label(lt[0]);
            icon.setFont(Font.font(20));

            VBox info = new VBox(2);
            Label name = new Label(lt[1]);
            name.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
            name.setTextFill(Color.WHITE);
            Label desc = new Label(lt[2]);
            desc.setFont(Font.font("Segoe UI", 10.5));
            desc.setTextFill(Color.web("#ffffff", 0.45));
            desc.setWrapText(true);
            info.getChildren().addAll(name, desc);
            HBox.setHgrow(info, Priority.ALWAYS);

            Label price = new Label(lt[3]);
            price.setFont(Font.font("Segoe UI", FontWeight.BOLD, 11));
            price.setTextFill(Color.web("#6bcb77"));

            row.getChildren().addAll(icon, info, price);
            localBox.getChildren().add(row);
        }
        container.getChildren().add(localBox);

        container.getChildren().add(StyleUtils.separator());

        // Emergency contacts
        Label emergencyTitle = new Label("🆘  Emergency Contacts");
        emergencyTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        emergencyTitle.setTextFill(Color.WHITE);
        container.getChildren().add(emergencyTitle);

        FlowPane emergencyGrid = StyleUtils.cardGrid();
        emergencyGrid.getChildren().addAll(
            createEmergencyCard("🚔", "Police", "100 / 112", "#ff6b6b"),
            createEmergencyCard("🚑", "Ambulance", "108 / 102", "#6bcb77"),
            createEmergencyCard("🚒", "Fire Brigade", "101", "#ffd93d"),
            createEmergencyCard("🏥", "Doon Hospital", "0135-2721800", "#4facfe"),
            createEmergencyCard("🆘", "Disaster Helpline", "1070", "#f093fb"),
            createEmergencyCard("👮", "Tourist Police", "1363", "#a18cd1")
        );
        container.getChildren().add(emergencyGrid);

        container.getChildren().add(StyleUtils.separator());

        // Packing & Tips
        VBox tipsBox = StyleUtils.glassCard("", 0);
        tipsBox.setMaxWidth(Double.MAX_VALUE);
        tipsBox.setSpacing(10);

        Label tipsTitle = new Label("🎒  Packing Checklist & Travel Tips");
        tipsTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        tipsTitle.setTextFill(Color.WHITE);

        Label tipsText = new Label(
            "👕  CLOTHING\n" +
            "• Summer: Light cotton clothes, sunglasses, sunscreen, hat\n" +
            "• Winter: Heavy woolens, jacket, thermals, gloves (it gets cold!)\n" +
            "• Monsoon: Waterproof jacket, umbrella, quick-dry clothes\n" +
            "• Always carry a light jacket — evenings can be cool year-round\n\n" +
            "👟  FOOTWEAR\n" +
            "• Comfortable walking shoes (lots of walking at tourist spots)\n" +
            "• Waterproof shoes/sandals for monsoon and waterfall visits\n" +
            "• Trekking shoes if visiting Tiger Falls or Rajaji NP\n\n" +
            "📱  ESSENTIALS\n" +
            "• Power bank (long day trips)\n" +
            "• Cash (not all places accept UPI/cards in rural areas)\n" +
            "• Valid ID proof (required at many attractions and hotels)\n" +
            "• First aid kit with basic medicines\n" +
            "• Insect repellent (especially for forest areas)\n\n" +
            "💡  PRO TIPS\n" +
            "• Hindi is widely spoken; English understood in tourist areas\n" +
            "• Bargain at local markets — prices can be inflated for tourists\n" +
            "• Drink bottled water only\n" +
            "• Respect local customs at temples and religious sites\n" +
            "• Carry your own water bottle — refill stations available\n" +
            "• Book Mussoorie hotels in advance during peak season"
        );
        tipsText.setFont(Font.font("Segoe UI", 12));
        tipsText.setTextFill(Color.web("#ffffff", 0.6));
        tipsText.setWrapText(true);
        tipsText.setLineSpacing(3);
        tipsBox.getChildren().addAll(tipsTitle, tipsText);
        container.getChildren().add(tipsBox);

        // Useful Apps
        Label appsTitle = new Label("📱  Useful Apps for Dehradun");
        appsTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        appsTitle.setTextFill(Color.WHITE);
        appsTitle.setPadding(new Insets(10, 0, 0, 0));
        container.getChildren().add(appsTitle);

        FlowPane appsGrid = StyleUtils.cardGrid();
        appsGrid.getChildren().addAll(
            createAppCard("🗺", "Google Maps", "Navigation & local discovery"),
            createAppCard("🚖", "Ola / Uber", "Cab booking"),
            createAppCard("🏨", "MakeMyTrip", "Hotel & flight booking"),
            createAppCard("🍽", "Zomato / Swiggy", "Food delivery & restaurant discovery"),
            createAppCard("🌦", "Mausam (IMD)", "Weather updates"),
            createAppCard("🚂", "IRCTC", "Train ticket booking")
        );
        container.getChildren().add(appsGrid);
    }

    private static VBox createTransportCard(String icon, String mode, String details, String color) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setPrefWidth(250);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: " + color + "22;-fx-border-width: 1;"
        );

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);
        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(24));
        Label modeLbl = new Label(mode);
        modeLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        modeLbl.setTextFill(Color.WHITE);
        header.getChildren().addAll(iconLbl, modeLbl);

        Label detailsLbl = new Label(details);
        detailsLbl.setFont(Font.font("Segoe UI", 11));
        detailsLbl.setTextFill(Color.web("#ffffff", 0.55));
        detailsLbl.setWrapText(true);
        detailsLbl.setLineSpacing(3);

        card.getChildren().addAll(header, detailsLbl);
        return card;
    }

    private static VBox createEmergencyCard(String icon, String service, String number, String color) {
        VBox card = new VBox(6);
        card.setPadding(new Insets(16));
        card.setPrefWidth(170);
        card.setAlignment(Pos.CENTER);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.05);" +
            "-fx-background-radius: 14;-fx-border-radius: 14;" +
            "-fx-border-color: " + color + "33;-fx-border-width: 1;"
        );

        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(28));

        Label serviceLbl = new Label(service);
        serviceLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        serviceLbl.setTextFill(Color.WHITE);

        Label numberLbl = new Label(number);
        numberLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        numberLbl.setTextFill(Color.web(color));

        card.getChildren().addAll(iconLbl, serviceLbl, numberLbl);
        return card;
    }

    private static VBox createAppCard(String icon, String name, String desc) {
        VBox card = new VBox(4);
        card.setPadding(new Insets(14));
        card.setPrefWidth(170);
        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.04);" +
            "-fx-background-radius: 12;-fx-border-radius: 12;" +
            "-fx-border-color: rgba(255,255,255,0.06);-fx-border-width: 1;"
        );

        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(22));
        Label nameLbl = new Label(name);
        nameLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        nameLbl.setTextFill(Color.WHITE);
        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font("Segoe UI", 10));
        descLbl.setTextFill(Color.web("#ffffff", 0.4));

        card.getChildren().addAll(iconLbl, nameLbl, descLbl);
        return card;
    }
}